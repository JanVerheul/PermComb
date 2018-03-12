package permcombpack

import scala.util.continuations._
/**
  * Created by JanVerheul on 4/13/2017.
  */
object ContinuationsExperimenter2 extends App {

    val value: Int = reset {
        val a = 2
        def f(x: Int): Int = x * x + 10
        val b = f( shift { k: (Int => Int) => k(a) + 5 } )
        b * 3
    }

    println(value) // Should print 47...

}
