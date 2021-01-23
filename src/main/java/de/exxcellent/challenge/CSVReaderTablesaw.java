package de.exxcellent.challenge;

import tech.tablesaw.api.Table;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;

public class CSVReaderTablesaw implements FileReader {
    private final URL filePath;

    public CSVReaderTablesaw(URL filePath) {
        this.filePath = filePath;
    }

    @Override
    public Table readFile() throws FileNotFoundException, IOException {
        Table dataTable = Table.read().csv(filePath);
        return dataTable;
    }
}
