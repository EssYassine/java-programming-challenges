
/**
 * Part 1: Finding a Gene - Using the Simplified Algorithm
 */
public class Part1 {
    
    public static void main (String[] args) {
       testSimpleGene();
    }
    
    /**
     * Finds a simple gene in the given DNA sequence.
     */
    public static String findSimpleGene (String dna) {
        int startCodon = dna.indexOf("ATG");
        if (startCodon == -1) {
            return "";
        }
        int endCodon = dna.indexOf("TAA", startCodon + 3);
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
     * Tests the findSimpleGene method with various DNA sequences.
     */
    public static void testSimpleGene () {
        String[] testCases = {
            "TGGAGTGAGGG",   // No ATG
            "ATGGTTGGTA",    // No TAA
            "CCGAGTGTGTGT",  // No ATG, No TAA
            "ATGGATGTAGGAGTATAA", // Valid gene
            "ATGGTAA"         // Valid but not multiple of 3
        };
        for (String dna : testCases) {
            String result = findSimpleGene(dna);
            System.out.println("Testing DNA sequence: " + dna);
            if (!result.isEmpty()) {
                System.out.println("Gene found: " + result);
            } else {
                System.out.println("No gene found.");
            }
        }
    }
}
