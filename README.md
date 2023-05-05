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
