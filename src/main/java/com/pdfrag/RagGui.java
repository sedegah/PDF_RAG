package com.pdfrag;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.Map;

public class RagGui extends JFrame {

    private JTextArea statusArea;
    private JButton selectBtn;
    private JButton analyzeBtn;
    private JLabel fileLabel;
    private File selectedFile;

    public RagGui() {
        setTitle("PDF RAG Analyzer");
        setSize(500, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // UI Components
        statusArea = new JTextArea();
        statusArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(statusArea);

        selectBtn = new JButton("📂 Select PDF");
        analyzeBtn = new JButton("🧠 Analyze");
        analyzeBtn.setEnabled(false);
        fileLabel = new JLabel("No file selected");

        // Top panel
        JPanel topPanel = new JPanel(new FlowLayout());
        topPanel.add(selectBtn);
        topPanel.add(analyzeBtn);

        // Add to frame
        add(topPanel, BorderLayout.NORTH);
        add(fileLabel, BorderLayout.CENTER);
        add(scrollPane, BorderLayout.SOUTH);

        // Event handlers
        selectBtn.addActionListener(e -> chooseFile());
        analyzeBtn.addActionListener(e -> analyzeFile());

        setVisible(true);
    }

    private void chooseFile() {
        JFileChooser chooser = new JFileChooser();
        int result = chooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            selectedFile = chooser.getSelectedFile();
            fileLabel.setText("Selected: " + selectedFile.getName());
            analyzeBtn.setEnabled(true);
        }
    }

    private void analyzeFile() {
        try {
            String text = PdfTextExtractor.extractText(selectedFile.getAbsolutePath());
            Map<String, Integer> stats = RagClassifier.classifySentences(text);

            String outputPath = "annotated_gui_output.pdf";
            String reportPath = "gui_summary_report.pdf";

            PdfAnnotator.annotate(selectedFile.getAbsolutePath(), outputPath);
            ReportGenerator.generate(stats, reportPath);

            StringBuilder output = new StringBuilder();
            output.append("Analysis Complete:\n");
            stats.forEach((k, v) -> output.append(k).append(": ").append(v).append("\n"));
            output.append("\nSaved:\n").append(outputPath).append("\n").append(reportPath);
            statusArea.setText(output.toString());

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(RagGui::new);
    }
}
