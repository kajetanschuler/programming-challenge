package de.exxcellent.challenge;

import tech.tablesaw.api.Table;

import java.net.URL;

/**
 * The entry class for your solution. This class is only aimed as starting point and not intended as baseline for your software
 * design. Read: create your own classes and packages as appropriate.
 *
 * @author Benjamin Schmid <benjamin.schmid@exxcellent.de> (Challenge Author)
 * @author Kajetan Schuler <kajetan.schuler@gmail.com> (Challenge Solution)
 */
public final class App {

    private static final String WEATHER_RESOURCE_PATH = "weather.csv";
    private static final String[] WEATHER_COLUMN_LABELS =
            { "MxT", "MnT", "Day" };

    private static final String FOOTBALL_RESOURCE_PATH = "football.csv";
    private static final String[] FOOTBALL_COLUMN_LABELS =
            { "Goals", "Goals Allowed", "Team" };

    /**
     * This is the main entry method of your program.
     * @param args The CLI arguments passed
     */
    public static void main(String... args) {
        Table weatherData = null;
        Table footballData = null;
        String dayWithSmallestTempSpread;
        String teamWithSmallestGoalSpread;
        DataAnalyzer analyzer;
        
        // Your preparation code …
        URL weatherDataPath = App.class.getResource(WEATHER_RESOURCE_PATH);
        URL footballDataPath = App.class.getResource(FOOTBALL_RESOURCE_PATH);
        CSVReaderTablesaw csvReader = new CSVReaderTablesaw();

        try {
            weatherData = csvReader.readFile(weatherDataPath);
            footballData = csvReader.readFile(footballDataPath);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        analyzer = new DataAnalyzer(weatherData, WEATHER_COLUMN_LABELS[0], WEATHER_COLUMN_LABELS[1],
                WEATHER_COLUMN_LABELS[2]);
        dayWithSmallestTempSpread = analyzer.calculateMinimalSpread();
        System.out.printf("Day with smallest temperature spread : %s%n", dayWithSmallestTempSpread);

        analyzer = new DataAnalyzer(footballData, FOOTBALL_COLUMN_LABELS[0], FOOTBALL_COLUMN_LABELS[1],
                FOOTBALL_COLUMN_LABELS[2]);
        teamWithSmallestGoalSpread = analyzer.calculateMinimalSpread();
        System.out.printf("Team with smallest goal spread       : %s%n", teamWithSmallestGoalSpread);
    }
}
