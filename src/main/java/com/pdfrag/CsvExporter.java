package com.pdfrag;

import java.io.FileWriter;
import java.io.IOException;

public class CsvExporter {

    public static void export(String text, String outputPath) throws IOException {
        try (FileWriter writer = new FileWriter(outputPath)) {
            writer.write("Sentence,Status\n");

            // Split by period or newline, clean and classify
            for (String line : text.split("[\\.\\n]")) {
                String trimmed = line.trim().replaceAll("[,\"]", " ");
                if (trimmed.length() < 5) continue; // skip very short lines

                String status = RagClassifier.classify(trimmed);

                // Proper CSV quoting
                writer.write("\"" + trimmed + "\"," + status + "\n");
            }
        }
    }
}
