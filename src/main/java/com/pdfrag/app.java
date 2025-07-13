package com.yourorg.pdfrag;

import org.apache.commons.cli.*;
import javax.swing.*;
import java.awt.*;

public class App {
    public static void main(String[] args) throws Exception {
        Options opts = new Options();
        opts.addOption("i", "input", true, "Input PDF path");
        opts.addOption("o", "output", true, "Annotated PDF output path");
        opts.addOption("r", "report", true, "Summary report PDF output path");
        opts.addOption("red", true, "Red keywords (comma-separated)");
        opts.addOption("amber", true, "Amber keywords (comma-separated)");
        opts.addOption("green", true, "Green keywords (comma-separated)");
        opts.addOption("g", "gui", false, "Launch Swing GUI");

        CommandLineParser parser = new DefaultParser();
        CommandLine cmd = parser.parse(opts, args);

        if (cmd.hasOption("gui")) {
            SwingUtilities.invokeLater(() -> {
                PdfRagGui gui = new PdfRagGui();
                gui.setVisible(true);
            });
            return;
        }

        String input = cmd.getOptionValue("input");
        String output = cmd.getOptionValue("output");
        String report = cmd.getOptionValue("report");
        String[] red = cmd.getOptionValue("red").split(",");
        String[] amber = cmd.getOptionValue("amber").split(",");
        String[] green = cmd.getOptionValue("green").split(",");

        var pages = PdfTextExtractor.extractText(input);
        var statuses = RagAssessor.assess(pages, red, amber, green);

        if (output != null) PdfAnnotator.annotate(input, output, statuses);
        if (report != null) ReportGenerator.generate(report, statuses);
    }
}
