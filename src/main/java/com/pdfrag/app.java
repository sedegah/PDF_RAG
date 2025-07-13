package com.pdfrag;

import java.util.Map;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter PDF file path:");
        String pdfPath = sc.nextLine();

        String text = PdfTextExtractor.extractText(pdfPath);
        Map<String, Integer> ragStats = RagClassifier.classifySentences(text);

        String annotatedPath = "annotated_output.pdf";
        String reportPath = "rag_summary_report.pdf";

        PdfAnnotator.annotate(pdfPath, annotatedPath);
        ReportGenerator.generate(ragStats, reportPath);

        System.out.println("✅ Annotated PDF: " + annotatedPath);
        System.out.println("✅ RAG Report: " + reportPath);
    }
}
