import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

/**
 * The Excel service class to add more rows to the Excel file
 */
public class ExcelFileService {

    /**
     * Add the list of Employee objects as rows to Excel file
     * @param filePath the Excel file path to add more rows
     * @param client object
     */
    public void addRows(String filePath, Client client) {
        InputStream inputStream = null;
        Workbook workbook = null;
        OutputStream outputStream = null;
        try {
            inputStream = new FileInputStream(filePath);
            workbook = WorkbookFactory.create(inputStream);

            // Get first sheet of Excel file
            Sheet sheet = workbook.getSheetAt(0);
            // get latest index number
            int rowIndex = sheet.getLastRowNum();
            // Populate the index value of new row
            rowIndex = rowIndex + 1;
            createNewRow(workbook, sheet, rowIndex, client);
//            for (Client client : Clients) {
//                // Populate the index value of new row
//                rowIndex = rowIndex + 1;
//                createNewRow(workbook, sheet, rowIndex, client);
//            }
            // Write updated Excel file
            outputStream = new FileOutputStream(filePath);
            workbook.write(outputStream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                inputStream.close();
                workbook.close();
                outputStream.close();
            }catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    /**
     * Add new row to Excel sheet
     * @param workbook the Excel workbook
     * @param sheet the Excel sheet
     * @param rowIndex the index to add row
     * @param client the Employee record to add to Excel row
     */
    private void createNewRow(Workbook workbook, Sheet sheet, int rowIndex, Client client) {
        Row row = sheet.createRow(rowIndex);
        CellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setBorderTop(BorderStyle.THIN);
        cellStyle.setTopBorderColor(IndexedColors.BLACK.index);
        cellStyle.setBorderRight(BorderStyle.THIN);
        cellStyle.setRightBorderColor(IndexedColors.BLACK.index);
        cellStyle.setBorderBottom(BorderStyle.THIN);
        cellStyle.setBottomBorderColor(IndexedColors.BLACK.index);
        cellStyle.setBorderLeft(BorderStyle.THIN);
        cellStyle.setLeftBorderColor(IndexedColors.BLACK.index);

        Cell cell = row.createCell(0);
        cell.setCellValue(client.getFirstName());
        cell.setCellStyle(cellStyle);

        cell = row.createCell(1);
        cell.setCellValue(client.getLastName());
        cell.setCellStyle(cellStyle);

        cell = row.createCell(2);
        cell.setCellValue(client.getEmail());
        cell.setCellStyle(cellStyle);

        cell = row.createCell(3);
        cell.setCellValue(client.getPhoneNumber());
        cell.setCellStyle(cellStyle);

        cell = row.createCell(4);
        cell.setCellValue(client.getAddress());
        cell.setCellStyle(cellStyle);
    }
}