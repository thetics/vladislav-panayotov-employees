import java.io.*;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class commandInterpreter {
    private static final String PATH = "C:\\Users\\panai\\OneDrive\\Desktop\\";


    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader readFromFile = null;
        BufferedWriter writeToFile = null;

        String input = "";

        Map<String, String> setCommandArguments = new HashMap<>();

        while (!(input = reader.readLine()).equals("exit")) {
            String[] data = input.split("\\s+");
            String cmd = data[0];

            switch (cmd.toLowerCase(Locale.ROOT)) {
                case "set":
                    System.out.println(String.format("Saved %s = %s", data[1], data[2]));
                    break;
                case "get":
                    if (setCommandArguments.containsKey(data[1])) {
                        System.out.println(String.format("%s = %s", data[1], setCommandArguments.get(data[1])));
                    } else {
                        System.out.println(String.format("Err: no value for %s", data[1]));
                    }
                    break;
                case "load":
                    try {
                        readFromFile = new BufferedReader(new FileReader(PATH + data[1]));
                        System.out.println(String.format("Data from %s is loaded", data[1]));
                    } catch (FileNotFoundException fnfex) {
                        System.out.println(fnfex.getMessage() + "The file was not found.");
                    }
                    try {
                        String line;
                        while ((line = readFromFile.readLine()) != null) {
                            String dataFromFile[] = line.split("=");

                            setCommandArguments.put(dataFromFile[0], dataFromFile[1]);
                            System.out.println(String.format("Saved %s = %s", dataFromFile[0], dataFromFile[1]));
                        }
                    } catch (IOException ioex) {
                        System.out.println(ioex.getMessage() + "Error reading file.");
                    } finally {
                        readFromFile.close();
                    }

                    break;
                case "save":
                    try {
                        writeToFile = new BufferedWriter(new FileWriter
                                (PATH + data[1]));

                        for (Map.Entry<String, String> entry : setCommandArguments.entrySet()) {
                            writeToFile.write(String.format("%s=%s\n", entry.getKey(), entry.getValue()));
                        }
                        writeToFile.close();
                        System.out.println(String.format("Data exported to %s", data[1]));

                    } catch (Exception ex) {
                        System.out.println(ex);
                    } finally {
                        writeToFile.close();
                    }
                    break;
                case "exit":
                    return;
                case "reverse":
                    StringBuilder sb = new StringBuilder();
                    for (int i = 1; i < data.length; i++) {
                        sb.append(data[i] + " ");
                    }

                    System.out.println(sb.reverse());
                    break;
                case "count-words":
                    try {
                        readFromFile = new BufferedReader(new FileReader(PATH + data[1]));

                    } catch (FileNotFoundException fnfex) {
                        System.out.println(fnfex.getMessage() + "The file was not found.");
                    } finally {
                        readFromFile.close();
                    }
                    int wordCount = 0;
                    try {
                        String line;

                        while ((line = readFromFile.readLine()) != null) {
                            String dataFromFile[] = line.split("\\s+");

                            wordCount += dataFromFile.length;
                        }
                    } catch (IOException ioex) {
                        System.out.println(ioex.getMessage() + "Error reading file.");
                    }
                    System.out.println(String.format("Words in %s: %s", data[1], wordCount));
                    break;
                default:
                    System.out.println("Wrong command, please try entering it again");
                    break;
            }
        }

    }
}
