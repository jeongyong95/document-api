package com.joojeongyong.document.domain.document;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.WorkbookUtil;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

@Service
public class ExcelService {

  public Workbook getExcel() {
    Workbook sampleWorkBook = this.workbook();
    this.addNewSheet(sampleWorkBook, "POI_SAMPLE");
    this.addNewCell(sampleWorkBook, sampleWorkBook.getSheet("POI_SAMPLE"), 0, "Hello Honey!");
    sampleWorkBook.createSheet("허니");
    sampleWorkBook.createSheet("주주");
    return sampleWorkBook;
  }

  // new workbook
  public Workbook workbook() {
    return new XSSFWorkbook();
  }

  public Sheet addNewSheet(Workbook workbook, String newSheetName) {
    // Note that sheet name is Excel must not exceed 31 characters
    // and must not contain any of the any of the following characters:
    // 0x0000, 0x0003, colon (:), backslash (\), asterisk (*), question mark (?), forward slash (/),
    // opening square bracket ([), closing square bracket (])

    // You can use org.apache.poi.ss.util.WorkbookUtil#createSafeSheetName(String nameProposal)}
    // for a safe way to create valid names, this utility replaces invalid characters with a space
    // (' ')
    String safeSheetName = WorkbookUtil.createSafeSheetName(newSheetName);
    return workbook.createSheet(safeSheetName);
  }

  public void addNewCell(Workbook workbook, Sheet sheet, int rowNumber, String cellValue) {
    Row row = sheet.createRow(rowNumber);
    Cell cell = row.createCell(row.getLastCellNum() + 1);
    cell.setCellValue(cellValue);
  }
}
