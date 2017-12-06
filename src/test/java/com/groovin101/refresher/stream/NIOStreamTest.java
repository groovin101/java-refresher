package com.groovin101.refresher.stream;

import com.groovin101.refresher.util.TestUtil;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 *
 */
public class NIOStreamTest {

    @Test
    public void readWholeFile_lineAtATime_pathAndFilesClasses() {
        Path theFileAsPathObject =  Paths.get(TestUtil.addResourcesDirectoryPrefix("nio-test-01.txt"));
        try {
            List<String> lines = Files.readAllLines(theFileAsPathObject);
            for (String l : lines) {
                System.out.println(l);
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
