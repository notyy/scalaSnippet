package codeAnalyze;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class CodeAnalyzerTest {
    @Test
    public void should_recursive_scan_directory_and_return_file_count(){
        CodeAnalyzer codeAnalyzer = new CodeAnalyzer();
        int fileCount = codeAnalyzer.countFiles("src/test/fixture");
        assertThat(fileCount,is(3));
    }
}