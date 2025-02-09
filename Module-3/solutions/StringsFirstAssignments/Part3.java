
/**
 * Part 3: Problem Solving with Strings
 */
public class Part3 {
    
    public static void main (String[] args) {
        testing();
    }

    // Checks if s1 appears at least twice in s2
    public static boolean twoOccurences(String s1, String s2) {
        int first = s2.indexOf(s1);
        if (first == -1) return false; // Not found

        int second = s2.indexOf(s1, first + 1); // Search after first occurrence
        return second != -1;
    }
    
    // Returns the part of s2 that comes after s1's first occurrence
    public static String lastPart(String s1, String s2) {
        int index = s2.indexOf(s1);
        return (index == -1) ? s2 : s2.substring(index + s1.length());
    }
    
    public static void testing() {
        // Testing twoOccurences method
        System.out.println("Test twoOccurences:");
        System.out.printf("Does \"by\" appear at least twice in \"A story by Abby Long\"? %b%n", twoOccurences("by", "A story by Abby Long"));
        System.out.printf("Does \"a\" appear at least twice in \"banana\"? %b%n", twoOccurences("a", "banana"));
        System.out.printf("Does \"atg\" appear at least twice in \"ctgtatgta\"? %b%n", twoOccurences("atg", "ctgtatgta"));

        // Testing lastPart method
        System.out.println("\nTest lastPart:");
        System.out.printf("\"an\", \"banana\" -> \"%s\"%n", lastPart("an", "banana"));
        System.out.printf("\"zoo\", \"forest\" -> \"%s\"%n", lastPart("zoo", "forest"));
    }
}
