package com.joojeongyong.document.api.controller;

import com.joojeongyong.document.domain.document.ExcelService;
import com.joojeongyong.document.domain.document.PdfService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RequiredArgsConstructor
@RequestMapping(path = "/api/documents")
@RestController
public class DocumentController {
  private final ExcelService excelService;
  private final PdfService pdfService;

  @GetMapping("/excel")
  public ResponseEntity<Resource> getExcelFile() throws IOException {
    Resource resource = excelService.getExcel();
    return ResponseEntity.ok()
        .header(
            HttpHeaders.CONTENT_DISPOSITION,
            "attachment; filename=\"" + resource.getFilename() + "\"")
        .contentType(MediaType.APPLICATION_OCTET_STREAM)
        .contentLength(resource.contentLength())
        .body(resource);
  }

  @GetMapping("/pdf")
  public ResponseEntity<Resource> getPdfFile() throws IOException {
    Resource resource = pdfService.getPdf();
    return ResponseEntity.ok()
        .header(
            HttpHeaders.CONTENT_DISPOSITION,
            "attachment; filename=\"" + resource.getFilename() + "\"")
        .contentType(MediaType.APPLICATION_PDF)
        .contentLength(resource.contentLength())
        .body(resource);
  }
}
