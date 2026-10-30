import java.util.Scanner;

public class Q06_StringOperations {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the feedback paragraph:");
        String para = sc.nextLine();

        // 1. Count characters and words
        int charCount = para.length();
        int charNoSpace = para.replace(" ", "").length();
        int wordCount = para.trim().isEmpty() ? 0 : para.trim().split("\\s+").length;

        System.out.print("Enter word to search: ");
        String search = sc.nextLine();
        System.out.print("Enter word to replace: ");
        String oldWord = sc.nextLine();
        System.out.print("Enter new word: ");
        String newWord = sc.nextLine();

        System.out.println("\n----- RESULTS -----");
        System.out.println("Total characters (with spaces)   : " + charCount);
        System.out.println("Total characters (without spaces): " + charNoSpace);
        System.out.println("Total words                      : " + wordCount);

        // 2. Upper and lower case
        System.out.println("Uppercase : " + para.toUpperCase());
        System.out.println("Lowercase : " + para.toLowerCase());

        // 3. Search
        int index = para.toLowerCase().indexOf(search.toLowerCase());
        if (index >= 0)
            System.out.println("Word \"" + search + "\" found at index " + index);
        else
            System.out.println("Word \"" + search + "\" not found");

        // 4. Replace
        System.out.println("After replacing \"" + oldWord + "\" with \"" + newWord + "\":");
        System.out.println(para.replace(oldWord, newWord));

        // 5. Reverse
        String reversed = new StringBuilder(para).reverse().toString();
        System.out.println("Reversed paragraph: " + reversed);
        sc.close();
    }
}

/*
==================== SAMPLE INPUT / OUTPUT ====================
--- Sample run 1 ---
[Input]
The campus facilities are good and the faculty are very helpful
good
faculty
teachers
[Output]
Enter the feedback paragraph:
Enter word to search: Enter word to replace: Enter new word: 
----- RESULTS -----
Total characters (with spaces)   : 63
Total characters (without spaces): 53
Total words                      : 11
Uppercase : THE CAMPUS FACILITIES ARE GOOD AND THE FACULTY ARE VERY HELPFUL
Lowercase : the campus facilities are good and the faculty are very helpful
Word "good" found at index 26
After replacing "faculty" with "teachers":
The campus facilities are good and the teachers are very helpful
Reversed paragraph: lufpleh yrev era ytlucaf eht dna doog era seitilicaf supmac ehT

===============================================================
*/
