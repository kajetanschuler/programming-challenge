package de.exxcellent.challenge;

import tech.tablesaw.api.Table;
import java.io.IOException;
import java.net.URL;

/**
 * Class to handle CSV files as input
 */
public class CSVReaderTablesaw implements FileReader {

    /**
     * Reads the specified .csv file and returns Table object
     * @param filePath URL to the file that should be read
     * @return Returns a Table Object for further processing
     * @throws IOException Throws Excpetion if file cannot be read or accessed
     */
    @Override
    public Table readFile(URL filePath) throws IOException {
        return Table.read().csv(filePath);
    }
}
