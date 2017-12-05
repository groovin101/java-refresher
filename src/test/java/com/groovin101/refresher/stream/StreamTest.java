package com.groovin101.refresher.stream;

import org.junit.Test;

import java.io.*;

/**
 *
 */
public class StreamTest {

    @Test
    public void fileReader_read() {
        File file = new File(this.getClass().getClassLoader().getResource("stream-test-01.txt").getFile());
        try (Reader reader = new FileReader(file)) {
            int length = 0;
            char[] charBuffer = new char[9];
            while ((length = reader.read(charBuffer)) >= 0) {
                System.out.println("here come " + length + " more chars:");
                for (char c : charBuffer) {
                    System.out.println(" " + c);
                }
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void fileReader_fileWriter_readAndWrite() {
        File file = new File(this.getClass().getClassLoader().getResource("stream-test-01.txt").getFile());
        try (Reader reader = new FileReader(file);
             Writer writer = new FileWriter("./target/test-classes/stream-test-01-copy.txt")) {
            int length = 0;
            char[] charBuffer = new char[9];
            while ((length = reader.read(charBuffer)) >= 0) {
                System.out.println("here come " + length + " more chars:");
                for (char c : charBuffer) {
                    System.out.println(" " + c);
                }
                writer.write(charBuffer, 0, length);
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void bufferedReader_bufferedWriter_readAndWrite_lineByLine() {
        File fileToReadFrom = new File(getClass().getClassLoader().getResource("buffered-stream-test-02.txt").getFile());
        String fileToWriteToPath = "./target/test-classes/buffered-stream-test-02-out.txt";
        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(fileToReadFrom));
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileToWriteToPath))) {
            String nextLine = bufferedReader.readLine();
            while(nextLine != null) {
                System.out.println(nextLine);
                bufferedWriter.write("leroy jenkins was here... " + nextLine);
                bufferedWriter.newLine();
                nextLine = bufferedReader.readLine();
            }
        }
        catch(IOException e) {
            e.printStackTrace();
        }
    }
}