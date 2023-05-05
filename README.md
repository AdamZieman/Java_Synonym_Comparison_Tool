<h1>Synonyms</h1>



<!-- Program Description -->
This program utilizes natural language processing techniques to find the closest synonym for a given word based on a list of word choices. To achieve this, the program uses the cosine similarity between frequency vectors for the words to determine the synonym.

<br>

The program consists of two classes: 'Main' and 'Synonyms'.

<br>

The 'Main' class:
- Generates a corpus of classic literature by attempting to create a predefined array of URLs to literature from www.gutenberg.org.
- Prompts the user to enter a primary word and a list of word choices.
- Uses the 'Synonyms' class to calculate the cosine similarity between the primary word and the word choices.
- Prints the result.

<br>

The 'Synonyms' class:
- Contains the logic for parsing a corpus of text files and analyzing the frequency of occurrences of each word in the text.
- Constructs a HashMap of decriptors for each word in the corpus and the resulting descriptor vectors for each word in the descriptors map.
- Calculates cosine similarity between frequency vectors for the words.
- Contains a 'toString' method to return the result.
