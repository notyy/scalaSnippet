package codeAnalyze;

import java.io.File;
import java.util.List;

public interface DirectoryScanner {
    List<File> scan(String path);
}
