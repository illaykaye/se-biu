package phone.src;

import java.io.File;

public class Util {
    public static String getFileExtension(String filePath) {
        if (filePath == null || filePath.isEmpty()) {
            return "";
        }

        int dotIndex = filePath.lastIndexOf('.');
        if (dotIndex == -1 || dotIndex == filePath.length() - 1) {
            return "";
        }

        return filePath.substring(dotIndex + 1);
    }

    public static String getFileExtension(File file) {
        String fileName = file.getName();
        int dotIndex = fileName.lastIndexOf('.');
        if (dotIndex <= 0 || fileName.length() - 1 <= dotIndex) {
            return "";
        }
        return fileName.substring(dotIndex + 1);
    }
}
