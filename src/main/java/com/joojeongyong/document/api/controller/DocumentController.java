package com.joojeongyong.document.api.controller;

import com.joojeongyong.document.domain.document.ReportService;
import lombok.RequiredArgsConstructor;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

@RequiredArgsConstructor
@RequestMapping(path = "/api/documents")
@RestController
public class DocumentController {
  private final ReportService reportService;

  @GetMapping("/excel")
  public ResponseEntity<Resource> getExcelFile() throws IOException {
    Workbook workbook = reportService.getExcel();
    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
    workbook.write(byteArrayOutputStream);
    ByteArrayResource byteArrayResource =
        new ByteArrayResource(byteArrayOutputStream.toByteArray());

    return ResponseEntity.ok()
        .header(HttpHeaders.CONTENT_ENCODING, "binary")
        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + "sample.xlsx" + "\"")
        .contentType(MediaType.APPLICATION_OCTET_STREAM)
        .contentLength(byteArrayResource.contentLength())
        .body(byteArrayResource);
  }

  @GetMapping("/pdf")
  public ResponseEntity<Resource> getPdfFile() {
    PDDocument pdf = reportService.getPdf();
    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

    try {
      pdf.save(byteArrayOutputStream);
      pdf.close();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }

    ByteArrayResource byteArrayResource =
        new ByteArrayResource(byteArrayOutputStream.toByteArray());
    return ResponseEntity.ok()
        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + "sample.pdf" + "\"")
        .contentType(MediaType.APPLICATION_PDF)
        .contentLength(byteArrayResource.contentLength())
        .body(byteArrayResource);
  }
}
