package permcombpack

/**
  * Created by JanVerheul on 4/14/2017.
  */
object TimeTesterNonRepeatingCombPull extends App {

    val nonRepeatingCombPull = new NonRepeatingCombPull(List("A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"), 13)

    var counter = 0

    val begin = (new java.util.Date()).getTime()
    for (lst <- nonRepeatingCombPull) { counter += 1 }
    val end = (new java.util.Date()).getTime()

    println(counter)
    println("Time in MS: " + (end - begin))


}
