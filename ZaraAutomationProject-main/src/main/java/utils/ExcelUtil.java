package utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

public class ExcelUtil {
    private Sheet sheet;

    public ExcelUtil(String filePath, String sheetName) {
        try {
            FileInputStream inputStream = new FileInputStream(filePath);
            Workbook workbook = WorkbookFactory.create(inputStream);
            sheet = workbook.getSheet(sheetName);
            if (sheet == null) {
                throw new RuntimeException("Sayfa bulunamadı: " + sheetName);
            }
        } catch (Exception e) {
            throw new RuntimeException("Excel okuma hatası: " + e.getMessage());
        }
    }

    public String getCellData(int row, int column) {
        return sheet.getRow(row).getCell(column).toString();
    }

    public List<String> getAllDataInColumn(int columnIndex) {
        List<String> columnData = new ArrayList<>();
        for (Row row : sheet) {
            columnData.add(row.getCell(columnIndex).toString());
        }
        return columnData;
    }
}
