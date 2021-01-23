package de.exxcellent.challenge;

import tech.tablesaw.api.*;

/**
 * Class for analyzing tabular data. For this challenge, only the spread calculation is implemented.
 * Takes a Table as input.
 *
 * Info for the reviewers: My goal was to handle both spread calculations with one function. I had in mind, that this
 * class is used for multiple different calculations and therefore needs to be functional regardless of the used data.
 * In the future constructor overloading can be used to make different calculations available.
 */
public class DataAnalyzer {

    /**
     * Data that needs to be analyzed
     */
    private Table data;

    /**
     * Name of the column that has the minimum values for calculation
     */
    private String minCol;

    /**
     * Name of the column that has the maximum values for calculation
     */
    private String maxCol;

    /**
     * Name of the column with the label that needs to be returned
     */
    private String labelCol;

    /**
     * @param data Table that needs to be analyzed
     * @param minCol Name of the column with minimum values
     * @param maxCol Name of the column with maximum values
     * @param labelCol Name of the column with label that should be returned
     */
    public DataAnalyzer(Table data, String minCol, String maxCol, String labelCol) {
        this.data = data;
        this.minCol = minCol;
        this.maxCol = maxCol;
        this.labelCol = labelCol;
    }

    /**
     * Calculates the spread of two columns and returns the associated label.
     * @return Returns String of label with minimal spread
     */
    public String calculateMinimalSpread() {
        // Select subset of data that is needed for calculation
        Table selectedData = data.select(labelCol, minCol, maxCol);

        // Convert integer columns to double columns for later calculations
        DoubleColumn minColumn = selectedData.intColumn(minCol).asDoubleColumn();
        DoubleColumn maxColumn = selectedData.intColumn(maxCol).asDoubleColumn();

        // Calculate spread with tablesaw columnar function "subtract".
        // The absolute spread is used to fullfill the requirements for the footbal data
        DoubleColumn spread = maxColumn.subtract(minColumn).abs();

        // Add spread column to Table
        selectedData.addColumns(spread);

        // Get the row index of the minimal spread in the spread column
        int minIndex = spread.indexOf(spread.min());

        // Preparations for the label extraction
        ColumnType[] columnTypes = selectedData.columnTypes();
        StringColumn labelColumn;
        String label = "";

        // If the label column is an Integer it is converted to a String and the label is fetched
        if (columnTypes[0].name().equals("INTEGER")) {
            labelColumn = selectedData.intColumn(labelCol).asStringColumn();
            label = labelColumn.get(minIndex);
        }

        // If the label column is a String the label is directly fetched without conversion
        if (columnTypes[0].name().equals("STRING")){
            labelColumn = selectedData.stringColumn(labelCol);
            label = labelColumn.get(minIndex);
        }

        return label;

    }
}
