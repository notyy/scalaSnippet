package codeAnalyze;

import java.util.Map;
import java.util.Set;

public class AnalyzeResult {
    private int fileCount;
    private long totalLineCount;
    private Map<String, Integer> fileTypeCountMap;

    public AnalyzeResult(int fileCount, long totalLineCount, Map<String, Integer> fileTypeCountMap) {
        this.fileCount = fileCount;
        this.totalLineCount = totalLineCount;
        this.fileTypeCountMap = fileTypeCountMap;
    }

    public int fileCount() {
        return fileCount;
    }

    public long totalLineCount() {
        return totalLineCount;
    }

    public Set<String> fileTypes() {
        return fileTypeCountMap.keySet();
    }

    public int fileCountForType(String fileType) {
        return fileTypeCountMap.get(fileType);
    }
}
