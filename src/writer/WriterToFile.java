package writer;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class WriterToFile {

    private Logger logger = Logger.getLogger(WriterToFile.class.getName());

    /**
     * The method writes sorted information to output file.
     * @param fileName The name of file, which have been written information to.
     * @param dataToWrite Information for writing to output file.
     *                    Type parameter could be any of heirs of Object type.
     * */
    public void writeToFile(String fileName, List<?> dataToWrite) {
        try (BufferedWriter writer = new BufferedWriter(
                new OutputStreamWriter(
                        new FileOutputStream(fileName), StandardCharsets.UTF_8))) {
            for (Object elementOfData : dataToWrite) {
                writer.write(String.valueOf(elementOfData));
                writer.write("\n");
            }

            writer.flush();
        } catch (IOException e) {
            logger.log(Level.WARNING, "Cannot write the data correctly.");
        }
    }
}
