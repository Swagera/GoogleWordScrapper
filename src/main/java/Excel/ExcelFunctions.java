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

    public void saveToExcel(List<String> hotelNames, List<String> hotelReviewsCount,List<String> hotelRate,
                            List<String> hotelPrice, List<String> isBreakfast, List<String> isCancellationFree, String fileName) throws Exception {
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

        Cell headerPrice = headerRow.createCell(3);
        headerPrice.setCellValue("Price");

        Cell headerBreakfast = headerRow.createCell(4);
        headerBreakfast.setCellValue("Breakfast");

        Cell headerCancellation = headerRow.createCell(5);
        headerCancellation.setCellValue("Free cancellation");


        int size = hotelNames.size();

        for (int i = 0; i < size; i++) {
            Row row = sheet.createRow(rowIndex++);
            Cell cellName = row.createCell(0);
            cellName.setCellValue(hotelNames.get(i));

            Cell cellCount = row.createCell(1);
            cellCount.setCellValue(hotelReviewsCount.get(i));

            Cell cellRate = row.createCell(2);
            cellRate.setCellValue(hotelRate.get(i));

            Cell cellPrice = row.createCell(3);
            cellPrice.setCellValue(hotelPrice.get(i));

            Cell cellBreakfast = row.createCell(4);
            cellBreakfast.setCellValue(isBreakfast.get(i));

            Cell cellCancellation = row.createCell(5);
            cellCancellation.setCellValue(isCancellationFree.get(i));
        }

        FileOutputStream outputStream = new FileOutputStream("output/" + fileName + ".xlsx");
        workbook.write(outputStream);
        outputStream.close();
    }
}

