val shakespeareLines = sc.textFile("Shakespeare.txt")  // File of all Shakespeare's work

val plainOldCharRegex = raw"[a-zA-Z]".r                                         // Regex that matches only regular characters

{
shakespeareLines
    .flatMap(_.split(" "))                                                      // Split each line by spaces into words
    .filter(!_.isEmpty)                                                         // Remove any empty 'words'
    .map(_.split(""))                                                           // Split each word without flatten (We have a collection of arrays containing characters)
    .map(_.filter(                                                              // For each char array;
        !plainOldCharRegex.findFirstIn(_).isEmpty                               //     Remove any char who doesn't match the regex
    ))
    .map(_.mkString)                                                            // Recombine every char Array back into a String
    .filter(!_.isEmpty)                                                         // Remove any empty Strings
    .map(_.toLowerCase)                                                         // Force all Strings to lower case
    .map((_, 1))                                                                // Turn words into a tuple of (word, 1)
    .reduceByKey(_+_)                                                           // Reduce by summing the counts
    .top(5)(                                                                    // Take 5 highest values, based on the following Ordering function,
        Ordering[Long]                                                          //     It's a Long ordering
            .on(_._2)                                                           //     Use the second value of the tuple
                                                                                //         Fuckin Scala cryptic underscore bullshit (is also awesome).
                                                                                //         First '_' is to shorthand the lambda def for the map tuple (because a => a is just 5 too many characters), and '_2' accesses the second tuple value)
    )                                                                           // Returns a non-RDD
}
