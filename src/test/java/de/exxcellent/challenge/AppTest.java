package de.exxcellent.challenge;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tech.tablesaw.api.Table;
import java.net.URL;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Example JUnit 5 test case.
 * @author Benjamin Schmid <benjamin.schmid@exxcellent.de> (Challenge Author)
 * @author Kajetan Schuler <kajetan.schuler@gmail.com> (Challenge Solution)
 */
class AppTest {

    private String successLabel = "not successful";

    @BeforeEach
    void setUp() {
        successLabel = "successful";
    }

    @Test
    void aPointlessTest() {
        assertEquals("successful", successLabel, "My expectations were not met");
    }

    @Test
    void runFootball() {
        App.main("--football", "football.csv");
    }

    @Test
    public void minimumSpreadTest() {
        Table testData = null;
        URL weatherDataPath = App.class.getResource("test.csv");
        CSVReaderTablesaw csvReader = new CSVReaderTablesaw();

        try {
            testData = csvReader.readFile(weatherDataPath);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        final DataAnalyzer analyzer = new DataAnalyzer(testData, "min", "max", "label");

        final String successLabel = analyzer.calculateMinimalSpread();
        assertEquals("yes", successLabel, "record not correct");
    }

}