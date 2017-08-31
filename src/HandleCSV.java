import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Scanner;
import java.util.Stack;

public class HandleCSV {
    static Stack<String> readCSV(String filename) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(filename));
        Stack<String> fileLines = new Stack<>();
        scanner.useDelimiter("\n");

        while (scanner.hasNext()) {
            fileLines.add(scanner.next().replace("\r", ""));
        }
        return fileLines;
    }

    static void appendCSVToTop(String filename, String data) throws IOException {
        createFileIfNotExist(filename);
        File mFile = new File(filename);
        FileInputStream fileInputStream = new FileInputStream(mFile);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
        StringBuilder result = new StringBuilder();
        result.append(data);
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            result.append(line).append("\n");
        }
        mFile.delete();
        FileOutputStream fos = new FileOutputStream(mFile);
        fos.write(result.toString().getBytes());
        fos.flush();
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
