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
import java.util.Stack;

public class HandleCSV {
    public static Stack<String> readCSV(String filename) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(filename));
        Stack<String> fileLines = new Stack<>();
        scanner.useDelimiter("\n");

        while (scanner.hasNext()) {
            fileLines.add(scanner.next());
        }
        return fileLines;
    }

    static void appendCSV(String filename, String data) throws IOException {
        if (createFileIfNotExist(filename)) {
            Files.write(Paths.get(filename), data.getBytes(), StandardOpenOption.APPEND);
        } else {
            Files.write(Paths.get(filename), data.getBytes(), StandardOpenOption.APPEND);
        }
    }

    public static void clearCSV(String filename) throws FileNotFoundException {
        PrintWriter writer = new PrintWriter(filename);
        writer.close();
    }

    private static boolean createFileIfNotExist(String filename) throws IOException {
        File file = new File(filename);
        return file.createNewFile();
    }
}
