package FileIO;
import java.io.*;

import javax.swing.JOptionPane;

public class FileInfoController {
    private final String fileName;
    private String[][] data;

    public FileInfoController(String fileName) {
        this.fileName = fileName;
        readFile();
    }

    //method that returns the first row val of a particular column
    private void readFile() {
        try {
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            int numRows = (int) br.lines().count();
            br.close();
            br = new BufferedReader(new FileReader(fileName));
            String[] line = br.readLine().split(",");
            int numCols = line.length;
            data = new String[numRows + 1][numCols];
            data[0] = line;

            for (int i = 1; i < numRows; i++) {
                line = br.readLine().split(",");
                data[i] = line;
            }
            br.close();


        } catch (FileNotFoundException e) {
            displayErrorMessage("File not found");
        } catch (IOException e) {
            displayErrorMessage("IO Error");
        }


    }

    public void writeFile() {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(fileName));
            for (String[] row: data) {
                bw.write(String.join(",", row));
                bw.newLine();
            }
            bw.close();
        } catch (FileNotFoundException e) {
            displayErrorMessage("File not found");
        } catch (IOException e) {
            displayErrorMessage("IO Error");
        }
    }


    public void updateData(int rowNum, int colNum, String newVal) {
        if (rowNum < data.length && colNum <= data[rowNum].length) {
            //check to make sure that the indexes is within bounds
            data[rowNum][colNum - 1] = newVal;
        }
    }

    public int getRowNum() {
        return data.length - 1;
    }

    public String getData(int rowNum, int colNum) {

        if (rowNum < data.length && colNum <= data[rowNum].length) {
            return data[rowNum][colNum - 1];
        } else {
            return "";
        }
    }

    public void displayErrorMessage(String msg) {
        JOptionPane.showMessageDialog(
                null, msg, "Error", JOptionPane.ERROR_MESSAGE);
    }

    public void displayData() {
        for (String[] row: data) {
            System.out.println(String.join(",", row));
        }
    }

    public void reset() {
        String[][] clearedData = new String[2][data[0].length];
        clearedData[0] = data[0];
        clearedData[1] = new String[] {"1000", "0", "0"};
        data = clearedData;
        writeFile();
    }





}
