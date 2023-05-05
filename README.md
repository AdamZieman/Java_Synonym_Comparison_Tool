<h1>Synonyms</h1>



<!-- Program Description -->
This program utilizes natural language processing techniques to find the closest synonym for a given word based on a list of word choices. To achieve this, the program uses the cosine similarity between frequency vectors for the words to determine the synonym.

<br>

The program consists of two classes: 'Main' and 'Synonyms'.

<br>

The 'Main' class:
- Generates a corpus of classic literature by attempting to create a predefined array of URLs to literature from www.gutenberg.org.
- Enters a loop which:
  - Prompts the user to enter a primary word and a list of word choices.
  - Uses the 'Synonyms' class to calculate the cosine similarity between the primary word and the word choices.
  - Prints the result.

<br>

The 'Synonyms' class:
- Contains the logic for parsing a corpus of text files and analyzing the frequency of occurrences of each word in the text.
- Constructs a HashMap of decriptors for each word in the corpus and the resulting descriptor vectors for each word in the descriptors map.
- Calculates cosine similarity between frequency vectors for the words.
- Contains a 'toString' method to return the result.

<br>



<!-- Getting Started -->
<h2>Getting Started</h2>
To run this program, follow these steps:

1. Clone the project from GitHub.
2. Open the project in your Java IDE.
3. Run the 'Main' class.
4. Enter a primary word when prompted.
5. Enter a list of word choices (seperated by spaces) when prompted.
6. The program will determine the closest synonym and print the result to the consolue.
7. To exit the program, enter an empty string for the primary word.
