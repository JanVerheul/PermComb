package permcombpack

import rx.lang.scala.Observable
import rx.lang.scala.Subscriber

/**
  * Created by Janneman on 3/12/2018.
  */
object ReactiveExperimenter extends App {

    val ob: Observable[List[String]] = Observable.apply((emitter: Subscriber[List[String]]) => {
        PermComb.nonRepeatingComb(List("A", "B", "C", "D", "E", "F", "G"), 3, emitter.onNext)
    })

    ob.subscribe(
        onNext => println("Value received: " + onNext),
        onError => println("Error occurred: " + onError.getStackTrace),
        () => println("Stream completed!")
    )

}
