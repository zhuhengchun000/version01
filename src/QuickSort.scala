import java.text.SimpleDateFormat
import java.util.Date
import util.control.Breaks._

object QuickSort {
  def main(args: Array[String]): Unit = {
    //val arr = Array(-9,78,0,23,-567,70)
    val random = new util.Random()
    val arr1 = "1,2,3,4,5,9,7,8,10,8,11".split(",")
    val arr = arr1.map(_.toInt)
//    val arr = new Array[Int](80000000)
//    for (i <- 0 until 80000000) { //循环的生成随机数
//      arr(i) = random.nextInt(8000000)
//    }
    println("排序前")
    val now: Date = new Date()
    val dateFormat: SimpleDateFormat =
      new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
    val date = dateFormat.format(now)
    println("排序前时间=" + date) //输出时间
    println(arr.mkString(" "))
    quickSort(0, arr.length -1, arr) // 调用快排
    println("排序后")
    val now2: Date = new Date()
    val date2 = dateFormat.format(now2)
    println("排序后时间=" + date2) //输出时间
    println(arr.mkString(" "))
    //println(arr.mkString(" ")) // ( 0 )// -567 -9 0 23 78 70
  }
  /*
  1. left: 指定从数组的左边的下标 0
  2. right : 指定从数组的右边的下标 length -1
  3. arr : 进行排序的数组
  */
  def quickSort(left: Int, right: Int, arr: Array[Int]): Unit = {
    var l = left // 左下标
    var r = right // 右下标
    var pivot = arr((left + right) / 2) // 以中间的值为基准进行分割
    var temp = 0
    breakable {
      // while 语句的作用就是把比 pivot 小的数放到左边, 比 pivot 大的数放到右边
      while (l < r) {
        while (arr(l) < pivot) { //从左边找一个比 pivot 大的值对应下标  "1,2,3,4,5,9,7,8,10,8,11"
          l += 1
        }
        while (arr(r) > pivot) { //从右边找一个比 pivot 小的值对应下标
          r -= 1
        }
        if (l >= r) { // 说明本次交换结束,退出本次 while
          break()
        }
        var temp = arr(l) //交换
        arr(l) = arr(r)
        arr(r) = temp
        //处理后，如果发现 arr(l) == pivot 就 r - =1 , 提高效率
        if (arr(l) == pivot) {
          r -= 1
        }
        //
        if ((arr(r)) == pivot) {
          l += 1
        }
      }
    }
    if (l == r) { // 提高效率
      l += 1
      r -= 1
    }
    if (left < r) { //向左递归排序
      quickSort(left, r, arr)
    }
    if (right > l) {//向右递归排序
      quickSort(l, right, arr)
    }
  }
}
