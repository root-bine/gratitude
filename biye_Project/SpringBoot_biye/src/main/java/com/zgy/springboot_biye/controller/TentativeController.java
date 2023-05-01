package com.zgy.springboot_biye.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zgy.springboot_biye.config.Result;
import com.zgy.springboot_biye.controller.dto.SearchPage;
import com.zgy.springboot_biye.domain.Tentative;
import com.zgy.springboot_biye.service.TentativeService;
import org.apache.poi.hssf.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/ten")
public class TentativeController {
    @Autowired
    private TentativeService ten;

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        return Result.success(ten.delete(id));
    }

    @GetMapping("/page")
    public Result findPage(SearchPage searchPage) {
        Integer currentPage = searchPage.getCurrentPage();
        Integer pageSize = searchPage.getPageSize();
        if(currentPage == null || currentPage < 1 || pageSize == null || pageSize <1) {
            return Result.error("参数错误");
        }
        PageHelper.startPage(currentPage, pageSize);
        List<Tentative> page = ten.findPageHelper(searchPage);
        PageInfo<Tentative> pageInfo = new PageInfo<>(page);
        return Result.success(pageInfo);
    }

    @GetMapping("/excelDownloads")
    public void downloadAllClassmate(HttpServletResponse response) throws IOException {
        HSSFWorkbook workbook = new HSSFWorkbook();//创建HSSFWorkbook对象,  excel的文档对象
        HSSFSheet sheet = workbook.createSheet("拟定录取表"); //excel的表单

        List<Tentative> classmateList = ten.findAll();

        String fileName = "Tentative"  + ".xls";//设置要导出的文件的名字
        //新增数据行，并且设置单元格数据
        int rowNum = 1;
        String[] headers = { "学号", "姓名", "专业"};
        //headers表示excel表中第一行的表头
        HSSFRow row = sheet.createRow(0);
        //在excel表中添加表头
        for(int i=0;i<headers.length;i++){
            HSSFCell cell = row.createCell(i);
            HSSFRichTextString text = new HSSFRichTextString(headers[i]);
            cell.setCellValue(text);
        }

        //在表中存放查询到的数据放入对应的列
        for (Tentative tentative : classmateList) {
            HSSFRow row1 = sheet.createRow(rowNum);
            row1.createCell(0).setCellValue(tentative.getStuID());
            row1.createCell(1).setCellValue(tentative.getStuName());
            row1.createCell(2).setCellValue(tentative.getProfession());
            rowNum++;
        }

        response.setContentType("application/octet-stream");
        response.setHeader("Content-disposition", "attachment;filename=" + fileName);
        response.flushBuffer();
        workbook.write(response.getOutputStream());
    }
}
