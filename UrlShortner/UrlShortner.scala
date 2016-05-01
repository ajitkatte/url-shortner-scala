import scala.util.control.Breaks

object UrlShortner {
  val alphabets : String = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789"
  val base = alphabets.length

  def encode(number : Int): String = {
    var encodeNumber = number
    if(encodeNumber == 0) return alphabets(0).toString
    val encodedUrl = new StringBuffer
    while (encodeNumber > 0){
      encodedUrl.insert(0,alphabets(encodeNumber % base))
      encodeNumber = encodeNumber / base
    }
    encodedUrl.toString
  }

  def decode(decodeInput : String): Int = {
    var decodedNumber : Int = 0
    for(c <- decodeInput){
      decodedNumber = (decodedNumber * base) + alphabets.indexOf(c)
    }
    decodedNumber
  }

  def main(args : Array[String]): Unit ={
    val breakable = new Breaks
    breakable.breakable {
      for (i <- 0 to 10000) {
        if (decode(encode(i)) != i) {
          println(s"Failed")
          breakable.break()
        }
      }
    }
  }
}
