package chapter01.section01

import java.io.ByteArrayOutputStream
import scala.collection.mutable
import scala.collection.mutable.ArrayBuffer
import scala.io.StdIn

object Main {


  def main(args: Array[String]): Unit = {
    val outCapture = new ByteArrayOutputStream
    val stdout = System.out
    var line1: String = null
    var line2: String = null
    var line3: String = null

    line1 = StdIn.readLine()
    line2 = StdIn.readLine()
    line3 = StdIn.readLine()

    val array2: Array[String] = line2.split(",")
    val buffer2: ArrayBuffer[Integer] = new ArrayBuffer[Integer]
    for (elem <- array2) {
      buffer2.append(elem.toInt)
    }

    val array3: Array[String] = line3.split(",")
    val buffer3: ArrayBuffer[Integer] = new ArrayBuffer[Integer]
    for (elem <- array3) {
      buffer3.append(elem.toInt)
    }

    f(buffer2, buffer3)
  }

  def f(id: mutable.ArrayBuffer[Integer], score: mutable.ArrayBuffer[Integer]) = {

    val map: mutable.HashMap[Integer, ArrayBuffer[Integer]] = new mutable.HashMap[Integer, ArrayBuffer[Integer]]()
    for (i <- id.indices) {
      val key: Integer = id(i)
      val s: Integer = score(i)
      val scoreList: ArrayBuffer[Integer] = map.getOrElseUpdate(key, new ArrayBuffer[Integer])

      scoreList.append(s)
      map.update(key, scoreList)
    }

    val result = new mutable.HashMap[Integer, Integer]

    for (elem <- map) {
      val value: ArrayBuffer[Integer] = elem._2
      if (value.size >= 3 && !result.contains(elem._1)) {
        val integers: ArrayBuffer[Integer] = value.sortBy(a => a * -1)
        val sum: Int = integers(0) + integers(1) + integers(2)
        result.update(elem._1, sum)
      }
    }

    val array = result.toArray.sortBy(data => (-1 * data._2, -1* data._1)).map(_._1)

    println(array.mkString(","))
  }


}
