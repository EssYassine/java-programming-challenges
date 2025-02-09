import edu.duke.*;
/**
 * Part 2: HowMany - Finding Multiple Occurrences
 */
public class Part2 {
    
    public static void main(String[] args){
        testHowMany();
    }
    
    public static int howMany(String s1, String s2) {
        int occ = 0;
        int index = 0;
        while ((index = s2.indexOf(s1, index)) != -1) {
            occ++;
        index += s1.length(); // Move index forward to avoid counting overlaps
        }
        return occ;
    }
    
    public static void testHowMany(){
        String s1 = "AA", s2 = "ATAAAA";
        System.out.printf("\"%s\" occurs %d time(s) in \"%s\"%n", s1, howMany(s1,s2), s2);
        
        s1 = "GAA"; 
        s2 = "ATGAACGAATTGAATC";
        System.out.printf("\"%s\" occurs %d time(s) in \"%s\"%n", s1, howMany(s1,s2), s2);
    }
}
