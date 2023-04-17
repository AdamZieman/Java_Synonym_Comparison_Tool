package Synonyms;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

/**
 This class contains the main method that prompts the user to enter a primary word and a list of word
 choices. It then uses the Synonyms class to calculate the cosine similarity between the primary word
 and the word choices, and prints out the results.
 @author Adam Zieman
 */
public class Main {
    /**
     * This is the main method which prompts the user to enter a primary word and a list of word choices.
     * It uses the Synonyms class to calculate the cosine similarity between the primary word and the
     * word choices, and prints out the results.
     * @param args Unused.
     */
    public static void main(String[] args) {
        Scanner scanKeyboard = new Scanner(System.in); // Create a Scanner object to read input from the keyboard
        String primaryWord;
        String[] wordChoices;
        URL[] corpus = generateCorpus(); // Create an array of URLs to use as the corpus for generating synonyms
        Synonyms synonyms; // Declare a variable to store the Synonyms object

        // Start a loop to continually prompt the user for input
        do {
            // Prompt the user to enter a word
            System.out.print("Enter a word: ");
            primaryWord = scanKeyboard.nextLine().toLowerCase();

            // If the user entered a word, prompt them to enter choices
            if (!primaryWord.isEmpty()) {
                System.out.println("Enter the choices on one line (seperated by spaces):");
                wordChoices = scanKeyboard.nextLine().toLowerCase().split("\\s+");

                // If the user entered choices, generate synonyms using the Synonyms class
                if (wordChoices.length != 0) {
                    synonyms = new Synonyms(primaryWord, wordChoices, corpus);

                    System.out.println(synonyms); // Print the synonyms to the console
                }
            }
        } while (!primaryWord.isEmpty()); // Continue looping until the user enters an empty word

        scanKeyboard.close(); // Close the Scanner object to prevent resource leaks
    }

    /**
     * This method generates an array of URLs representing the corpus for calculating cosine similarity.
     * @return An array of URLs representing the corpus for calculating cosine similarity.
     */
    private static URL[] generateCorpus() {
        URL[] corpus = null;

        // Add URLs to the corpus array
        try {
            corpus = new URL[] {
                    // Pride and Prejudice, by Jane Austen
                    new URL("https://www.gutenberg.org/files/1342/1342-0.txt"),
                    // The Adventures of Sherlock Holmes, by A. Conan Doyle
                    new URL("https://www.gutenberg.org/cache/epub/1661/pg1661.txt"),
                    // A Tale of Two Cities, by Charles Dickens
                    new URL("https://www.gutenberg.org/files/98/98-0.txt"),
                    // Alice's Adventures In Wonderland, by Lewis Carroll
                    new URL("https://www.gutenberg.org/files/11/11-0.txt"),
                    // Moby Dick; or The Whale, by Herman Melville
                    new URL("https://www.gutenberg.org/files/2701/2701-0.txt"),
                    // War and Peace, by Leo Tolstoy
                    new URL("https://www.gutenberg.org/files/2600/2600-0.txt"),
                    // The Importance of Being Earnest, by Oscar Wilde
                    new URL("https://www.gutenberg.org/cache/epub/844/pg844.txt"),
                    // The Wisdom of Father Brown, by G.K. Chesterton
                    new URL("https://www.gutenberg.org/files/223/223-0.txt"),
            };
        }
        // Print the stack trace if an exception is caught
        catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return corpus; // Return the corpus array
    }
}
