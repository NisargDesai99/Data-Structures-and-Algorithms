package NisargDesai.InOut.Text;

import java.io.*;
import java.util.ArrayList;

import static java.lang.System.out;

public class FileIO {

    private File file;
    private FileReader fileReader;
    private BufferedReader bufferedReader;
    private FileWriter fileWriter;
    private BufferedWriter bufferedWriter;

    public FileIO(File file) {
        this.file = file;
    }

    private boolean openFileForReading() {
        try {
            fileReader = new FileReader(file);
            bufferedReader = new BufferedReader(fileReader);
        } catch (Exception ex) {

            if (ex instanceof FileNotFoundException) {
                out.println("The file could not be found. Creating new file now...");
                createFile(file);
            } else {
                out.println("Error opening file for reading.\n" + ex);
            }
        }

        if (fileReader != null && bufferedReader != null) {
            return true;
        }
        return false;
    }

    private boolean openFileForWriting(boolean append) {
        try {
            fileWriter = new FileWriter(file, append);
            bufferedWriter = new BufferedWriter(fileWriter);
        } catch (Exception ex) {
            out.println("Error opening file for writing.");
        }

        if (fileWriter != null && bufferedWriter != null) {
            return true;
        }
        return false;
    }

    public void createFile(File fileToCreate) {
        try {
            boolean fileCreated = fileToCreate.createNewFile();
        } catch (Exception ex) {
            out.println("Error creating file.");
        }
    }

    public ArrayList<String> readAsStringArrayList() {
        String line = null;
        ArrayList<String> list = new ArrayList<>();

        try {
            if (openFileForReading()) {
                while ( (line = bufferedReader.readLine()) != null) {
                    list.add(line);
                }
                closeFileForReading();
            } else {
                closeFileForReading();
                return null;
            }
        } catch (Exception ex) {
            out.println("Error reading file.");
        }

        return list;
    }

    public String readAsString() {
        String content = "";
        String line = null;

        try {
            if (openFileForReading()) {
                content = fileReader.getEncoding();
                out.println(content);

                closeFileForReading();
            }
        } catch (Exception ex) {
            out.println("Error reading file");
        }

        return content;
    }

    public void writeFromArrayList(ArrayList<String> list) {
        try {
            if (openFileForWriting(false)) {
                int counter = 1;
                for (String line : list) {
                    if (counter == list.size()) {
                        bufferedWriter.write(line);
                        break;
                    }
                    bufferedWriter.write(line);
                    bufferedWriter.write("\n");
                    counter++;
                }
            }
            closeFileForWriting();
        } catch (Exception ex) {
            out.println("Error writing to file.");
        }
    }

    public void appendToFile(String strToAppend) {
        try {
            fileWriter = new FileWriter(file,true);
            bufferedWriter = new BufferedWriter(fileWriter);

            bufferedWriter.append(strToAppend + "\n");

            bufferedWriter.close();
        } catch (Exception ex) {
            out.println("Error writing to file.");
        }
    }

    private void closeFileForReading() {
        try {
            fileReader.close();
            bufferedReader.close();
        } catch (Exception ex) {
            out.println("Error closing the file for reading.");
        }
    }

    private void closeFileForWriting() {
        try {
            fileWriter.close();
            bufferedWriter.close();
        } catch (Exception ex) {
            out.println("Error closing the file for writing.");
        }
    }

    public File getFile() {
        return this.file;
    }

}
