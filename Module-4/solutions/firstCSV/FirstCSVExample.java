package firstCSV;

/**
 * ------------ Parsing Export Data ------------
 * Reads a chosen CSV file of our preferences and prints each field.
 * @author Duke Software Team
 */
import edu.duke.*;
import org.apache.commons.csv.*;

public class FirstCSVExample {
    
    public void readFood() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        for (CSVRecord rec : parser){
            System.out.print(rec.get("Name") + " ");
            System.out.print(rec.get("Favorite Color") + " ");
            System.out.println(rec.get("Favorite Food"));
        }
    }
    
    /**
     * Tests various methods using a CSV file containing export data.
     */
    public void tester(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        
        // Uncomment the tests as needed:
        
        // Test countryInfo() - Retrieve information for a specific country
        // System.out.println(countryInfo(parser, "Germany"));
        // System.out.println(countryInfo(parser, "Nauru"));
        
        // Test listExportersTwoProducts() - Find countries that export both specified products
        // listExportersTwoProducts(parser, "cotton", "flowers");
        
        // Test numberOfExporters() - Count the number of countries exporting a specific product
        // System.out.println("Number of countries that export cocoa: " + numberOfExporters(parser, "cocoa"));

        // Test bigExporters() - List countries with exports exceeding a certain value
        bigExporters(parser, "$999,999,999,999");
        
        // If additional parsing is needed after consuming the parser, create a new one
        // parser = fr.getCSVParser();
    }
    
    /**
     * Retrieves information about a specific country from a CSV file.
     */
    public String countryInfo(CSVParser parser, String country){
        for (CSVRecord rec : parser){
            // Check if the current record matches the given country
            if (rec.get("Country").equalsIgnoreCase(country)){ // Case-insensitive match
                return String.format("%s: %s: %s", 
                    rec.get("Country"), rec.get("Exports"), rec.get("Value (dollars)"));
            }
        }
        return "NOT FOUND";
    }
    
    /**
     * Prints the names of countries that export both specified products.
     */
    public void listExportersTwoProducts(CSVParser parser, String exportItem1, String exportItem2){
        for (CSVRecord rec : parser){
            String exports = rec.get("Exports");
            
            // Check if both export items are present in the "Exports" field
            if (exports.contains(exportItem1) && exports.contains(exportItem2)){
                System.out.println(rec.get("Country"));
            }
        }
    }
    
    /**
     * Counts the number of countries that export a specified product.
     */
    public int numberOfExporters(CSVParser parser, String exportItem){
        int count = 0;
        
        for (CSVRecord record : parser){
            if(record.get("Exports").contains(exportItem)){
                count++;
            }
        }
        return count;
    }
    
    public void bigExporters(CSVParser parser, String amount){
        for (CSVRecord rec : parser){
            String value = rec.get("Value (dollars)");
            if(value.length() > amount.length()){
                System.out.println(rec.get("Country") + " " + value);
            }
        }
    }
}