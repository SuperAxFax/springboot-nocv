package com.fax.controller;

import com.fax.entity.NocvData;
import com.fax.service.IndexService;
import com.fax.vo.DataView;
import com.fax.vo.NocvDataVo;
import com.sun.deploy.net.HttpResponse;
import com.sun.org.glassfish.gmbal.ParameterNames;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ExcelUploadController {

    @Autowired
    private IndexService indexService;
    @RequestMapping("/excelupload")
    @ResponseBody
    public DataView excelUpload(@RequestParam("file") MultipartFile file) throws Exception {
        //1：对文件进行解析
        XSSFWorkbook sheets = new XSSFWorkbook(file.getInputStream());
        XSSFSheet sheetAt = sheets.getSheetAt(0);
        //2：将解析后的数据插入数据库
        List<NocvData> list = new ArrayList<>();
        for (int i = 0; i < sheetAt.getPhysicalNumberOfRows(); i++) {
            NocvData nocvData = new NocvData();
            XSSFRow row = sheetAt.getRow(i);
            nocvData.setName(row.getCell(0).getStringCellValue());
            nocvData.setValue((int) row.getCell(1).getNumericCellValue());
            list.add(nocvData);
        }
        indexService.saveBatch(list);
        DataView dataView = new DataView();
        //3：返回状态信息
        if (list!=null){
            dataView.setCode(200);
            dataView.setMsg("数据上传成功！");
        }
        System.out.println(dataView);
        System.out.println(list);
        return dataView;
    }

    /**导出中国疫情数据
     * 步骤：
     *    1：查询数据库
     *    2：将查询到的数据封装成excel表格
     *    3：建立输出流，导出数据(先拼接输出文件的名字，，后缀名，格式)
     *    4：关闭流
     */
    @RequestMapping("/dooutput")
    @ResponseBody
    public void doOutput(HttpServletResponse response) throws Exception {
        //1：查询数据库
        List<NocvData> list = indexService.list();
        //2：将查询到的数据封装成excel表格
        XSSFWorkbook sheets = new XSSFWorkbook();
        XSSFSheet sheet = sheets.createSheet("中国疫情数据表");
        XSSFRow row = sheet.createRow(0);
        row.createCell(0).setCellValue("城市名称");
        row.createCell(1).setCellValue("确诊数量");
        for (NocvData nocvData : list) {
            XSSFRow sheetRow = sheet.createRow(sheet.getLastRowNum() + 1);
            sheetRow.createCell(0).setCellValue(nocvData.getName());
            sheetRow.createCell(1).setCellValue(nocvData.getValue());
        }
           //..3：调整reponse的页面格式，不然会乱码
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/octet-stream;chartset=utf8");
        response.setHeader("Content-Disposition","attachment;filename="+ new String("中国疫情数据表".getBytes(),"iso-8859-1")+".xlsx");
        //3：建立输出流，导出数据
        OutputStream os = null;
        os = response.getOutputStream();
        sheets.write(os);
        os.flush();

        //4：关闭输出流
        os.close();
    }
}
