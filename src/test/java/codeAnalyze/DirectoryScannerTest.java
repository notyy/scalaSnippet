package codeAnalyze;

import org.junit.Test;

import java.io.File;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class DirectoryScannerTest {
    @Test
    public void can_recursive_scan_folder_and_return_file_list(){
        DirectoryScanner directoryScanner = new DirectoryScannerImpl();
        List<File> files = directoryScanner.scan("src/test/fixture");
        assertThat(files.size(), is(3));
    }
}