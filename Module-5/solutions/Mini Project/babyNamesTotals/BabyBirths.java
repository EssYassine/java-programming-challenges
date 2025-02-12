/**
 * Print out total number of babies born, as well as for each gender, in a given CSV file of baby name data.
 * 
 * @author Duke Software Team 
 */
import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.File;

public class BabyBirths {
    public void printNames () {
        FileResource fr = new FileResource();
        for (CSVRecord rec : fr.getCSVParser(false)) {
            int numBorn = Integer.parseInt(rec.get(2));
            if (numBorn <= 100) {
                System.out.println("Name " + rec.get(0) +
                           " Gender " + rec.get(1) +
                           " Num Born " + rec.get(2));
            }
        }
    }

    public void totalBirths (FileResource fr) {
        int totalBirths = 0;
        int totalBoys = 0;
        int totalGirls = 0;
        for (CSVRecord rec : fr.getCSVParser(false)) {
            int numBorn = Integer.parseInt(rec.get(2));
            totalBirths += numBorn;
            if (rec.get(1).equals("M")) {
                totalBoys += numBorn;
            }
            else {
                totalGirls += numBorn;
            }
        }
        System.out.println("total births = " + totalBirths);
        System.out.println("female girls = " + totalGirls);
        System.out.println("male boys = " + totalBoys);
    }

    public void testTotalBirths () {
        FileResource fr = new FileResource();
        //FileResource fr = new FileResource("data/yob2014.csv");
        totalBirths(fr);
    }
    
    /**
     * Gets the rank of a given name for a specific year and gender.
     */
    public int getRank(int year, String name, String gender){
        String fileName = String.format("data/yob%d.csv", year);
        FileResource fr = new FileResource(fileName);
        
        int rank = 1; // Rank starts at 1
        for (CSVRecord rec : fr.getCSVParser(false)) {
            if (rec.get(1).equals(gender)) { // Check gender match
                if (rec.get(0).equals(name)) {
                    return rank; // Found the name, return rank
                }
                rank++; // Increase rank for next name
            }
        }
        return -1; // Name not found
    }
    
    public void testGetRank(){
        int rank = getRank(1880, "Eli", "M");
        rank = getRank(1960, "Emily", "F");
        rank = getRank(1971, "Frank", "M");
        
        if(rank != -1) {
            //System.out.println("Rank of Eli in 1880: " + rank);
            //System.out.println("Rank of Emily in 1960: " + rank);
            System.out.println("Rank of Frank in 1971: " + rank);
        } else {
            System.out.println("No entry matches the given criteria!");
        }
    }
    
    /**
     * Gets the name at a given rank for a specific year and gender.
     */
    public String getName(int year, int rank, String gender) {
        String fileName = String.format("data/yob%d.csv", year);
        FileResource fr = new FileResource(fileName);
        
        int currentRank = 0;
        for (CSVRecord rec : fr.getCSVParser(false)) {
            if (rec.get(1).equals(gender)) { // Match gender
                currentRank++; // Increase rank count
                if (currentRank == rank) {
                    return rec.get(0); // Return name at requested rank
                }
            }
        }
        return "NO NAME"; // If rank is out of bounds
    }
    
    public void testGetName() {
        String name = getName(1880, 177, "M");
        name = getName(1980, 350, "F");
        name = getName(1982, 450, "M");
        
        if(!name.equals("NO NAME")) {
            //System.out.println("The boy’s name of rank 177 in 1880: " + name);
            //System.out.println("The girl’s name of rank 350 in 1980: " + name);
            System.out.println("The boy’s name of rank 450 in 1982: " + name);
        } else {
            System.out.println("No entry matches the given criteria!");
        }
    }
    
    public String whatIsNameInYear(String name, int year, int newYear, String gender) {
        int rank = getRank(year, name, gender);
    
        if (rank == -1) {
            return String.format("Name %s not found in year %d!", name, year);
        }
        
        String newName = getName(newYear, rank, gender);
        
        if(!newName.equals("NO NAME")) {
            return String.format("%s born in %d would be %s if %s was born in %d.",
                name, year, newName, gender.equals("F") ? "she" : "he", newYear);
        } else {
            return String.format("No entry matches for year %d!", newYear);
        }
    }
    
    public void testWhatIsNameInYear() {
        System.out.println(whatIsNameInYear("Owen", 1974, 2014, "M"));
        //System.out.println(whatIsNameInYear("Susan", 1972, 2014, "F"));
        //System.out.println(whatIsNameInYear("Isabella", 2012, 2014, "F"));
        //System.out.println(whatIsNameInYear("Michael", 1980, 2000, "M"));
        //System.out.println(whatIsNameInYear("Rando", 2012, 2014, "M")); // Name doesn't exist
    }
    
    /**
     * Retrieve the year with the highest rank for the given name and gender
     */
    public int yearOfHighestRank(String name, String gender) {
        int highestYear = -1; // Track the year with the highest rank
        int highestRank = Integer.MAX_VALUE; // Start with a very high rank (worse rank)

        DirectoryResource dr = new DirectoryResource();
        
        // iterate over files
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            String fileName = f.getName();
            
            // Extract year from filename
            int currentYear = Integer.parseInt(
                fileName.substring(fileName.indexOf("b") + 1, 
                fileName.indexOf("."))
                );
            
            int currRank = getRank(currentYear, name, gender);
            
            // If rank is valid and better than the highest rank found so far
            if (currRank != -1 && currRank < highestRank) {
                highestRank = currRank;
                highestYear = currentYear;
            }
        }
        return highestYear;
    }
    
    public void testYearOfHighestRank(){
        int year = yearOfHighestRank("Mason", "M");
        year = yearOfHighestRank("Genevieve", "F");
        year = yearOfHighestRank("Mich", "M");
        if(year != -1) {
            //System.out.println("Mason had the highest rank in " + year);
            //System.out.println("Genevieve had the highest rank in " + year);
            System.out.println("Mich had the highest rank in " + year);
        } else {
            System.out.println("Name not found in any file!");
        }
    }
    
    public double getAverageRank(String name, String gender){
        double sum = 0;
        int total = 0;
        
        DirectoryResource dr = new DirectoryResource();
        
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            String fileName = f.getName();
            

            int year = Integer.parseInt(
                fileName.substring(fileName.indexOf("b") + 1, 
                fileName.indexOf("."))
                );
            
            int rank = getRank(year, name, gender);
            if(rank != -1) { // Only add valid ranks
                sum += rank;
                total++;
            }
        }
        return (total > 0) ? sum / total : -1.0;
    }
    
    public void testGetAverageRank(){
        double avgRank = getAverageRank("Mason", "M");
        //avgRank = getAverageRank("Jacob", "M");
        //avgRank = getAverageRank("Susan", "F");
        avgRank = getAverageRank("Robert", "M");
        if(avgRank != -1) {
            //System.out.println("Mason had an average ranking of " + avgRank);
            //System.out.println("Jacob had an average ranking of " + avgRank);
            //System.out.println("Susan had an average ranking of " + avgRank);
            System.out.println("Robert had an average ranking of " + avgRank);
        } else {
            System.out.println("Name not found in any file!");
        }
    }
    
    public int getTotalBirthsRankedHigher(int year, String name, String gender){
        String fileName = String.format("data/yob%d.csv", year);
        FileResource fr = new FileResource(fileName);
        
        int totalBirths = 0;
        
        for (CSVRecord rec : fr.getCSVParser(false)) {
            if (rec.get(1).equals(gender)) {
                if (rec.get(0).equals(name)){
                    return totalBirths;
                }
                totalBirths += Integer.parseInt(rec.get(2)); // Sum births of higher-ranked names
            }
        }
        return 0; // Return 0 if name not found
    }
    
    public void testGetTotalBirthsRankedHigher(){
        int num = getTotalBirthsRankedHigher(2012, "Ethan", "M");
        num = getTotalBirthsRankedHigher(1990, "Emily", "F");
        num = getTotalBirthsRankedHigher(1990, "Drew", "M");
        if (num != 0) {
            //System.out.println("Total births ranked higher than Ethan: " + num);
            //System.out.println("Total births ranked higher than Emily: " + num);
            System.out.println("Total births ranked higher than Drew: " + num);
        } else {
            System.out.println("The name not found in the file!");
        }
    }
}
