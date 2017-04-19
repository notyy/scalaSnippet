package codeAnalyze;

import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class CodeAnalyzerTest {

    private CodeAnalyzer codeAnalyzer = new CodeAnalyzer(new DirectoryScannerImpl(), new FileAnalyzerImpl());
    private String path = "src/test/fixture";

    @Test
    public void should_aggregate_file_analyze_result() throws IOException {
        DirectoryScanner directoryScanner = mock(DirectoryScanner.class);
        List<File> files = new ArrayList<File>();
        files.add(new File("1.java"));
        files.add(new File("2.java"));
        files.add(new File("3.java"));
        files.add(new File("4.scala"));
        when(directoryScanner.scan(anyString())).thenReturn(files);

        FileAnalyzer fileAnalyzer = mock(FileAnalyzer.class);
        when(fileAnalyzer.countLines(new File("1.java"))).thenReturn(5L);
        when(fileAnalyzer.countLines(new File("2.java"))).thenReturn(10L);
        when(fileAnalyzer.countLines(new File("3.java"))).thenReturn(15L);
        when(fileAnalyzer.countLines(new File("4.scala"))).thenReturn(20L);
        CodeAnalyzer codeAnalyzer = new CodeAnalyzer(directoryScanner, fileAnalyzer);
        AnalyzeResult analyzeResult = codeAnalyzer.analyze(path);
        assertThat(analyzeResult.fileCount(), is(4));
        assertThat(analyzeResult.totalLineCount(), is(50L));
        Set<String> types = new HashSet<String>() {
        };
        types.add("java");
        types.add("scala");
        assertThat(analyzeResult.fileTypes(),is(types));
        assertThat(analyzeResult.fileCountForType("java"),is(3));
        assertThat(analyzeResult.fileCountForType("scala"),is(1));
    }

//    @Test
//    public void can_return_file_count_by_file_type() throws IOException {
//
//        CodeAnalyzer codeAnalyzer = new CodeAnalyzer(new DirectoryScannerImpl());
//
//        AnalyzeResult analyzeResult = codeAnalyzer.analyze(path);
//
//        Map<String, Integer> fileCountByType = analyzeResult.fileCountByType();
//
//    }
}