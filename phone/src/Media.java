// Group 4
package phone.src;

import java.util.HashSet;
import java.util.HashMap;

import java.awt.Desktop;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.IOException;

@SuppressWarnings("unused")
public class Media extends Application {
    private final Path dirPath;
    private final HashSet<String> supportedFileTypes;
    private final HashMap<String, Integer> videoLengths;

    public Media(String dirName) {
        File directory = new File(dirName);
        this.supportedFileTypes = new HashSet<>();
        this.supportedFileTypes.add("mp3");
        this.supportedFileTypes.add("mp4");
        this.videoLengths = new HashMap<>();

        if (directory.mkdir()) {
            System.out.println("Directory created successfully!");
        }
        else {
            System.out.println("Failed to create the directory.");
        }
        dirPath = Paths.get(dirName);
    }

    @Override
    protected boolean decodeUserInput(int input) throws Exception {
        switch (input) {
            case 1:
                this.addFile();
                break;
            case 2:
                this.playByName();
                break;
            case 3:
                this.playAll();
                break;
            case 4:
                return true;
            default:
                throw new Exception("Invalid Option");
        }
        return false;
    }

    @Override
    protected void printOptions() {
        System.out.println("Media:");
        System.out.println("1: Add media file");
        System.out.println("2: Play specific file");
        System.out.println("3: Play all");
        System.out.println("4: Exit");
    }

    @Override
    public void onContactRemoval() {
        throw new UnsupportedOperationException("Not implemented, yet.");
    }

    private void addFile() {
        System.out.println("Enter path of new MP3/MP4 file: ");
        Path filePath = Paths.get(scanner.nextLine());
        System.out.println("Enter the file's length in seconds: ");
        String lenStr = scanner.nextLine();
        int fileLen = 0;
        if (isInteger(lenStr)) {
            fileLen = Integer.parseInt(lenStr);
        }

        Path targetFile = this.dirPath.resolve(filePath.getFileName());

        try {
            Files.copy(filePath, targetFile);
            this.videoLengths.put(filePath.getFileName().toString(), fileLen);
        }
        catch (IOException e) {
            System.err.println("Failed to copy the file: " + e.getMessage());
        }
    }

    private static boolean isInteger(String s) {
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private void playByName() {
        // Create a File object
        File directory = new File(this.dirPath.toString());

        // Get the list of all files and directories in the specified directory
        File[] fileList = directory.listFiles();

        // Check if the directory is not empty
        if (fileList == null) {
            System.out.println("The directory is empty.");
            return;
        }

        // Loop through the files and directories and print their names
        for (File file : fileList) {
            // Print only files, not directories
            if (file.isFile() && this.supportedFileTypes.contains(Util.getFileExtension(file))) {
                System.out.println(file.getName());
            }
        }

        System.out.println("Choose a file: ");
        String fileName = this.scanner.nextLine();
        Path filePath = this.dirPath.resolve(fileName);

        if (Files.exists(filePath)) {
            try {
                Desktop.getDesktop().open(filePath.toFile());
                int fileLen = videoLengths.get(fileName);
                if (fileLen == 0) {
                    System.out.println(fileName + "is now playing for unknown amount of time.");
                }
                else {
                    System.out.println(fileName + "is now playing for " + fileLen + " seconds.");
                }
            } catch (IOException e) {
                System.err.println("Failed to play the file: " + e.getMessage());
            }
        } else {
            System.out.println("File not found: " + fileName);
        }
    }

    private void playAll() {
        File directory = new File(this.dirPath.toString());

        // Get the list of all files and directories in the specified directory
        File[] fileList = directory.listFiles();

        // Check if the directory is not empty
        if (fileList == null) {
            System.out.println("The directory is empty.");
            return;
        }

        // Loop through the files and directories and print their names
        for (File file : fileList) {
            String fileName = file.getName();
            // Print only files, not directories
            if (file.isFile()) {
                try {
                    Desktop.getDesktop().open(file);
                    int fileLen = videoLengths.get(fileName);
                    if (fileLen == 0) {
                        System.out.println(fileName + "is now playing for unknown amount of time.");
                    }
                    else {
                        System.out.println(fileName + "is now playing for " + fileLen + " seconds.");
                    }
                } catch (IOException e) {
                    System.err.println("Failed to play the file: " + e.getMessage());
                }
            }
        }
    }

}