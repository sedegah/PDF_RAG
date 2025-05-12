package com.yourorg.pdfrag;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class PdfTextExtractor {
    public static List<String> extractText(String path) throws Exception {
        try (PDDocument doc = PDDocument.load(new File(path))) {
            PDFTextStripper stripper = new PDFTextStripper();
            List<String> pages = new ArrayList<>();
            int pageCount = doc.getNumberOfPages();
            for (int i = 1; i <= pageCount; i++) {
                stripper.setStartPage(i);
                stripper.setEndPage(i);
                pages.add(stripper.getText(doc));
            }
            return pages;
        }
    }
}
