package codeAnalyze;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class DirectoryScannerImpl implements DirectoryScanner {
    public DirectoryScannerImpl() {
    }

    @Override
    public List<File> scan(String path) {
        File directory = new File(path);
        File[] subFiles = directory.listFiles();
        List<File> rs = new ArrayList<File>();
        for (File subFile : subFiles) {
            if (subFile.isFile()) {
                rs.add(subFile);
            } else {
                rs.addAll(scan(subFile.getPath()));
            }
        }
        return rs;
    }
}