import util.control.Breaks._

object ForBreak {
 def main(args: Array[String]): Unit = {
 //在 for 循环中，仍然使用 break() 中断
 breakable {
 for (i <- 1 to 900000) {
 if (i == 99) {
 break()
 }
 println("i=" + i)
 }
 }
 println("程序继续....")
 }
}