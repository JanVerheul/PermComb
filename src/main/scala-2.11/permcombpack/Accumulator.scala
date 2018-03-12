package permcombpack

import scala.collection.mutable

/**
  * Created by JanVerheul on 3/8/2018.
  *
  * The class is designed to facilitate testing of push-style generators.
  *
  * Create or clear an instance of Accumulator and pass its push method as callback into a generator function.
  *
  * After completion of the generation process, all kinds of information useful to verify correct working of
  *
  * the generator can be extracted from the internal state of this class.
  */
class Accumulator[T] {
    var listAccu: List[List[T]] = Nil
    var mapAccu: mutable.Map[List[T], List[Int]] = new mutable.HashMap[List[T], List[Int]]()
    var counter = 0
    def clear(): Unit = {
        listAccu = Nil
        mapAccu.clear()
        counter = 0
    }
    def push(listElem: List[T]): Unit = {
        listAccu = listElem :: listAccu
        val mapElem = mapAccu.getOrElse(listElem, Nil)
        mapAccu.put(listElem, counter :: mapElem)
        counter += 1
    }
    def getSize() = counter
    def getSizeDistinct() = mapAccu.size
    def getSize(listElem: List[T]) = mapAccu.getOrElse(listElem, Nil).length
    def getIndexFirst(listElem: List[T]) = mapAccu.getOrElse(listElem, List(-1)).head
}
