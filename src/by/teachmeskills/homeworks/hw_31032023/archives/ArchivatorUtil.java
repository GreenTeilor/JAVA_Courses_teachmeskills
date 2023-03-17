package by.teachmeskills.homeworks.hw_31032023.archives;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ArchivatorUtil {

    private ArchivatorUtil() {

    }

    public static void archive(File directory, File output) {
        try (ZipOutputStream zout = new ZipOutputStream(new FileOutputStream(output))) {
            for (File file : directory.listFiles()) {
                FileInputStream reader = new FileInputStream(file);
                ZipEntry entry = new ZipEntry(file.getName());
                zout.putNextEntry(entry);
                byte[] buffer = new byte[reader.available()];
                reader.read(buffer);
                zout.write(buffer);
                zout.closeEntry();
                reader.close();
                file.delete();
            }
            directory.delete();
        } catch (IOException | NullPointerException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void printFilesInDirectory(File directory) {
        try {
            for (File file : directory.listFiles()) {
                System.out.println(file.getPath());
            }
        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
        }
    }
}
