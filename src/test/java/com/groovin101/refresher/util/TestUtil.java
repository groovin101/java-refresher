package com.groovin101.refresher.util;

/**
 *
 */
public class TestUtil {

    public static final String TEST_RESOURCES_PATH = "./src/test/resources";
    public static final String OUTPUT_FILE_PATH = "./target/test-classes";

    public static String addOutputDirectoryToFilename(String filename) {
        return OUTPUT_FILE_PATH.concat("/").concat(filename);
    }

    public static String addResourcesDirectoryPrefix(String filenameWithOrWithoutSubDir) {
        return TEST_RESOURCES_PATH.concat("/").concat(filenameWithOrWithoutSubDir);
    }
}
