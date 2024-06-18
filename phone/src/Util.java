package phone.src;

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
}
