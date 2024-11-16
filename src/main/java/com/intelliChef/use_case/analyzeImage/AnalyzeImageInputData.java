package com.intelliChef.use_case.analyzeImage;

public class AnalyzeImageInputData {
    private final byte[] imageBytes;

    public AnalyzeImageInputData(byte[] imageBytes) {
        this.imageBytes = imageBytes;
    }

    public byte[] getImageBytes() {
        return imageBytes;
    }
}
