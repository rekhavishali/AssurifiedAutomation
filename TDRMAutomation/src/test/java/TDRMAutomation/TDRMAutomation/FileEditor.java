package TDRMAutomation.TDRMAutomation;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileEditor {

    public static void main(String[] args) {
        String inputFile = "C:\\Users\\RekhaVishali\\OneDrive - Bourntec Solutions Pvt Ltd\\Desktop\\input.TXT"; // Replace with your input file path
        String outputFile = "C:\\Users\\RekhaVishali\\OneDrive - Bourntec Solutions Pvt Ltd\\Desktop\\Output.TXT"; // Replace with your output file path

        try {
            // Read the content of the input file
            String content = readFile(inputFile);

            // Make edits to the content (replace "oldText" with your desired modification)
            String modifiedContent = content.replace("com.example", "TDRMAutomation.TDRMAutomation");
            String modifiedContent1 = content.replace("public class FailureTestcase", "public class LoginFailure extends TestBase");

            // Write the modified content to the output file
            writeFile(outputFile, modifiedContent);
            writeFile(outputFile, modifiedContent1);

            System.out.println("File editing completed successfully.");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String readFile(String filePath) throws IOException {
        StringBuilder content = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
        }
        return content.toString();
    }

    private static void writeFile(String filePath, String content) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write(content);
        }
    }
}
