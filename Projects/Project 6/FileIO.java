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
                out.println("The file could not be found. Please enter a new path or filename.");
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
            if (ex instanceof FileNotFoundException) {
                out.println("The file could not be found. Creating new file now...");
                createFile(file);
            }
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
                // return an empty list if the file did not open
                list.clear();
                return list;
            }
        } catch (Exception ex) {
            out.println("Error reading file.");
        }

        return list;
    }

    public String readAsString() {
        StringBuilder contentBldr = new StringBuilder();
        String line = null;

        try {
            if (openFileForReading()) {
                while ( (line = bufferedReader.readLine()) != null) {
                    contentBldr.append(line);
                }
                closeFileForReading();
            }
        } catch (Exception ex) {
            out.println("Error reading file");
        }

        return contentBldr.toString();
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
                closeFileForWriting();
            }
        } catch (Exception ex) {
            out.println("Error writing to file.");
        }
    }

    public void appendToFile(String strToAppend) {
        try {
            if (openFileForWriting(true)) {
                bufferedWriter.append(strToAppend);
                closeFileForWriting();
            }
        } catch (Exception ex) {
            out.println("Error appending to file.");
        }
    }

    public void overwriteToFile(String strToWrite) {
        try {
            if (openFileForWriting(false)) {
                this.bufferedWriter.write(strToWrite);
                closeFileForWriting();
            }
        } catch (Exception ex) {
            out.println("Error writing to file.");
        }
    }

    public void clearFile() {
        try {
            if (openFileForWriting(false)) {
                closeFileForWriting();
            }
        } catch (Exception ex) {
            out.println("Error writing to file.");
        }
    }

    private void closeFileForReading() {
        try {
            bufferedReader.close();
            fileReader.close();
        } catch (Exception ex) {
            out.println("Error closing the file for reading.");
        }
    }

    private void closeFileForWriting() {
        try {
            bufferedWriter.close();
            fileWriter.close();
        } catch (Exception ex) {
            out.println("Error closing the file for writing.");
        }
    }

    public File getFile() {
        return this.file;
    }

}
