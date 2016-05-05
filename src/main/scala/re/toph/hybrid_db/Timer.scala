package re.toph.hybrid_db

import scala.collection.mutable

/**
  * Created by christoph on 28/04/16.
  */
object Timer {
  val times = mutable.Map[String, Long]()
  val counts = mutable.Map[String, Int]()

  def time[R](name:String, block: => R): R = {
    val t0 = System.nanoTime()
    val result = block    // call-by-name

    // comment out to un-filter DB calls
    if (name == "DB") return result

    val t1 = System.nanoTime()
    val diff = (t1 - t0)/1000000
    val total = times.getOrElse(name, 0L)
    times += (name -> (total + diff))
    val count = counts.getOrElse(name, 0)
    counts += (name -> (count + 1))

    println(s"Time for $name: ${diff}ms (${total + diff}ms total, ${(total+diff)/(count+1)}ms avg)")
    result
  }
}