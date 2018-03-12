package permcombpack

/**
  * Created by JanVerheul on 3/7/2018.
  *
  * The class provides methods for generating permutations and combinations push-style.
  *
  * All four kinds of permutations and combinations are supported.
  *
  * Some alternative forms of shaping the algorithms are provided (let the generation process stop as
  *
  * soon as the callback delivers true, or accumulate all combinations for which the callback deliverse true).
  */
object PermComb {

    /*
     * The method calls <f> with all permutations of length <length> of the elements <elems>.
     * Repetitions of elements of <elems> can occur.
     */
    def repeatingPerm[T](elems: List[T], genSize: Int, f: List[T] => Unit): Unit = {
        def repeatingPermRec(elems: List[T], depth: Int, partResult: List[T]): Unit = depth match {
            case 0 => f(List())
            case 1 => for (elem <- elems) f(elem :: partResult)
            case n => for (elem <- elems) repeatingPermRec(elems, depth - 1, elem :: partResult)
        }
        if (genSize < 0) throw new IllegalArgumentException("Negative lengths not allowed in repeatingPerm...")
        repeatingPermRec(elems, genSize, Nil)
    }

    /*
     * The method calls <f> with all permutations of length <length> of the elements <elems>.
     * Repetitions of elements of <elems> cannot occur.
     */
    def nonRepeatingPerm[T](elems: List[T], genSize: Int, f: List[T] => Unit): Unit = {
        def removeElem[T](elems: List[T], elem: T): List[T] = elems match {
            case Nil => Nil
            case head :: tail => if (head == elem) removeElem(tail, elem) else head :: removeElem(tail, elem)
        }
        def nonRepeatingPermRec(elems: List[T], depth: Int, f: List[T] => Unit, partResult: List[T]): Unit = depth match {
            case 0 => f(List())
            case 1 => for (elem <- elems) f(elem :: partResult)
            case n => for (elem <- elems) {
                nonRepeatingPermRec(removeElem(elems, elem), depth - 1, f, elem :: partResult)
            }
        }
        if (genSize < 0) throw new IllegalArgumentException("Negative lengths not allowed in nonRepeatingPerm...")
        if (genSize > elems.size) throw new IllegalArgumentException("Lengths over elems.size not allowed in nonRepeatingPerm...")
        nonRepeatingPermRec(elems.reverse, genSize, f, Nil)
    }

    /*
     * The method calls <f> with all combinations of length <length> of the elements <elems>.
     * Repetitions of elements of <elems> can occur.
     *
     * This is the most difficult one of all four algorithms. Internally, the algorithm for generating combinations
     * without repetitions is used and the problem of generating combinations with repetitions is reduced to the problem
     * of generating combinations without repetition.
     */
    def repeatingComb[T](elems: List[T], genSize: Int, f: List[T] => Unit): Unit = {
        def folder(triple: (List[T], Int, List[T]), index: Int): (List[T], Int, List[T]) = triple match {
            case (elems, i, result) => (elems.drop(index - i), index + 1, elems.drop(index - i).head :: result)
        }
        def mapSimulation(input: List[Int]): List[T] = (((elems.reverse, 0, Nil: List[T]) /: input) (folder))._3
        def nonRepeatingCombRec(elems: List[Int], length: Int, partResult: List[Int]): Unit = {
            if (elems.size == length) f(mapSimulation(partResult.reverse ++ elems))
            else {
                if (!elems.isEmpty) {
                    nonRepeatingCombRec(elems.tail, length - 1, elems.head :: partResult)
                    nonRepeatingCombRec(elems.tail, length, partResult)
                }
            }
        }
        if (genSize < 0) throw new IllegalArgumentException("Negative lengths not allowed in repeatingComb...")
        val simulationSize = genSize + elems.length - 1
        nonRepeatingCombRec((0 until simulationSize).toList, genSize, Nil)
    }

    /*
     * The method calls <f> with all combinations of length <length> of the elements <elems>.
     * Repetitions of elements of <elems> cannot occur.
     */
    def nonRepeatingComb[T](elems: List[T], genSize: Int, f: List[T] => Unit): Unit = {
        def nonRepeatingCombRec(elems: List[T], length: Int, partResult: List[T]): Unit = {
            if (elems.size == length) f(elems.reverse ::: partResult)
            else {
                if (!elems.isEmpty) {
                    nonRepeatingCombRec(elems.tail, length, partResult)
                    if (length > 0) nonRepeatingCombRec(elems.tail, length - 1, elems.head :: partResult)
                }
            }
        }
        if (genSize < 0) throw new IllegalArgumentException("Negative lengths not allowed in nonRepeatingComb...")
        if (genSize > elems.size) throw new IllegalArgumentException("Lengths over elems.size not allowed in nonRepeatingComb...")
        nonRepeatingCombRec(elems.reverse, genSize, Nil)
    }

    /*
     * The method calls <f> with all combinations of length <length> of the elements <elems>.
     * Repetitions of elements of <elems> cannot occur.
     *
     * The method delivers the first combination for which <f> evaluates to true and then stops.
     * If none of the combinations evaluates to true, the result Option will be empty.
     */
    def nonRepeatingCombSeekFirst[T](elems: List[T], length: Int, f: List[T] => Boolean): Option[List[T]] = {
        def nonRepeatingCombSeekFirstRec(elems: List[T], length: Int, partResult: List[T]): Option[List[T]] = {
            if (elems.size == length) {
                val result = elems.reverse ::: partResult
                if (f(result)) Some(result) else None
            }
            else {
                if (!elems.isEmpty) {
                    nonRepeatingCombSeekFirstRec(elems.tail, length, partResult) match {
                        case None => nonRepeatingCombSeekFirstRec(elems.tail, length - 1, elems.head :: partResult)
                        case x => x
                    }
                }
                else None
            }
        }
        if (length < 0) throw new IllegalArgumentException("Negative lengths not allowed in nonRepeatingCombSeekFirst...")
        if (length > elems.size) throw new IllegalArgumentException("Lengths over elems.size not allowed in nonRepeatingCombSeekFirst...")
        nonRepeatingCombSeekFirstRec(elems.reverse, length, Nil)
    }

    /*
     * The method calls <f> with all combinations of length <length> of the elements <elems>.
     * Repetitions of elements of <elems> cannot occur.
     *
     * The method delivers a list of all the combinations for which <f> evaluates to true.
     */
    def nonRepeatSeekAll[T](elems: List[T], length: Int, f: List[T] => Boolean): List[List[T]] = {
        def nonRepeatRec(elems: List[T], length: Int, partResult: List[T]): List[List[T]] = {
            if (elems.size == length) {
                val result = elems.reverse ::: partResult
                if (f(result)) List(result) else List()
            }
            else {
                if (!elems.isEmpty) {
                    val tailPart = nonRepeatRec(elems.tail, length, partResult)
                    val headPart = nonRepeatRec(elems.tail, length - 1, elems.head :: partResult)
                    tailPart ::: headPart
                }
                else Nil
            }
        }
        if (length < 0) throw new IllegalArgumentException("Negative lengths not allowed in nonRepeatSeekAll...")
        if (length > elems.size) throw new IllegalArgumentException("Lengths over elems.size not allowed in nonRepeatSeekAll...")
        nonRepeatRec(elems.reverse, length, Nil)
    }

    /*
     * The method converts a pull-style generator into a push-style generator. Conversion the other way around is much more difficult,
     * if one wants to avoid potential performance and memory usage problems.
     */
    def constructPushGenerator[T](callback: (List[T] => Unit), iterator: Iterator[List[T]]) = for (lst <- iterator) callback(lst)

}
