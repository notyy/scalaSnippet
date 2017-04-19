package codeAnalyze;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class FileAnalyzerImpl implements FileAnalyzer {
    public FileAnalyzerImpl() {
    }

    @Override
    public long countLines(File file) throws IOException {
        long count = 0;
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));
            while (reader.readLine() != null) {
                count += 1;
            }
        } finally {
            if (reader != null) reader.close();
        }
        return count;
    }
}