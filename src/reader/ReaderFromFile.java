package reader;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ReaderFromFile {

    // the flag uses for correct output of warning messages
    private boolean isFileExists = true;

    private Logger logger = Logger.getLogger(ReaderFromFile.class.getName());

    /**
     * The method reads information from input file.
     * @param fileName The name of file, which have been written information to.
     * @return The list with all information from input file.
     * */
    public List<String> readFromFile(String fileName) {
        List<String> dataFromFile = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(
                        new FileInputStream(fileName), StandardCharsets.UTF_8))) {
            String line;

            while ((line = reader.readLine()) != null)
                dataFromFile.add(line);

        } catch (FileNotFoundException e) {
            logger.log(Level.WARNING, "File " + fileName + " not found.");
            isFileExists = false;
        } catch (IOException e) {
            logger.log(Level.WARNING, "Cannot read the data correctly.");
        }

        if (isFileExists && dataFromFile.isEmpty())
            logger.log(Level.WARNING, "File " + fileName + " is empty.");

        return dataFromFile;
    }
}
