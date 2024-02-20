<h1>Synonym Comparison Tool</h1>



<!-- Program Description -->
<p>This program uses text processing to find the closest synonym for a given word based on a list of word choices. To achieve this, the program uses the cosine similarity between frequency vectors for the words to determine the synonym.</p>



<!-- How It Works -->
<h2><b>How It Works</b></h2>
<p>The program consists of two classes: 'Main' and 'Synonyms'.</p>
<p>The 'Main' class:</p>

- Generates a corpus of classic literature by attempting to create a predefined array of URLs to literature from www.gutenberg.org.
- Enters a loop which:
  - Prompts the user to enter a primary word and a list of word choices.
  - Uses the 'Synonyms' class to calculate the cosine similarity between the primary word and the word choices.
  - Prints the result.

<p>The 'Synonyms' class:</p>

- Contains the logic for parsing a corpus of text files and analyzing the frequency of occurrences of each word in the text.
- Constructs a HashMap of decriptors for each word in the corpus and the resulting descriptor vectors for each word in the descriptors map.
- Calculates cosine similarity between frequency vectors for the words.
  - Returns -1.0 if either the primary word or the currently searched, word choice was not found in corpus.
- Contains a 'toString' method to return the result.
  - Returns the prompt "There are no synonyms" if either the primary word or all the word choices were not found in corpus.

![Output](https://user-images.githubusercontent.com/96446640/236387253-1fc0f754-194b-4ede-a10e-f9dbe71e8c45.png)



<!-- Limitations -->
<h2><b>Limitations</b></h2>
<p>Due to the limited size of corpus, the program may determine an incorrect synonym. This is can be remedied by either solution.</p>

- Increase the size of corpus to increase the amount of data the program has access to.
- Tailor the corpus to match the category of the primary word.
  - If primary word is a historical word, then fill corpus with historical text;
  - If primary word is a sports word, then fill corpus with sports text.



<!-- Acknowledgments -->
<h2><b>Acknowledgments</b></h2>
<p>The program uses the following eight classic novels from Project Gutenberg as the corpus for generating synonyms:</p>

- Pride and Prejudice, by Jane Austen
- The Adventures of Sherlock Holmes, by A. Conan Doyle
- A Tale of Two Cities, by Charles Dickens
- Alice's Adventures In Wonderland, by Lewis Carroll
- Moby Dick; or The Whale, by Herman Melville
- War and Peace, by Leo Tolstoy
- The Importance of Being Earnest, by Oscar Wilde
- The Wisdom of Father Brown, by G.K. Chesterton
