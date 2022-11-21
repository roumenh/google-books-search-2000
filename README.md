# Search in Google Books API

This simple app connects to Google Books API and searches by author's name for books written in Czech, retrieves their name, authors and thumbnail.
If you click on the list item, you will see next screen, a book detail. Here on top of the previous info you will see book description and a link to Google Play Store (in case it is possible to buy the book via it).
 

What could/will be improved:
- if there are more than 1 authors - display all authors (so far using dirty .First)
- if the search is not successful (no results) - > inform user with some Toast
- Tests
- layout (there are some hardcoded widths but so far I am not that proficient in layouts)
- refreshing of the images works in a funny way (sometimes there are thumbnails from past searches)
- and I guess a lot of other things that I as a beginner did in incorrect way

Overall it works!
Have fun ;)

# How to make it work at your side:

You need to create file apikey.properties inside the root directory
inside that file add this line:

API_KEY="your api key to google cloud where google books api is enabled"
