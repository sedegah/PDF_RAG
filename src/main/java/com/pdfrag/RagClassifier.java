package com.pdfrag;

import java.util.*;

public class RagClassifier {
    private static final List<String> RED = Arrays.asList("urgent", "critical", "failure");
    private static final List<String> AMBER = Arrays.asList("warning", "delayed", "risk");
    private static final List<String> GREEN = Arrays.asList("ok", "complete", "success");

    public static String classify(String text) {
        String lower = text.toLowerCase();
        if (RED.stream().anyMatch(lower::contains)) return "RED";
        if (AMBER.stream().anyMatch(lower::contains)) return "AMBER";
        return "GREEN";
    }

    public static Map<String, Integer> classifySentences(String text) {
        Map<String, Integer> results = new HashMap<>();
        results.put("RED", 0);
        results.put("AMBER", 0);
        results.put("GREEN", 0);
        for (String line : text.split("[.\\n]")) {
            String status = classify(line);
            results.put(status, results.get(status) + 1);
        }
        return results;
    }
}
