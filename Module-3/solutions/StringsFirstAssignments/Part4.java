
/**
 * Part 4: Finding Web Links
 */

import edu.duke.*;
public class Part4 {
    
    public static void main (String[] args) {
        testFindYouTubeLinks();
    }
    
    public static void findYouTubeLinks(String url) {
        // Create a URLResource object to fetch content
        URLResource ur = new URLResource(url);
        
        // Loop through each line
        for (String line : ur.lines()) {
            // Convert line to lowercase for case-insensitive search
            String lowerCaseLine = line.toLowerCase();
            
            // Check if the line contains "youtube.com"
            int youtubeIndex = lowerCaseLine.indexOf("youtube.com");
            
            if (youtubeIndex != -1) {
                // Extract the URL from <a href="...">
                int startIndex = line.lastIndexOf("\"", youtubeIndex);
                int endIndex = line.indexOf("\"", youtubeIndex + 1);

                if (startIndex != -1 && endIndex != -1) {
                    System.out.println(line.substring(startIndex + 1, endIndex));
                }
            }
        }
    }
    
    public static void testFindYouTubeLinks() {
        // URL of the page containing links
        String url = "https://www.dukelearntoprogram.com//course2/data/manylinks.html";
        
        findYouTubeLinks(url);
    }
}
