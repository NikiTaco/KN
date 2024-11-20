import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public class FileBackup {

    public static void backupFiles(String srcDir) throws IOException {
        File sourceDir = new File(srcDir);
        File backupDir = new File(srcDir + "/backup");

        if (!backupDir.exists()) {
            backupDir.mkdir();
        }

        File[] files = sourceDir.listFiles();

        if (files != null) {
            for (File file : files) {
                if (file.isFile()) {
                    Path sourcePath = file.toPath();
                    Path destinationPath = backupDir.toPath().resolve(file.getName());
                    Files.copy(sourcePath, destinationPath, StandardCopyOption.REPLACE_EXISTING);
                }
            }
        }

        System.out.println("Backup completed successfully.");
    }

    public static void main(String[] args) {
        try {
            backupFiles("./");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
