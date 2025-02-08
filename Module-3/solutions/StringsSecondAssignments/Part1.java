import edu.duke.*;
/**
 * Part 1: Finding many Genes 
 */
public class Part1 {
    
    public static void main (String[] args){
        printAllGenes("GATGTAAGATGCCCTAGTGGTGATGGAGTTGTGA");
    }
    
    public static int findStopCodon(String dna, int startIndex, String stopCodon) {
        for (int i = startIndex + 3; i <= dna.length() - 3; i += 3) {
            if (dna.substring(i, i + 3).equals(stopCodon)) {
                return i;
            }
        }
        return dna.length(); // If no valid stop codon is found, return dna length
    }
    
    public static void testFindStopCodon(){
        String dna = "ATGTGGAGGTAGTTATAA";
        System.out.println(findStopCodon(dna, 0, "TAA"));
    }
    
    public static String findGene(String dna){
        int startIndex = dna.indexOf("ATG");
        if (startIndex == -1) return ""; // No start codon
        
        int taaIndex = findStopCodon(dna, startIndex, "TAA");
        int tagIndex = findStopCodon(dna, startIndex, "TAG");
        int tgaIndex = findStopCodon(dna, startIndex, "TGA");
        
        // Find the smallest valid stop codon index
        int endIndex = Math.min(taaIndex, Math.min(tagIndex, tgaIndex));
        if (endIndex < dna.length()) {
            return dna.substring(startIndex, endIndex + 3);
        }
        return ""; // No valid stop codon
    }
    
    public static void testFindGene(){
        String[] testCases = {
            "AGTAGGTATG",   // No stop codon
            "GATGGTGTAG",   // Stop codon TAG
            "GTATGAATATTTAATGGTGA", // Multiple genes
            "GTATGTGGAGT",  // No stop codon
            "AATGCTAACTAGCTGACTAAT" // Gene present
        };
        
        for (String dna : testCases) {
            System.out.println("DNA: " + dna + " -> Gene: " + findGene(dna));
        }
    }
    
    public static void printAllGenes(String dna){
        int startIndex = 0;
        System.out.println("DNA: " + dna);
        
        while(true){
            String gene = findGene(dna.substring(startIndex));
            
            if (gene.isEmpty()) break;
            
            System.out.println("Gene : " + gene);
            
            // Move index forward to avoid infinite loop
            startIndex = dna.indexOf(gene, startIndex) + gene.length();
        }
    }
}
