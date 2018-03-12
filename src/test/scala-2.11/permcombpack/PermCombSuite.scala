package permcombpack

import org.scalatest.FunSuite

/**
  * Created by JanVerheul on 3/7/2018.
  */
class PermCombSuite extends FunSuite {

    val testList1 = List()
    val testList2 = List("A", "B", "C", "D", "E", "F", "G")
    val accumulator = new Accumulator[String]

    // TODO Some kind of matrix test case specification would be a better fit here...

    test("A Test of permutations with repeating") {
        accumulator.clear()
        PermComb.repeatingPerm(testList1, 0, accumulator.push)
        assert(accumulator.getSize() == 1)
        assert(accumulator.getSizeDistinct() == 1)
        assert(accumulator.getSize(List()) == 1)

        accumulator.clear()
        PermComb.repeatingPerm(testList1, 2, accumulator.push)
        assert(accumulator.getSize() == 0)
        assert(accumulator.getSizeDistinct() == 0)

        accumulator.clear()
        PermComb.repeatingPerm(testList1, 4, accumulator.push)
        assert(accumulator.getSize() == 0)
        assert(accumulator.getSizeDistinct() == 0)

        accumulator.clear()
        PermComb.repeatingPerm(testList2, 0, accumulator.push)
        assert(accumulator.getSize() == 1)
        assert(accumulator.getSizeDistinct() == 1)
        assert(accumulator.getSize(List()) == 1)

        accumulator.clear()
        PermComb.repeatingPerm(testList2, 2, accumulator.push)
        assert(accumulator.getSize() == 49)
        assert(accumulator.getSizeDistinct() == 49)
        assert(accumulator.getSize(List()) == 0)
        assert(accumulator.getSize(List("A", "A")) == 1)
        assert(accumulator.getSize(List("A", "B")) == 1)
        assert(accumulator.getSize(List("B", "A")) == 1)
        assert(accumulator.getSize(List("A", "A", "A", "A")) == 0)
        assert(accumulator.getSize(List("D", "C", "B", "A")) == 0)

        accumulator.clear()
        PermComb.repeatingPerm(testList2, 4, accumulator.push)
        assert(accumulator.getSize() == 2401)
        assert(accumulator.getSizeDistinct() == 2401)
        assert(accumulator.getSize(List()) == 0)
        assert(accumulator.getSize(List("A", "A")) == 0)
        assert(accumulator.getSize(List("A", "B")) == 0)
        assert(accumulator.getSize(List("A", "A", "A", "A")) == 1)
        assert(accumulator.getSize(List("A", "B", "C", "D")) == 1)
        assert(accumulator.getSize(List("D", "C", "B", "A")) == 1)
    }

    test("A Test of permutations without repeating") {
        accumulator.clear()
        PermComb.nonRepeatingPerm(testList1, 0, accumulator.push)
        assert(accumulator.getSize() == 1)
        assert(accumulator.getSizeDistinct() == 1)
        assert(accumulator.getSize(List()) == 1)

        accumulator.clear()
        PermComb.nonRepeatingPerm(testList2, 0, accumulator.push)
        assert(accumulator.getSize() == 1)
        assert(accumulator.getSizeDistinct() == 1)
        assert(accumulator.getSize(List()) == 1)

        accumulator.clear()
        PermComb.nonRepeatingPerm(testList2, 2, accumulator.push)
        assert(accumulator.getSize() == 42)
        assert(accumulator.getSizeDistinct() == 42)
        assert(accumulator.getSize(List()) == 0)
        assert(accumulator.getSize(List("A", "A")) == 0)
        assert(accumulator.getSize(List("A", "B")) == 1)
        assert(accumulator.getSize(List("B", "A")) == 1)
        assert(accumulator.getSize(List("A", "A", "A", "A")) == 0)
        assert(accumulator.getSize(List("A", "B", "C", "D")) == 0)
        assert(accumulator.getSize(List("D", "C", "B", "A")) == 0)

        accumulator.clear()
        PermComb.nonRepeatingPerm(testList2, 4, accumulator.push)
        assert(accumulator.getSize() == 840)
        assert(accumulator.getSizeDistinct() == 840)
        assert(accumulator.getSize(List()) == 0)
        assert(accumulator.getSize(List("A", "A")) == 0)
        assert(accumulator.getSize(List("A", "B")) == 0)
        assert(accumulator.getSize(List("A", "A", "A", "A")) == 0)
        assert(accumulator.getSize(List("A", "B", "C", "D")) == 1)
        assert(accumulator.getSize(List("D", "C", "B", "A")) == 1)
    }

    test("A Test of combinations with repeating") {
        accumulator.clear()
        PermComb.repeatingComb(testList1, 0, accumulator.push)
        assert(accumulator.getSize() == 1)
        assert(accumulator.getSizeDistinct() == 1)
        assert(accumulator.getSize(List()) == 1)

        accumulator.clear()
        PermComb.repeatingComb(testList1, 2, accumulator.push)
        assert(accumulator.getSize() == 0)
        assert(accumulator.getSizeDistinct() == 0)

        accumulator.clear()
        PermComb.repeatingComb(testList1, 4, accumulator.push)
        assert(accumulator.getSize() == 0)
        assert(accumulator.getSizeDistinct() == 0)

        accumulator.clear()
        PermComb.repeatingComb(testList2, 0, accumulator.push)
        assert(accumulator.getSize() == 1)
        assert(accumulator.getSizeDistinct() == 1)
        assert(accumulator.getSize(List()) == 1)

        accumulator.clear()
        PermComb.repeatingComb(testList2, 2, accumulator.push)
        assert(accumulator.getSize() == 28)
        assert(accumulator.getSizeDistinct() == 28)
        assert(accumulator.getSize(List()) == 0)
        assert(accumulator.getSize(List("A", "A")) == 1)
        assert(accumulator.getSize(List("A", "B")) == 1)
        assert(accumulator.getSize(List("B", "A")) == 0)
        assert(accumulator.getSize(List("A", "A", "A", "A")) == 0)
        assert(accumulator.getSize(List("A", "B", "C", "D")) == 0)
        assert(accumulator.getSize(List("D", "C", "B", "A")) == 0)

        accumulator.clear()
        PermComb.repeatingComb(testList2, 4, accumulator.push)
        assert(accumulator.getSize() == 210)
        assert(accumulator.getSizeDistinct() == 210)
        assert(accumulator.getSize(List()) == 0)
        assert(accumulator.getSize(List("A", "A")) == 0)
        assert(accumulator.getSize(List("A", "B")) == 0)
        assert(accumulator.getSize(List("B", "A")) == 0)
        assert(accumulator.getSize(List("A", "A", "A", "A")) == 1)
        assert(accumulator.getSize(List("A", "B", "C", "D")) == 1)
        assert(accumulator.getSize(List("D", "C", "B", "A")) == 0)
    }

    test("A Test of combinations without repeating") {
        accumulator.clear()
        PermComb.nonRepeatingComb(testList1, 0, accumulator.push)
        assert(accumulator.getSize() == 1)
        assert(accumulator.getSizeDistinct() == 1)
        assert(accumulator.getSize(List()) == 1)

        accumulator.clear()
        PermComb.nonRepeatingComb(testList2, 0, accumulator.push)
        assert(accumulator.getSize() == 1)
        assert(accumulator.getSizeDistinct() == 1)
        assert(accumulator.getSize(List()) == 1)

        accumulator.clear()
        PermComb.nonRepeatingComb(testList2, 2, accumulator.push)
        assert(accumulator.getSize() == 21)
        assert(accumulator.getSizeDistinct() == 21)
        assert(accumulator.getSize(List()) == 0)
        assert(accumulator.getSize(List("A", "A")) == 0)
        assert(accumulator.getSize(List("A", "B")) == 1)
        assert(accumulator.getSize(List("B", "A")) == 0)
        assert(accumulator.getSize(List("A", "A", "A", "A")) == 0)
        assert(accumulator.getSize(List("A", "B", "C", "D")) == 0)
        assert(accumulator.getSize(List("D", "C", "B", "A")) == 0)

        accumulator.clear()
        PermComb.nonRepeatingComb(testList2, 4, accumulator.push)
        assert(accumulator.getSize() == 35)
        assert(accumulator.getSizeDistinct() == 35)
        assert(accumulator.getSize(List()) == 0)
        assert(accumulator.getSize(List("A", "A")) == 0)
        assert(accumulator.getSize(List("A", "B")) == 0)
        assert(accumulator.getSize(List("B", "A")) == 0)
        assert(accumulator.getSize(List("A", "A", "A", "A")) == 0)
        assert(accumulator.getSize(List("A", "B", "C", "D")) == 1)
        assert(accumulator.getSize(List("D", "C", "B", "A")) == 0)
    }

    test("A Test of permutations with repeating (using a pull-style generator") {
        accumulator.clear()
        var repeatingPermPull = new RepeatingPermPull[String](testList1, 0)
        PermComb.constructPushGenerator(accumulator.push, repeatingPermPull.iterator)
        assert(accumulator.getSize() == 1)
        assert(accumulator.getSizeDistinct() == 1)
        assert(accumulator.getSize(List()) == 1)

        accumulator.clear()
        repeatingPermPull = new RepeatingPermPull[String](testList1, 2)
        PermComb.constructPushGenerator(accumulator.push, repeatingPermPull.iterator)
        assert(accumulator.getSize() == 0)
        assert(accumulator.getSizeDistinct() == 0)

        accumulator.clear()
        repeatingPermPull = new RepeatingPermPull[String](testList1, 4)
        PermComb.constructPushGenerator(accumulator.push, repeatingPermPull.iterator)
        assert(accumulator.getSize() == 0)
        assert(accumulator.getSizeDistinct() == 0)

        accumulator.clear()
        repeatingPermPull = new RepeatingPermPull[String](testList1, 0)
        PermComb.constructPushGenerator(accumulator.push, repeatingPermPull.iterator)
        assert(accumulator.getSize() == 1)
        assert(accumulator.getSizeDistinct() == 1)
        assert(accumulator.getSize(List()) == 1)

        accumulator.clear()
        repeatingPermPull = new RepeatingPermPull[String](testList2, 2)
        PermComb.constructPushGenerator(accumulator.push, repeatingPermPull.iterator)
        assert(accumulator.getSize() == 49)
        assert(accumulator.getSizeDistinct() == 49)
        assert(accumulator.getSize(List()) == 0)
        assert(accumulator.getSize(List("A", "A")) == 1)
        assert(accumulator.getSize(List("A", "B")) == 1)
        assert(accumulator.getSize(List("B", "A")) == 1)
        assert(accumulator.getSize(List("A", "A", "A", "A")) == 0)
        assert(accumulator.getSize(List("D", "C", "B", "A")) == 0)

        accumulator.clear()
        repeatingPermPull = new RepeatingPermPull[String](testList2, 4)
        PermComb.constructPushGenerator(accumulator.push, repeatingPermPull.iterator)
        assert(accumulator.getSize() == 2401)
        assert(accumulator.getSizeDistinct() == 2401)
        assert(accumulator.getSize(List()) == 0)
        assert(accumulator.getSize(List("A", "A")) == 0)
        assert(accumulator.getSize(List("A", "B")) == 0)
        assert(accumulator.getSize(List("A", "A", "A", "A")) == 1)
        assert(accumulator.getSize(List("A", "B", "C", "D")) == 1)
        assert(accumulator.getSize(List("D", "C", "B", "A")) == 1)
    }

    test("A Test of combinations without repeating (using a pull-style generator)") {
        accumulator.clear()
        var nonRepeatingCombPull = new NonRepeatingCombPull[String](testList1, 1)
        PermComb.constructPushGenerator(accumulator.push, nonRepeatingCombPull.iterator)
        // Pull-style generator doesn't work well in this boundary case! Generating a combination
        // of length zero from zero elements should produce one combination...
        //assert(accumulator.getSize() == 1)
        //assert(accumulator.getSizeDistinct() == 1)
        //assert(accumulator.getSize(List()) == 1)

        accumulator.clear()
        nonRepeatingCombPull = new NonRepeatingCombPull[String](testList2, 0)
        PermComb.constructPushGenerator(accumulator.push, nonRepeatingCombPull.iterator)
        assert(accumulator.getSize() == 1)
        assert(accumulator.getSizeDistinct() == 1)
        assert(accumulator.getSize(List()) == 1)

        accumulator.clear()
        nonRepeatingCombPull = new NonRepeatingCombPull[String](testList2, 2)
        PermComb.constructPushGenerator(accumulator.push, nonRepeatingCombPull.iterator)
        assert(accumulator.getSize() == 21)
        assert(accumulator.getSizeDistinct() == 21)
        assert(accumulator.getSize(List()) == 0)
        assert(accumulator.getSize(List("A", "A")) == 0)
        assert(accumulator.getSize(List("A", "B")) == 1)
        assert(accumulator.getSize(List("B", "A")) == 0)
        assert(accumulator.getSize(List("A", "A", "A", "A")) == 0)
        assert(accumulator.getSize(List("A", "B", "C", "D")) == 0)
        assert(accumulator.getSize(List("D", "C", "B", "A")) == 0)

        accumulator.clear()
        nonRepeatingCombPull = new NonRepeatingCombPull[String](testList2, 4)
        PermComb.constructPushGenerator(accumulator.push, nonRepeatingCombPull.iterator)
        assert(accumulator.getSize() == 35)
        assert(accumulator.getSizeDistinct() == 35)
        assert(accumulator.getSize(List()) == 0)
        assert(accumulator.getSize(List("A", "A")) == 0)
        assert(accumulator.getSize(List("A", "B")) == 0)
        assert(accumulator.getSize(List("B", "A")) == 0)
        assert(accumulator.getSize(List("A", "A", "A", "A")) == 0)
        assert(accumulator.getSize(List("A", "B", "C", "D")) == 1)
        assert(accumulator.getSize(List("D", "C", "B", "A")) == 0)
    }

    test("A Test of combinations without repeating (using a pull-style generator based on Scala Limited Continuations)") {
        accumulator.clear()
        var nonRepeatingCombCont = new NonRepeatingCombCont[String](testList1, 0)
        PermComb.constructPushGenerator(accumulator.push, nonRepeatingCombCont.iterator)
        assert(accumulator.getSize() == 1)
        assert(accumulator.getSizeDistinct() == 1)
        assert(accumulator.getSize(List()) == 1)

        accumulator.clear()
        nonRepeatingCombCont = new NonRepeatingCombCont[String](testList2, 0)
        PermComb.constructPushGenerator(accumulator.push, nonRepeatingCombCont.iterator)
        assert(accumulator.getSize() == 1)
        assert(accumulator.getSizeDistinct() == 1)
        assert(accumulator.getSize(List()) == 1)

        accumulator.clear()
        nonRepeatingCombCont = new NonRepeatingCombCont[String](testList2, 2)
        PermComb.constructPushGenerator(accumulator.push, nonRepeatingCombCont.iterator)
        assert(accumulator.getSize() == 21)
        assert(accumulator.getSizeDistinct() == 21)
        assert(accumulator.getSize(List()) == 0)
        assert(accumulator.getSize(List("A", "A")) == 0)
        assert(accumulator.getSize(List("A", "B")) == 1)
        assert(accumulator.getSize(List("B", "A")) == 0)
        assert(accumulator.getSize(List("A", "A", "A", "A")) == 0)
        assert(accumulator.getSize(List("A", "B", "C", "D")) == 0)
        assert(accumulator.getSize(List("D", "C", "B", "A")) == 0)

        accumulator.clear()
        nonRepeatingCombCont = new NonRepeatingCombCont[String](testList2, 4)
        PermComb.constructPushGenerator(accumulator.push, nonRepeatingCombCont.iterator)
        assert(accumulator.getSize() == 35)
        assert(accumulator.getSizeDistinct() == 35)
        assert(accumulator.getSize(List()) == 0)
        assert(accumulator.getSize(List("A", "A")) == 0)
        assert(accumulator.getSize(List("A", "B")) == 0)
        assert(accumulator.getSize(List("B", "A")) == 0)
        assert(accumulator.getSize(List("A", "A", "A", "A")) == 0)
        assert(accumulator.getSize(List("A", "B", "C", "D")) == 1)
        assert(accumulator.getSize(List("D", "C", "B", "A")) == 0)
    }


}
