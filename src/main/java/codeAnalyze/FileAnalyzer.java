package codeAnalyze;

import java.io.File;
import java.io.IOException;

public interface FileAnalyzer {
    long countLines(File file) throws IOException;
}
