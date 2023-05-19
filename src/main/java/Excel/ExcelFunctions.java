package Excel;

import Validation.Validation;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.util.*;

public class ExcelFunctions extends Validation {

    public void saveToExcel(List<String> hotelNames, String fileName) throws Exception {
        if (!isValidFileName(fileName)) {
            throw new IllegalArgumentException("File name is invalid");
        }

        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet();
        int rowIndex = 0;
        Cell textValue;

        for (String name : hotelNames) {
            Row row = sheet.createRow(rowIndex++);
            textValue = row.createCell(0);
            textValue.setCellValue(name);

        }

        FileOutputStream outputStream = new FileOutputStream(new File("C:\\Users\\48500\\Desktop\\Projekty\\GoogleWordScrapper\\output\\" + fileName + ".xlsx"));
        workbook.write(outputStream);
        outputStream.close();
    }
}

