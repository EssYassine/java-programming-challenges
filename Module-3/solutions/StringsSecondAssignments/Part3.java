import edu.duke.*;
/**
 * Part 3: How Many Genes?
 */
public class Part3 {
    
    public static void main (String[] args){
        testCountGenes();
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
    
    public static int countGenes(String dna){
        int occ = 0, startIndex = 0;
        String dnaStrand = dna;
        
        while (startIndex < dna.length()) {
            String gene = findGene(dna.substring(startIndex)); // Find gene in remaining DNA
            if (gene.isEmpty()) {
            break;
            }
            
            occ++;
            startIndex = dna.indexOf(gene, startIndex) + gene.length(); // Move past the found gene
        }
        return occ;
    }
    
    public static void testCountGenes(){
        String dna = "ATGTAAGATGCCCTAGT";
        System.out.printf("%d gene(s) were found in the DNA strand \"%s\".%n", countGenes(dna), dna);
    
        dna = "GATGTAAGATGCCCTAGTGGTGATGGAGTTGTGA";
        System.out.printf("%d gene(s) were found in the DNA strand \"%s\".%n", countGenes(dna), dna);
    }
}
