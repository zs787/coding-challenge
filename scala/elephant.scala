// A (hopefully) quick Scala exercise. Some input values and functions
// are defined for you, while others will need to be filled in. Any method or
// test value with a '???' is expected to be replaced with an implementation,
// otherwise a NotImplementedError is thrown

// It's recommended to run this sample in an IntelliJ or Eclipse worksheet,
// although it can also be completed within the Scala REPL as well

// Note that the the 'assert' test lines are provided to help assist you with
// the expected results of your implementation. Note that in the worksheet
// when they execute successfully they will look like 'res0: Unit = ()'
//
// You may add or replace any of the tests as desired, but please include
// comments as to why you're doing so (e.g., you can convince me you have a
// better implementation than the tests expect)

// This is the input corpus we'll be working with. Our goal is to
// programmatically determine the unique count of words of the input

val words = s"""
                |Hadoop is the Elephant King!
                |A yellow and elegant thing.
                |He never forgets
                |Useful data, or lets
                |An extraneous element cling!
                |A wonderful king is Hadoop.
                |The elephant plays well with Sqoop.
                |But what helps him to thrive
                |Are Impala, and Hive,
                |And HDFS in the group.
                |Hadoop is an elegant fellow.
                |An elephant gentle and mellow.
                |He never gets mad,
                |Or does anything bad,
                |Because, at his core, he is yellow.
   """.stripMargin

// As a first pass, let's try split the text on spaces, then remove whitespace
// around the words using 'trim'. We'll get back an array of strings.
def naiveInputParser(text: String): Array[String] = text.split(" ").map(word => word.trim)

val naiveResults = naiveInputParser(words)

// Print the first 5 words
// naiveResults.take(5).foreach(println)

assert(naiveResults.contains("Hadoop") == true)
// Not bad

assert(naiveResults.contains("Elephant") == true)
// So far so good...

assert(naiveResults.contains("elephant") == true)
// Uh oh, a lowercase 'elephant' as well! These should count as the same word

assert(naiveResults.contains("King!\nA") == true)
// That's not even a word!

// It looks like we need to do some data cleaning here. Let's define a function
// that splits the input text on all whitespace (not just spaces), removes
// punctuation, lowercases everything, and doesn't allow any empty results.

// Replace the '???' with the implementation code. There are tests below to
// verify your results!

def parseInput(text: String): Array[String] = ???

val betterResults = parseInput(words)

// Print the first 5 words
betterResults.take(5).foreach(println)

// Some tests to verify
assert(betterResults.size == 72)
assert(betterResults.head == "hadoop")
assert(betterResults.tail.head == "is")

// Given an array of strings, group them together and count the number
// of occurrences, returned as a Map[String, Int]

// Note that there are a number of ways to accomplish this. Both functional
// and imperative solutions are acceptable.

def getWordCount(wordArray: Array[String]) : Map[String, Int] = ???

val wordCounts = getWordCount(parseInput(words))

// Verify the results
assert(wordCounts("hadoop") == 3)
assert(wordCounts("is") == 4)
assert(wordCounts("the") == 3)

// Let's find the most commonly occurring word in the set, returned as a tuple
// Don't worry about handling edge cases here, e.g. for this purpose, we can
// assume the map is non-empty, and there isn't a tie for most commonly
// occurring word
def getMostFrequentWordCount(input: Map[String, Int]): (String, Int) = ???

// Fill in the values below to get the unit test passing
// (hint: it will be a pretty common word)
assert(getMostFrequentWordCount(wordCounts) == (???, ???))
