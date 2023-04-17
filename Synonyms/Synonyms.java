package Synonyms;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Scanner;

/**
 * A class that calculates synonyms for given words by parsing a corpus of text files and analyzing the frequency of
 * occurrences of each word in the text. Synonyms are calculated using cosine similarity between frequency vectors for
 * the words.
 * @author Adam Zieman
 */
public class Synonyms {
    /** Map of descriptors for each word in the corpus. */
    private HashMap<String, HashMap<String, Integer>> descriptors;

    /** The primary word for which synonyms are to be found. */
    private String primaryWord;

    /** The array of word choices to be considered for synonyms. */
    private String[] wordChoices;

    /**
     * Constructs a Synonyms object with the given primary word, array of word choices, and URLs to the corpus.
     * Parses the corpus for each word in the primary word and word choices arrays and stores the resulting descriptor
     * vectors for each word in the descriptors map.
     * @param primaryWord the primary word for which synonyms are to be found
     * @param wordChoices the array of word choices to be considered for synonyms
     * @param corpus      an array of URLs to the corpus
     */
    public Synonyms(String primaryWord, String[] wordChoices, URL[] corpus) {
        // Set the primary word and word choices instance variables to the passed arguments
        this.primaryWord = primaryWord;
        this.wordChoices = wordChoices;

        // Initialize the descriptors map to store the synonyms for each word
        descriptors = new HashMap<>();

        // Parse the corpus to find synonyms for the primary word
        parseCorpus(primaryWord, corpus);

        // Parse the corpus to find synonyms for each of the alternative word choices
        for (String wordChoice : wordChoices) {
            parseCorpus(wordChoice, corpus);
        }
    }

    /**
     * Parses the given corpus for a given search word and stores the resulting descriptor vector in the descriptors
     * map.
     * @param searchWord the word for which to find synonyms
     * @param corpus     an array of URLs to the corpus
     */
    private void parseCorpus(String searchWord, URL[] corpus) {
        Scanner scanURL = null; // Initialize a Scanner to read each URL
        HashMap<String, Integer> descriptorVector = new HashMap<>(); // Initialize a HashMap to store the descriptor vector

        try {
            // Iterate over each URL in the corpus
            for (URL url : corpus) {
                /* Open a new Scanner for the URL and set the delimiter to split on end-of-sentence punctuation or the
                end of the file. */
                scanURL = new Scanner(url.openStream());
                scanURL.useDelimiter("[\\.\\?\\!]|\\Z");

                // Iterate over each sentence in the URL
                while (scanURL.hasNext()) {
                    String sentence = scanURL.next().toLowerCase().replaceAll("\\W+", " ");

                    // Only process sentences that contain the search word
                    if (sentence.contains(searchWord)) {
                        String[] words = sentence.split("\\s+");

                        // Iterate over each word in the sentence
                        for (String word : words) {

                            // Ignore the search word itself and any empty strings
                            if (!word.equals(searchWord) && !word.equals("")) {

                                /* Check whether the word already exists in the descriptor vector, if so increment the
                                count for the word */
                                if (descriptorVector.containsKey(word)) {
                                    descriptorVector.replace(word, descriptorVector.get(word) + 1);
                                }
                                // Otherwise, add the word to the descriptor vector if it does not already exist
                                else {
                                    descriptorVector.put(word, 1);
                                }
                            }
                        }
                    }
                }
            }
        }
        // Handle any IO errors that occur while opening or reading from the URLs
        catch (IOException e) {
            e.printStackTrace();
        }

        descriptors.put(searchWord, descriptorVector); // Add the descriptor vector for the search word to the HashMap

        // Check whether the scanner exists, if so close the Scanner for the last URL in the corpus
        if (scanURL != null) {
            scanURL.close();
        }
    }

    /**
     * Calculates the cosine similarity between two given words by comparing their respective frequency of occurrence
     * in the corpus.
     * @param word1 the first word
     * @param word2 the second word
     * @return the cosine similarity between word1 and word2, or -1.0 if either word is not found in the descriptors map
     */
    public double calculateCosineSimilarity(String word1, String word2) {
        // retrieve the frequency maps for both words
        HashMap<String, Integer> word1Map = descriptors.get(word1);
        HashMap<String, Integer> word2Map = descriptors.get(word2);

        // Checks whether either maps are not empty, calculate the cosine similarity
        if (!word1Map.isEmpty() && !word2Map.isEmpty()) {
            double dotProduct = calculateDotProduct(word1Map, word2Map);
            double word1VectorProduct = calculateVectorProduct(word1Map);
            double word2VectorProduct = calculateVectorProduct(word2Map);
            double totalVectorProduct = word1VectorProduct * word2VectorProduct;
            return dotProduct / totalVectorProduct;
        }
        return -1.0; // Otherwise, return -1.0 to indicate that at least one map was empty
    }

    /**
     * Calculates the dot product of two word maps by iterating over the keys in one map and checking if the other map
     * contains the same key, then multiplying the values of those keys and adding to the dot product.
     * <p>
     *     The dot product is calculated as the summation of the products of the values from matching keys.
     * </p>
     * @param word1Map the descriptor vector for the first word
     * @param word2Map the descriptor vector for the second word
     * @return the dot product of word1Map and word2Map
     */
    private double calculateDotProduct(HashMap<String, Integer> word1Map, HashMap<String, Integer> word2Map) {
        double dotProduct = 0.0;
        for (String key : word1Map.keySet()) {
            if (word2Map.containsKey(key)) {
                dotProduct += word1Map.get(key) * word2Map.get(key);
            }
        }
        return dotProduct;
    }

    /**
     * Calculates the vector product of the given word map, which is the square root of the sum of squares of the
     * frequencies of each word in the map.
     * <p>
     *     The vector product is calculated as the square root of the sum of the squares of each value in the wordMap.
     * </p>
     * @param wordMap the descriptor vector
     * @return the vector product of wordMap
     */
    private double calculateVectorProduct(HashMap<String, Integer> wordMap) {
        double vectorProduct = 0.0;
        for (String key : wordMap.keySet()) {
            vectorProduct += Math.pow(wordMap.get(key), 2);
        }
        return Math.sqrt(vectorProduct);
    }

    /**
     Returns a string representation of this object. The method calculates the cosine similarity
     between the primary word and the other words in the wordChoices array, and appends each word and
     its corresponding cosine similarity value to a StringBuilder object. The method then returns the
     StringBuilder object as a string, with the word with the highest cosine similarity value appended
     at the end.
     @return A string representation of this object containing the words in the wordChoices array and
     their cosine similarity values, and the word with the highest cosine similarity value.
     If the wordChoices array is empty, the method returns the string "There are no synonyms".
     */
    @Override
    public String toString() {
        String largestCosineWord = "";
        double largestCosineValue = -1.0;
        StringBuilder stringBuilder = new StringBuilder();

        // Iterate through word choices and calculate cosine similarity
        for (String wordChoice : wordChoices) {
            double cosineValue = calculateCosineSimilarity(primaryWord, wordChoice);
            stringBuilder.append("\t").append(wordChoice).append(" ").append(cosineValue).append("\n");

            // update largest cosine similarity if necessary
            if (cosineValue > largestCosineValue) {
                largestCosineWord = wordChoice;
                largestCosineValue = cosineValue;
            }
        }

        // if no synonyms found, return appropriate message
        if (largestCosineValue == -1.0) {
            return "There are no synonyms\n";
        }
        // otherwise, append largest cosine similarity word and return as string
        else {
            return stringBuilder.append(largestCosineWord).append("\n").toString();
        }
    }
}
