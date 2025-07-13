package com.pdfrag;

import java.io.*;
import java.util.List;
import java.util.Arrays;
import org.apache.pdfbox.pdmodel.*;
import org.apache.pdfbox.text.*;
import org.apache.pdfbox.pdmodel.interactive.annotation.*;

public class PdfAnnotator {

    public static void annotate(String inputPath, String outputPath) throws IOException {
        PDDocument doc = PDDocument.load(new File(inputPath));
        PDFTextStripperByArea stripper = new PDFTextStripperByArea();
        PDFTextStripper textStripper = new PDFTextStripper();

        for (int page = 0; page < doc.getNumberOfPages(); page++) {
            PDPage pdPage = doc.getPage(page);
            String content = textStripper.getText(doc);

            for (String status : Arrays.asList("RED", "AMBER", "GREEN")) {
                if (content.toLowerCase().contains(status.toLowerCase())) {
                    PDAnnotationText annotation = new PDAnnotationText();
                    annotation.setContents("RAG Status: " + status);
                    annotation.setColor(getColor(status));
                    pdPage.getAnnotations().add(annotation);
                }
            }
        }

        doc.save(outputPath);
        doc.close();
    }

    private static PDColor getColor(String status) {
        switch (status) {
            case "RED": return new PDColor(new float[]{1, 0, 0}, PDDeviceRGB.INSTANCE);
            case "AMBER": return new PDColor(new float[]{1, 0.64f, 0}, PDDeviceRGB.INSTANCE);
            default: return new PDColor(new float[]{0, 1, 0}, PDDeviceRGB.INSTANCE);
        }
    }
}
