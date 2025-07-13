package com.pdfrag;

import java.io.*;
import java.util.Map;
import org.apache.pdfbox.pdmodel.*;
import org.apache.pdfbox.pdmodel.common.*;
import org.apache.pdfbox.pdmodel.font.*;

public class ReportGenerator {
    public static void generate(Map<String, Integer> stats, String path) throws IOException {
        PDDocument doc = new PDDocument();
        PDPage page = new PDPage();
        doc.addPage(page);

        PDPageContentStream stream = new PDPageContentStream(doc, page);
        PDFont font = PDType1Font.HELVETICA_BOLD;

        stream.beginText();
        stream.setFont(font, 16);
        stream.newLineAtOffset(50, 700);
        stream.showText("RAG Summary Report");
        stream.endText();

        stream.beginText();
        stream.setFont(PDType1Font.HELVETICA, 12);
        stream.newLineAtOffset(50, 670);
        for (Map.Entry<String, Integer> entry : stats.entrySet()) {
            stream.showText(entry.getKey() + ": " + entry.getValue());
            stream.newLineAtOffset(0, -20);
        }
        stream.endText();

        stream.close();
        doc.save(path);
        doc.close();
    }
}
