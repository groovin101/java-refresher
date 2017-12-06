package com.groovin101.refresher.stream;

import com.groovin101.refresher.util.TestUtil;
import org.junit.Test;

import java.io.BufferedWriter;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 */
public class NIOFileSystemTest {

    /**
     * Normally Paths deal with the default OS, represented by a FileSystem object, but zip files are
     * actually considered a FileSystem in and of themselves.
     */
    @Test
    public void zipfile_asFileSystem_copyFilesInto() throws URISyntaxException {

        //Note we are using try-with-resources... if we fail to close the zipFileAsFS via these means or manually, our copy would not succeed
        try (FileSystem zipFileAsFS = createZipFile("zip-01.zip")) {
            //write first file using a new name
            Path destinationFileFromZipFileSystem = zipFileAsFS.getPath("new-filename-01-for-zip.txt");
            Files.copy(getFileToAddViaDefaultFileSystem(), destinationFileFromZipFileSystem, StandardCopyOption.REPLACE_EXISTING);//replace if already exists

            //add a subdirectory to the zip
            Path newSubDirInZipPath = zipFileAsFS.getPath("newSubDirectory/");
            Files.createDirectory(newSubDirInZipPath);

            //write second file to the new subdirectory
            Path destinationFile2FromZipFileSystem = zipFileAsFS.getPath("newSubDirectory", "add-to-zip-02-changed-name-see.txt");
            Files.copy(getFileToAddViaPathsObjectAsShortcut(), destinationFile2FromZipFileSystem, StandardCopyOption.REPLACE_EXISTING);
        }
        catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void zipfile_asFileSystem_createFileInZipUsingWriter() throws URISyntaxException {
        try (FileSystem zipFs = createZipFile("zip-02.zip")) {
            do_zipfile_asFileSystem_createFileInZipUsingWriter(zipFs);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void do_zipfile_asFileSystem_createFileInZipUsingWriter(FileSystem zipFs) {
        Path newFileToWriteTo = zipFs.getPath("created-programmatically-by-Writer.txt");
        try (BufferedWriter bufferedWriter = Files.newBufferedWriter(newFileToWriteTo, StandardOpenOption.CREATE)) {
            bufferedWriter.write("I was written using...");
            bufferedWriter.newLine();
            bufferedWriter.write("... a BufferedWriter!");
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void zipfile_asFileSystem_createFileInZipUsingFilesClass() throws URISyntaxException {

        List<String> dataInAnIterable = new ArrayList<>();
        dataInAnIterable.add("I was written using...");
        dataInAnIterable.add("... Files.write(PathRepresentingThisFile, IterableCollectionOfStrings, Charset, StandardOpenOptionOnHowToCreateOrEditFile)");

        try (FileSystem zipFs = createZipFile("zip-03.zip")) {
            Path newFileToWriteTo = zipFs.getPath("created-programmatically-by-Files-class.txt");
            //Collections are Iterables
            Files.write(newFileToWriteTo, dataInAnIterable, Charset.defaultCharset(), StandardOpenOption.CREATE);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    private FileSystem createZipFile(String filename) throws URISyntaxException, IOException {

        Map<String, String> fsProps = new HashMap<>();
        fsProps.put("create", "true"); //tell fs to create file if does not exist

        Path zipPath = Paths.get(TestUtil.addOutputDirectoryToFilename(filename));
        URI zipURI = new URI("jar:file", zipPath.toUri().getPath(), null);
        return FileSystems.newFileSystem(zipURI, fsProps);
    }

    private Path getFileToAddViaDefaultFileSystem() {
        return Paths.get(TestUtil.TEST_RESOURCES_PATH, "oneDirDown", "add-to-zip-01.txt");
    }

    private Path getFileToAddViaPathsObjectAsShortcut() {
        return FileSystems.getDefault().getPath(TestUtil.TEST_RESOURCES_PATH, "oneDirDown", "add-to-zip-02.txt");
    }

}
