package com.joojeongyong.document.domain.document;

import lombok.RequiredArgsConstructor;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ReportService {
    private final ExcelService excelService;
    private final PdfService pdfService;

    public PDDocument getPdf() {
        return pdfService.getPdf();
    }

    public Workbook getExcel() {
        return excelService.getExcel();
    }
}
