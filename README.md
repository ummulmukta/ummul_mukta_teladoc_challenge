# ummul_mukta_teladoc_challenge
The code uses Java's RestAssured and JsonPath APIs to make the query and parse the received JSon file to get the required info.

At first the resource URL is defined from the given contract.

Then Java's Scanner utility is used to get user input for the movie name based on which the query will be generated.

Once the movie to search for has been entered, the code concatenates it with the existing URL and makes the first GET post to get the number of pages that the query will result; The contract says "In order to get all results, you may have to make multiple page requests".

Once the require number of pages that the query will result is known, a 'for loop' is entered to dynamically make multiple queries based on the total number of pages after making the required concatenation and the received Json from each page is parsed to get the titles.

The JsonPath API lets you get the results in an ArrayList. All the titles thus received are consolidated in an ArrayList and Java's Collection framework is used to sort the titles in ascending order and an advanced for loop is used to print them in line with the contract.
