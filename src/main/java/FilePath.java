// Java Program to illustrate Reading from FileReader
// using BufferedReader Class

// Importing input output classes
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

// Main class
public class FilePath {

    public static String getExcelFilePath() throws IOException {
        File file = new File("C:\\Users\\saad_\\Desktop\\ForeFrontFencingApplication\\Documents\\ExcelFilePath.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));
        return br.readLine();
    }

    public static void setExcelFilePath(String message) throws IOException {
        // Creating an instance of file
        Path path = Paths.get("C:\\Users\\saad_\\Desktop\\ForeFrontFencingApplication\\Documents\\ExcelFilePath.txt");
        // Converting string to byte array
        // using getBytes() method
        byte[] arr = message.getBytes();

        // Clear the file
        try(BufferedWriter writer = Files.newBufferedWriter(path)){
            writer.write("");
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Try block to check for exceptions
        try {
            // Now calling Files.write() method using path
            // and byte array
            Files.write(path, arr);
        }// Catch block to handle the exceptions
        catch (IOException ex) {
            // Print message as exception occurred when
            // invalid path of local machine is passed
            System.out.print("Invalid Path");
        }
    }

    public static String getPDFFilePath() throws IOException {
        File file = new File("C:\\Users\\saad_\\Desktop\\ForeFrontFencingApplication\\Documents\\PDFFilePath.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));
        return br.readLine();
    }

    public static void setPDFFilePath(String message) throws IOException {
        // Creating an instance of file
        Path path = Paths.get("C:\\Users\\saad_\\Desktop\\ForeFrontFencingApplication\\Documents\\PDFFilePath.txt");
        // Converting string to byte array
        // using getBytes() method
        byte[] arr = message.getBytes();

        // Clear the file
        try(BufferedWriter writer = Files.newBufferedWriter(path)){
            writer.write("");
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Try block to check for exceptions
        try {
            // Now calling Files.write() method using path
            // and byte array
            Files.write(path, arr);
        }// Catch block to handle the exceptions
        catch (IOException ex) {
            // Print message as exception occurred when
            // invalid path of local machine is passed
            System.out.print("Invalid Path");
        }
    }

    public static String getFilledPDFFormFilePath() throws IOException {
        File file = new File("C:\\Users\\saad_\\Desktop\\ForeFrontFencingApplication\\Documents\\FilledPDFFormFilePath.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));
        return br.readLine();
    }

    public static void setFilledPDFFilePath(String message) throws IOException {
        // Creating an instance of file
        Path path = Paths.get("C:\\Users\\saad_\\Desktop\\ForeFrontFencingApplication\\Documents\\FilledPDFFormFilePath.txt");

        // Adding backslash at the end of the path if forgotten by user
        if (!message.endsWith("\\")){
            message = message + "\\";
        }

        // Converting string to byte array
        // using getBytes() method
        byte[] arr = message.getBytes();

        // Clear the file
        try(BufferedWriter writer = Files.newBufferedWriter(path)){
            writer.write("");
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Try block to check for exceptions
        try {
            // Now calling Files.write() method using path
            // and byte array
            Files.write(path, arr);
        }// Catch block to handle the exceptions
        catch (IOException ex) {
            // Print message as exception occurred when
            // invalid path of local machine is passed
            System.out.print("Invalid Path");
        }
    }

}