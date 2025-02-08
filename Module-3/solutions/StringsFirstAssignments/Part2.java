
/**
 * Part 2: Finding a Gene - Using the Simplified Algorithm Reorganized
 */
public class Part2 {
    
    public static void main (String[] args) {
       testSimpleGene();
    }
    
    /**
     * Finds a simple gene in the given DNA sequence (with 
     * uppercase or lowercase letters).
     */
    public static String findSimpleGene (String dna, int startCodon, int endCodon) {
        if (startCodon == -1) {
            return "";
        }
        if (endCodon == -1) {
            return "";
        }
        return extractGene(dna, startCodon, endCodon);
    }
    
    /**
     * Extracts the gene sequence between start and end codons.
     */
    public static String extractGene(String dna, int startCodon, int endCodon) {
        String simpleGene = dna.substring(startCodon, endCodon + 3);
        if (simpleGene.length() % 3 == 0) {
            return simpleGene;
        }
        return "";
    }

    /**
     * Tests the updated findSimpleGene method with various DNA sequences.
     */
    public static void testSimpleGene () {
        String[] testCases = {
            "TGGAGTGAGGG",   // No ATG
            "ATGGTTGGTA",    // No TAA
            "CCGAGTGTGTGT",  // No ATG, No TAA
            "ATGGATGTAGGAGTATAA", // Valid gene
            "ATGGTAA",         // Valid but not multiple of 3
            "gatgctataat" // DNA String with lowercase letters
        };
        for (String dna : testCases) {
            int startCodon = dna.toUpperCase().indexOf("ATG");
            int endCodon = dna.toUpperCase().indexOf("TAA", startCodon + 3);
            String result = findSimpleGene(dna, startCodon, endCodon);
            System.out.println("Testing DNA sequence: " + dna);
            if (!result.isEmpty()) {
                System.out.println("Gene found: " + result);
            } else {
                System.out.println("No gene found.");
            }
        }
    }
}
