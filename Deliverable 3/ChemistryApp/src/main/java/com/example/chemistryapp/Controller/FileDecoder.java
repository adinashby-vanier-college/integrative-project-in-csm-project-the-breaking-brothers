package com.example.chemistryapp.Controller;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Base64;

public class FileDecoder {
    public static void decodeFile(String encodedFilePath, String outputFilePath) throws IOException {
        // Read the encoded file into a string
        BufferedReader reader = new BufferedReader(new FileReader(encodedFilePath));
        String encodedString = reader.readLine();
        reader.close();

        // Decode the Base64 string back to bytes
        byte[] decodedBytes = Base64.getDecoder().decode(encodedString);

        // Write the decoded bytes to the output file
        FileOutputStream fileOutputStream = new FileOutputStream(outputFilePath);
        fileOutputStream.write(decodedBytes);
        fileOutputStream.close();

        System.out.println("File decoded and saved to: " + outputFilePath);
    }
}