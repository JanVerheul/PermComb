package permcombpack

import scala.util.continuations._
/**
  * Created by JanVerheul on 4/13/2017.
  */
object ContinuationsExperimeter1 extends App {

    var state: (Unit => Int) = null

    var value: Int = reset {
        var xx = 0
        shift { k: (Unit => Int) => { state = k; xx } }
        xx += 3
        shift { k: (Unit => Int) => { state = k; xx } }
        xx *= 2
        shift { k: (Unit => Int) => { state = k; xx } }
        xx += 5
        shift { k: (Unit => Int) => { state = k; xx } }
        xx *= 3
        shift { k: (Unit => Int) => { state = k; xx } }
        xx += 13
        shift { k: (Unit => Int) => { state = k; xx } }
        xx
    }

    def next() = {
        state()
    }

    println(next())
    println(next())
    println(next())
    println(next())
    println(next())
    println(next())

}
