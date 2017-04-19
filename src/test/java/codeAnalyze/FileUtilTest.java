package codeAnalyze;

import org.junit.Test;

import java.io.File;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class FileUtilTest {
    @Test
    public void can_extract_extPath_of_a_file(){
        String extPath = FileUtil.extPath(new File("src/test/fixture/1.scala"));
        assertThat(extPath, is("scala"));
    }
}