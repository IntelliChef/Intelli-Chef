package use_case.analyze_image;

public class AnalyzeImageInputData {
    private final byte[] imageBytes;

    public AnalyzeImageInputData(byte[] imageBytes) {
        this.imageBytes = imageBytes;
    }

    public byte[] getImageBytes() {
        return imageBytes;
    }
}
