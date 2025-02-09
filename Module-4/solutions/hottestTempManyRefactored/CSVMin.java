 

import edu.duke.*;
import org.apache.commons.csv.*;
import java.lang.reflect.Field;
import java.io.File;

public class CSVMin {
    
    /**
     * Returns the CSVRecord with the coldest valid temperature.
     */
    public CSVRecord coldestHourInFile(CSVParser parser) {
        CSVRecord lowestSoFar = null;
        
        for (CSVRecord currentRow : parser) {
            lowestSoFar = getLowestOfTwo(currentRow, lowestSoFar);
        }
        
        return lowestSoFar;
    }
    
    
    /**
     * Helper method to compare two records and return 
     * the one with the lower temperature.
     */
    private CSVRecord getLowestOfTwo(CSVRecord currentRow, CSVRecord lowestSoFar) {
        // Extract the temperature value, handling potential missing or invalid data.
        String tempStr = currentRow.get("TemperatureF");
        if (tempStr.equals("-9999")) {
            return lowestSoFar; // Ignore invalid readings
        }
        
        double currentTemp = Double.parseDouble(tempStr);
        
        if (lowestSoFar == null) {
            return currentRow; // Initialize lowestSoFar with the first valid record
        }
        
        double lowestTemp = Double.parseDouble(lowestSoFar.get("TemperatureF"));

        return (currentTemp < lowestTemp) ? currentRow : lowestSoFar;
    }

    public void testColdestHourInFile() {
        FileResource fr = new FileResource();
        CSVRecord lowest = coldestHourInFile(fr.getCSVParser());
        
        if (lowest != null) {
            System.out.println("Coldest temperature was " + lowest.get("TemperatureF") +
                    " at " + lowest.get("DateUTC"));
        } else {
            System.out.println("No valid temperature data found.");
        }
    }

    /**
     * Finds the file with the coldest temperature among multiple files.
     */
    public String fileWithColdestTemperature() {
        CSVRecord lowestSoFar = null;
        File fileWithColdest = null;
        DirectoryResource dr = new DirectoryResource();
        
        // Iterate over selected files
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            CSVRecord currentRow = coldestHourInFile(fr.getCSVParser());
            
            // Check and update the lowest temperature and corresponding file
            if (lowestSoFar == null || getLowestOfTwo(currentRow, lowestSoFar) == currentRow) {
                lowestSoFar = currentRow;
                fileWithColdest = f;
            }
        }
        // Return the absolute path of the file with the coldest temperature
        return (fileWithColdest != null) ? fileWithColdest.getAbsolutePath() : "";
    }
    
    public void testFileWithColdestTemperature(){
        String filePath = fileWithColdestTemperature();
        
        if (filePath.isEmpty()) {
            System.out.println("No valid temperature data found.");
            return;
        }
        
        String fileName = new File(filePath).getName();
        FileResource fr = new FileResource(filePath);
        CSVRecord coldestDay = coldestHourInFile(fr.getCSVParser());
        
        System.out.printf("""
            Coldest day was in file %s
            Coldest temperature on that day was %s°F
            All the temperatures on the coldest day were:
            """, fileName, coldestDay.get("TemperatureF"));
            
        for (CSVRecord record : fr.getCSVParser()) {
            System.out.println(record.get("DateUTC") + " " + record.get("TemperatureF"));
        }
    }

    /**
     * Finds the record with the lowest humidity in a given file.
     */
    public CSVRecord lowestHumidityInFile(CSVParser parser){
        CSVRecord lowestSoFar = null;
        
        for (CSVRecord currentRow : parser) {
            lowestSoFar = getLowestHumidity(currentRow, lowestSoFar);
        }

        return lowestSoFar;
    }
    
    public CSVRecord getLowestHumidity(CSVRecord currentRow, CSVRecord lowestSoFar) {
        String currentHumidityStr = currentRow.get("Humidity");

        // Ignore invalid humidity values
        if (currentHumidityStr.equals("N/A")) {
            return lowestSoFar;
        }
        
        double currentHumidity = Double.parseDouble(currentHumidityStr);

        if (lowestSoFar == null) {
            return currentRow;
        }
        
        String lowestHumidityStr = lowestSoFar.get("Humidity");
        if (lowestHumidityStr.equals("N/A")) {
            return currentRow; // If previous lowest was invalid, take the new valid one
        }
        
        double lowestHumidity = Double.parseDouble(lowestHumidityStr);

        return (currentHumidity < lowestHumidity) ? currentRow : lowestSoFar;
    }
    
    public void testLowestHumidityInFile() {
        FileResource fr = new FileResource();
        CSVRecord rec = lowestHumidityInFile(fr.getCSVParser());
        
        if (rec != null) {
            System.out.println("Lowest Humidity was " + rec.get("Humidity") +
                               " at " + rec.get("DateUTC"));
        } else {
            System.out.println("No valid humidity data found.");
        }
    }
    
    /**
     * Finds the record with the lowest humidity across multiple files.
     */
    public CSVRecord lowestHumidityInManyFiles () {
        CSVRecord lowestSoFar = null;
        DirectoryResource dr = new DirectoryResource();
        
        // Iterate over selected files
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            CSVRecord currentRow = lowestHumidityInFile(fr.getCSVParser());
            lowestSoFar = getLowestHumidity(currentRow, lowestSoFar);
        }
        
        return lowestSoFar;
    }
    
    public void testLowestHumidityInManyFiles(){
        CSVRecord rec = lowestHumidityInManyFiles();
        
        if (rec != null) {
            System.out.println("Lowest Humidity was " + rec.get("Humidity") +
                               " at " + rec.get("DateUTC"));
        } else {
            System.out.println("No valid humidity data found in the selected files.");
        }
    }
    
    /**
     * Calculates the average temperature from a CSV file.
     */
    public double averageTemperatureInFile(CSVParser parser){
        double sum = 0.0;
        int total = 0;
        
        for (CSVRecord currentRow : parser) {
            String tempStr = currentRow.get("TemperatureF");
            
            if (!tempStr.equals("N/A")) {
                double temp = Double.parseDouble(tempStr);
                if (temp != -9999) {  // Ignore invalid sentinel values
                    sum += temp;
                    total++;
                }
            }
        }
        return (total > 0) ? (sum / total) : Double.NaN;
    }
    
    public void testAverageTemperatureInFile() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        double avgTemp = averageTemperatureInFile(parser);
        
        if (Double.isNaN(avgTemp)) {
            System.out.println("No valid temperature data found in the file.");
        } else {
            System.out.println("Average temperature in file is " + avgTemp);
        }
    }
    
    /**
     * Calculates the average temperature when humidity is above a threshold.
     */
    public double averageTemperatureWithHighHumidityInFile(CSVParser parser, int value){
        double sum = 0.0;
        int total = 0;
        
        for (CSVRecord currentRow : parser) {
            String humidityStr = currentRow.get("Humidity");
            String tempStr = currentRow.get("TemperatureF");
            
            if (!humidityStr.equals("N/A") && !tempStr.equals("N/A")) {
                int humidity = Integer.parseInt(humidityStr);
                double temp = Double.parseDouble(tempStr);
                
                if (humidity >= value && temp != -9999) {  // Ignore invalid data
                    sum += temp;
                    total++;
                }
            }
        }
        return (total > 0) ? (sum / total) : Double.NaN;
    }
    
    public void testAverageTemperatureWithHighHumidityInFile() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        double avg = averageTemperatureWithHighHumidityInFile(parser, 80);

        if (Double.isNaN(avg)) {
            System.out.println("No temperatures found with that humidity level.");
        } else {
            System.out.println("Average temperature in file for humidity ? 80: " + avg);
        }
    }
}
