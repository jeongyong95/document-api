package com.joojeongyong.document.domain.document;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.springframework.stereotype.Service;

@Service
public class PdfService {

  public PDDocument getPdf() {
    PDDocument document = new PDDocument();
    document.addPage(new PDPage());
    document.addPage(new PDPage());
    return document;
  }
}
