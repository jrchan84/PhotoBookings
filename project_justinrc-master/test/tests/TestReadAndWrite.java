package tests;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestReadAndWrite {

    @BeforeEach
    public void before(){
        try {
            FileWriter data = new FileWriter("testData.txt");
            BufferedWriter buffWr = new BufferedWriter(data);
            String lineOfText = null;

            lineOfText = "testing";
            buffWr.write(lineOfText, 0, lineOfText.length());
            buffWr.newLine();

            lineOfText = "1";
            buffWr.write(lineOfText, 0, lineOfText.length());
            buffWr.newLine();

            lineOfText = "2";
            buffWr.write(lineOfText, 0, lineOfText.length());
            buffWr.newLine();

            lineOfText = "3";
            buffWr.write(lineOfText, 0, lineOfText.length());
            buffWr.newLine();

            buffWr.close();
            data.close();

        } catch (FileNotFoundException e) {
            System.out.println("The date file data.txt was not found or could not be opened.");
        } catch (Exception e) {
            System.out.println("A problem occurred while this program was running");
        }
    }


    // Test reading from a file.
    @Test
    public void testReadingFile() {
        try {
            FileReader data = new FileReader("testData.txt");
            BufferedReader buff = new BufferedReader(data);
            String lineOfData = null;

            lineOfData = buff.readLine();
            assertEquals("testing", lineOfData);
            lineOfData = buff.readLine();
            assertEquals("1", lineOfData);
            lineOfData = buff.readLine();
            assertEquals("2", lineOfData);
            lineOfData = buff.readLine();
            assertEquals("3", lineOfData);

            buff.close();
            data.close();

        } catch (FileNotFoundException e) {
            System.out.println("The date file data.txt was not found or could not be opened.");
        } catch (Exception e) {
            System.out.println("A problem occurred while this program was running");
        }
    }

    // Test writing from a file.
    @Test
    public void testWritingFile() {

        try {
            FileWriter data = new FileWriter("testData.txt");
            BufferedWriter buffWr = new BufferedWriter(data);
            String lineOfText = null;

            lineOfText = "testing";
            buffWr.write(lineOfText, 0, lineOfText.length());
            buffWr.newLine();

            lineOfText = "3";
            buffWr.write(lineOfText, 0, lineOfText.length());
            buffWr.newLine();

            lineOfText = "2";
            buffWr.write(lineOfText, 0, lineOfText.length());
            buffWr.newLine();

            lineOfText = "1";
            buffWr.write(lineOfText, 0, lineOfText.length());
            buffWr.newLine();

            buffWr.close();
            data.close();

            FileReader read = new FileReader("testData.txt");
            BufferedReader buff = new BufferedReader(read);
            String lineOfData = null;

            lineOfData = buff.readLine();
            assertEquals("testing", lineOfData);
            lineOfData = buff.readLine();
            assertEquals("3", lineOfData);
            lineOfData = buff.readLine();
            assertEquals("2", lineOfData);
            lineOfData = buff.readLine();
            assertEquals("1", lineOfData);

            buff.close();
            read.close();



        } catch (FileNotFoundException e) {
            System.out.println("The date file data.txt was not found or could not be opened.");
        } catch (Exception e) {
            System.out.println("A problem occurred while this program was running");
        }
    }

}
