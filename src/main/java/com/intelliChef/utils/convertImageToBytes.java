package com.intelliChef.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Takes an image path and converts the image into byte array.
 */
public class convertImageToBytes {
    public static byte[] execute(String imagePath) throws IOException, RuntimeException {
        Path path = Paths.get(imagePath);
        return Files.readAllBytes(path);
    }
}
