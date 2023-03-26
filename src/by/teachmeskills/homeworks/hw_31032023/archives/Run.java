package by.teachmeskills.homeworks.hw_31032023.archives;

import java.io.File;

public class Run {
    private final static String DIRECTORY = "data\\archive";

    public static void main(String[] args) {
        File directory = new File(DIRECTORY + "\\files");
        File output = new File(DIRECTORY + "\\result.zip");
        ArchivatorUtil.printFilesInDirectory(directory);
        ArchivatorUtil.archive(directory, output);
    }
}
