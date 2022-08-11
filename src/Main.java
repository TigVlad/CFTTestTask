import reader.ReaderFromFile;
import sorter.IntegerMergeSorter;
import sorter.StringMergeSorter;
import writer.WriterToFile;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.IntStream;

public class Main {

    // flag for checking if the first parameter is indicated explicitly
    private static boolean isFirstParameterSet = true;
    // boolean is true if ascending sorting order is set, and false if descending sorting order is set
    private static boolean sortMode;
    // variable takes"-i" and "-s" values
    private static String dataType;
    // name of file to write the sorted information
    private static String outputFileName;
    // list with input file names in the parameters
    private static List<String> inputFileNames = new ArrayList<>();

    // list for receiving all the information from files of inputFileNames list
    private static List<String> dataFromAllFiles = new ArrayList<>();

    private static Logger logger = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {
        sortMode = ascendingOrDescendingMode(args[0]);

        assignmentOfValuesByArgs(args);

        ReaderFromFile reader = new ReaderFromFile();
        WriterToFile writer = new WriterToFile();

        for (String fileName : inputFileNames) {
            List<String> dataFromCurrentFile = reader.readFromFile(fileName);

            dataFromAllFiles.addAll(dataFromCurrentFile);
        }

        if (dataType.equals("-i")) {
            List<Integer> listOfIntsFromFiles = parseStringListToInt(dataFromAllFiles);

            // converting Integer[] array to int[] array (Java 8 magic) by lambda's
            int[] arrayOfIntsFromFiles = listOfIntsFromFiles.stream().mapToInt(i->i).toArray();

            int[] sortedIntArray = IntegerMergeSorter.mergeSort(arrayOfIntsFromFiles, sortMode);

            // converting int[] array to Integer[] array (Java 8 magic) by lambda's
            Integer[] sortedIntegerArray = IntStream.of(sortedIntArray).boxed().toArray(Integer[]::new);

            List<Integer> sortedIntList = Arrays.asList(sortedIntegerArray);

            writer.writeToFile(outputFileName, sortedIntList);
        } else {
            String[] arrayOfStringsFromFiles = dataFromAllFiles.toArray(new String[0]);

            String[] sortedStringArray = StringMergeSorter.mergeSort(arrayOfStringsFromFiles, sortMode);

            List<String> sortedStringList = Arrays.asList(sortedStringArray);

            writer.writeToFile(outputFileName, sortedStringList);

            Collections.sort(sortedStringList);
        }
    }

    /**
     * The method gets the order of sorting.
     * @param args0 The args[0] parameter from args[] array to show order of sorting.
     * @return Boolean variable is true if ascending order of sorting required
     *         and it's false if descending order of sorting required.
     * */
    public static boolean ascendingOrDescendingMode(String args0) {
        switch (args0) {
            case "-a":
                sortMode = true;
                break;
            case "-d":
                sortMode = false;
                break;
            default: // if the args[0] is neither "-a", nor "-d"
                sortMode = true;
                isFirstParameterSet = false;
        }

        return sortMode;
    }

    /**
     * The method delegates to every field one of the value from args[] array.
     * @param args The parameters of command line with pointing of ascending/descending sorting order,
     *             type of elements in file(s), name of output file and name(s) input file(s).
     * */
    public static void assignmentOfValuesByArgs(String[] args) {
        int parametersCounter = 0; // variable uses for code optimising

        if (isFirstParameterSet) parametersCounter = 1;

        dataType = args[parametersCounter];
        outputFileName = args[++parametersCounter];

        // adding the rest of parameters to inputFileNames List
        inputFileNames.addAll(Arrays.asList(args).subList(++parametersCounter, args.length));
    }

    /**
     * The method transforms String type element to int type element within the cycle.
     * @param listOfStrings List of strings from file(s).
     * @return List of int type elements from file(s).
     * */
    public static List<Integer> parseStringListToInt(List<String> listOfStrings) {
        List<Integer> listOfInts = new ArrayList<>();

        for (String stringFromListOfStrings : listOfStrings) {
            try {
                listOfInts.add(Integer.parseInt(stringFromListOfStrings));
            } catch (NumberFormatException nfe) {
                logger.log(Level.WARNING, "Cannot parse '" + stringFromListOfStrings + "' to int type.");
                logger.log(Level.WARNING, "Error in input file(s)," +
                        "int type array has different from digits sign: '" + stringFromListOfStrings + "'");
            }
        }

        return listOfInts;
    }
}
