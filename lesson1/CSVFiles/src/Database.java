import java.util.ArrayList;
import java.util.Arrays;

public class Database {
    private String[] colNames;
    private int numRows;
    private String[][] data;

    public String[] getColNames() {
        return colNames;
    }

    public void setColNames(String[] colNames) {
        this.colNames = colNames;
    }

    public int getNumRows() {
        return numRows;
    }

    public void setNumRows(int numRows) {
        this.numRows = numRows;
    }

    public String[][] getData() {
        return data;
    }

    public void setData(String[][] data) {
        this.data = data;
    }

    public Database(String contents) {
        String [] lines = contents.split("\\r?\\n");
        this.setColNames(lines[0].split(","));
        this.setNumRows(lines.length - 1);

        int rows = this.getNumRows();
        int cols = lines[0].split(",").length;

        String [][] data = new String [rows][cols];

        for (int i = 1; i < rows; i++) {
            String[] temp = lines[i].split(",");
            for (int j = 0; j < cols; j++) {
                data[i - 1][j] = temp[j];
            }
        }


        this.setData(data);
    }

    public String getValue(String columnName, int row) {
        String [][] data = this.getData();
        int col = Arrays.asList(this.getColNames()).indexOf(columnName);

        return data[row][col];
    }

}


