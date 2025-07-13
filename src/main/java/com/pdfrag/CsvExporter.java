package com.pdfrag;

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class CsvExporter {

    public static void export(String text, String outputPath) throws IOException {
        try (FileWriter writer = new FileWriter(outputPath)) {
            writer.write("Sentence,Status\n");

            for (String line : text.split("[.\\n]")) {
                String trimmed = line.trim().replaceAll(",", " ");
                if (trimmed.length() < 5) continue;

                String status = RagClassifier.classify(trimmed);
                writer.write("\"" + trimmed + "\"," + status + "\n");
            }
        }
    }
}
