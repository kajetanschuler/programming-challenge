package de.exxcellent.challenge;

import tech.tablesaw.api.*;

public class DataAnalyzer {

    private Table data;
    private String minCol;
    private String maxCol;
    private String labelCol;

    public DataAnalyzer(Table data, String minCol, String maxCol, String labelCol) {
        this.data = data;
        this.minCol = minCol;
        this.maxCol = maxCol;
        this.labelCol = labelCol;
    }

    public String calculateMinimalSpread() {
        Table selectedData = data.select(labelCol, minCol, maxCol);
        ColumnType[] columnTypes = selectedData.columnTypes();
        DoubleColumn minColumn = selectedData.intColumn(minCol).asDoubleColumn();
        DoubleColumn maxColumn = selectedData.intColumn(maxCol).asDoubleColumn();
        DoubleColumn spread = maxColumn.subtract(minColumn).abs();
        selectedData.addColumns(spread);
        int minIndex = spread.indexOf(spread.min());

        StringColumn labelColumn;
        String label = "";


        if (columnTypes[0].name().equals("INTEGER")) {
            labelColumn = selectedData.intColumn(labelCol).asStringColumn();
            label = labelColumn.get(minIndex);
        }

        if (columnTypes[0].name().equals("STRING")){
            labelColumn = selectedData.stringColumn(labelCol);
            label = labelColumn.get(minIndex);
        }
        return label;
    }
}
