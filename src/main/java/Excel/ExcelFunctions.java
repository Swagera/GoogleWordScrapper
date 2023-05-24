package Excel;

import Validation.Validation;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.swing.*;
import java.io.File;
import java.io.FileOutputStream;
import java.util.*;

public class ExcelFunctions extends Validation {

    public void saveToExcel(List<String> hotelNames, List<Integer> hotelReviewsCount,List<String> hotelRate, String fileName) throws Exception {
        if (!isValidFileName(fileName)) {
            throw new IllegalArgumentException("File name is invalid");
        }

        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet();
        int rowIndex = 0;

        Row headerRow = sheet.createRow(rowIndex++);
        Cell headerName = headerRow.createCell(0);
        headerName.setCellValue("Hotel Name");

        Cell headerCount = headerRow.createCell(1);
        headerCount.setCellValue("Reviews Count");

        Cell headerRate = headerRow.createCell(2);
        headerRate.setCellValue("Rate");


            int maxSize = Math.max(hotelNames.size(), Math.max(hotelReviewsCount.size(), hotelRate.size()));
            for (int i = 0; i < maxSize; i++) {
                Row row = sheet.createRow(rowIndex++);
                Cell cellName = row.createCell(0);
                cellName.setCellValue(i < hotelNames.size() ? hotelNames.get(i) : "");

                Cell cellCount = row.createCell(1);
                if (i < hotelReviewsCount.size()) {
                    Integer reviewsCount = hotelReviewsCount.get(i);
                    cellCount.setCellValue(reviewsCount != null ? String.valueOf(reviewsCount) : "");
                } else {
                    cellCount.setCellValue("");
                }

                Cell cellRate = row.createCell(2);
                cellRate.setCellValue(i < hotelRate.size() ? hotelRate.get(i) : "");
            }

        FileOutputStream outputStream = new FileOutputStream("C:\\Users\\48500\\Desktop\\Projekty\\BookingScrapper\\output\\" + fileName + ".xlsx");
        workbook.write(outputStream);
        outputStream.close();
    }
}

