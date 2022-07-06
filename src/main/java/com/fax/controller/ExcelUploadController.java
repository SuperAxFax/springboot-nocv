package com.fax.controller;

import com.fax.entity.NocvData;
import com.fax.service.IndexService;
import com.fax.vo.DataView;
import com.fax.vo.NocvDataVo;
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

import java.io.IOException;
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
}
