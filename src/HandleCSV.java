import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class HandleCSV {
    public static List<String> readCSV(String filename) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(filename));
        List<String> fileLines = new ArrayList<>();
        scanner.useDelimiter("\n");

        while (scanner.hasNext()) {
            fileLines.add(scanner.next());
        }
        return fileLines;
    }

    static void appendCSV(String filename, String data) throws IOException {
        createFileIfNotExist(filename);
        Files.write(Paths.get(filename), data.getBytes(), StandardOpenOption.APPEND);
    }

    public static void clearCSV(String filename) throws FileNotFoundException {
        PrintWriter writer = new PrintWriter(filename);
        writer.close();
    }

    private static void createFileIfNotExist(String filename) throws IOException {
        File file = new File(filename);
        file.createNewFile();
    }
}
