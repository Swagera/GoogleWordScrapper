package Excel;

import Validation.Validation;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

public class ExcelFunctions extends Validation {

    public void saveToExcel(List<String> list, String fileName) throws Exception {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet();
        int rowIndex = 0;
        Cell textValue;

        for (String text : list) {

            Row row = sheet.createRow(rowIndex++);
            textValue = row.createCell(0);
            textValue.setCellValue(text);
        }
        if (!isValidFileName(fileName)) {
            FileOutputStream outputStream = new FileOutputStream(new File("output//" + fileName + ".xlsx"));
            workbook.write(outputStream);
        } else {
            throw new IllegalArgumentException("File name is invalid");
        }
    }
}
