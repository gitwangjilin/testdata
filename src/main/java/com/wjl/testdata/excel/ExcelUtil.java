package com.wjl.testdata.excel;

import io.swagger.annotations.ApiModelProperty;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*************************************************************************
 ******
 * - Copyright (c) 2021 redshield.com
 * - File Name: ExcelUtil
 * - @Author: WangJiLIn
 * - Description:
 * 接⼝描述
 * - Functions:
 *
 * - History:
 * Date        Author          Modification
 * 2021/1/21   WangJiLin     Create the current class
 *************************************************************************
 ******/

@Component
public class ExcelUtil {
    public static final String XLS = ".xls";
    public static final String XLSX = ".xlsx";

    public <T> List<T> readExcelFileToDTO(MultipartFile file, Class<T> clazz) throws IOException {
        return readExcelFileToDTO(file, clazz, 0);
    }

    public <T> List<T> readExcelFileToDTO(MultipartFile file, Class<T> clazz, Integer sheetId) throws IOException {
        //将文件转成workbook类型
        Workbook workbook = buildWorkbook(file);
        //第一个表
        return readSheetToDTO(workbook.getSheetAt(sheetId), clazz);
    }

    public <T> List<T> readSheetToDTO(Sheet sheet, Class<T> clazz) throws IOException {
        List<T> result = new ArrayList<>();
        List<Map<String, String>> sheetValue = changeSheetToMapList(sheet);
        for (Map<String, String> valueMap : sheetValue) {
            T dto = buildDTOByClass(clazz, valueMap);
            if (dto != null) {
                result.add(dto);
            }
        }
        return result;
    }

    //类型转换
    private Workbook buildWorkbook(MultipartFile file) throws IOException {
        String filename = file.getOriginalFilename();
        if (filename.endsWith(XLS)) {
            return new HSSFWorkbook(file.getInputStream());
        } else if (filename.endsWith(XLSX)) {
            return new XSSFWorkbook(file.getInputStream());
        } else {
            throw new IOException("unknown file format: " + filename);
        }
    }

    private List<Map<String, String>> changeSheetToMapList(Sheet sheet) {
        List<Map<String, String>> result = new ArrayList<>();
        int rowNumber = sheet.getPhysicalNumberOfRows();
        String[] titles = getSheetRowValues(sheet.getRow(0)); // 第一行作为表头
        for (int i = 1; i < rowNumber; i++) {
            String[] values = getSheetRowValues(sheet.getRow(i));
            Map<String, String> valueMap = new HashMap<>();
            for (int j = 0; j < titles.length; j++) {
                valueMap.put(titles[j], values[j]);
            }
            result.add(valueMap);
        }
        return result;
    }

    private <T> T buildDTOByClass(Class<T> clazz, Map<String, String> valueMap) {
        try {
            T dto = clazz.newInstance();
            for (Field field : clazz.getDeclaredFields()) {
                ApiModelProperty desc = field.getAnnotation(ApiModelProperty.class);
                String value = valueMap.get(desc.value());
                if (value != null) {
                    if(field.getType().getName().equalsIgnoreCase("java.lang.Integer")) {
                        Method method = clazz.getMethod(getSetMethodName(field.getName()), field.getType());
                        Integer age = Integer.parseInt(value.substring(0,value.indexOf(".")));
                        method.invoke(dto, age);
                    }else{
                        Method method = clazz.getMethod(getSetMethodName(field.getName()), field.getType());
                        method.invoke(dto, value);
                    }
                }
            }

            return dto ;
        } catch (Exception e) {
            e.getStackTrace();
        }
        return null;
    }

    private String getSetMethodName(String name) {
        String firstChar = name.substring(0, 1);
        return "set" + firstChar.toUpperCase() + name.substring(1);
    }

    private String[] getSheetRowValues(Row row) {
        if (row == null) {
            return new String[]{};
        } else {
            int cellNumber = row.getLastCellNum();
            List<String> cellValueList = new ArrayList<>();
            for (int i = 0; i < cellNumber; i++) {
                cellValueList.add(getValueOnCell(row.getCell(i)));
            }
            return cellValueList.toArray(new String[0]);
        }
    }

    private String getValueOnCell(Cell cell) {
        if (cell == null) {
            return "";
        }
        switch (cell.getCellTypeEnum()) {
            case STRING: return cell.getStringCellValue();
//            case NUMERIC: return String.format("%.2f", cell.getNumericCellValue());
            case NUMERIC: return String.valueOf(cell.getNumericCellValue());
            case BOOLEAN: return cell.getBooleanCellValue() ? "true" : "false";
            case FORMULA:
                try {
                    return cell.getStringCellValue();
                } catch (Exception e) {
//                    return String.format("%.2f", cell.getNumericCellValue());
                    return String.valueOf(cell.getNumericCellValue());
                }
            default: return "";
        }
    }

    public static void main(String[] args) {
        int i = Integer.parseInt("12");
        System.out.println(i);
        Integer integer = Integer.valueOf("12");
        System.out.println(integer);
    }
}