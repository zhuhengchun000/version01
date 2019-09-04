import java.text.SimpleDateFormat
import java.util.Date


object InsertSort {
  def main(args: Array[String]): Unit = {


    //测试数组
    //val arr = Array(101, 34, 119, 1,-1, 45,900)

    val random = new util.Random()
    val arr = new Array[Int](80000)
    for (i <- 0 until 80000) { //循环的生成随机数
      arr(i) = random.nextInt(8000000)
    }

    println("排序前")
    val now: Date = new Date()
    val dateFormat: SimpleDateFormat =
      new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
    val date = dateFormat.format(now)
    println("排序前时间=" + date) //输出时间


    insertSort(arr) //调用插入排序


    println("插入排序后")


    val now2: Date = new Date()
    val date2 = dateFormat.format(now2)
    println("排序后时间=" + date2) //输出时间
    //println(arr.mkString(" "))


    //第 1 轮插入排序
    //(101), 34, 119, 1 => (34,101),119,1


    // (101, 101) 119, 1
    /*	var insertVal = arr(1)
    var insertIndex = 1 - 1 //  表示有序表的最后这个元素的下标


    //还没找到位置
    while (insertIndex >= 0 && insertVal < arr(insertIndex)) {

    更多 Java  –大数据  –前端  –python 人工智能  -区块链资料下载，可访问百度：尚硅谷官网	第 515 页









    尚硅谷 Scala 语言课程
    arr(insertIndex+1) = arr(insertIndex)
    insertIndex -= 1
    }
    //退出 while  说明插入的位置就找到了
    arr(insertIndex + 1) = insertVal


    println("第 1 轮的结果是")
    println(arr.mkString(" "))




    //第 2 轮
    insertVal = arr(2)
    insertIndex = 2 - 1 //  表示有序表的最后这个元素的下标


    //还没找到位置
    while (insertIndex >= 0 && insertVal < arr(insertIndex)) {
    arr(insertIndex+1) = arr(insertIndex)
    insertIndex -= 1
    }
    //退出 while  说明插入的位置就找到了
    arr(insertIndex + 1) = insertVal


    println("第 2 的结果是")
    println(arr.mkString(" "))





    更多 Java  –大数据  –前端  –python 人工智能  -区块链资料下载，可访问百度：尚硅谷官网	第 516 页









    尚硅谷 Scala 语言课程
    //第 3 轮
    insertVal = arr(3)
    insertIndex = 3 - 1 //  表示有序表的最后这个元素的下标


    //还没找到位置
    while (insertIndex >= 0 && insertVal < arr(insertIndex)) {
    arr(insertIndex+1) = arr(insertIndex)
    insertIndex -= 1
    }
    //退出 while  说明插入的位置就找到了
    arr(insertIndex + 1) = insertVal


    println("第 3 的结果是")
    println(arr.mkString(" "))*/


  }


  def insertSort(arr: Array[Int]): Unit = {
    //发现规律
    for (i <- 1 until arr.length) {
      var insertVal = arr(i)
      var insertIndex = i - 1 //  表示有序表的最后这个元素的下标


      //还没找到位置
      while (insertIndex >= 0 && insertVal < arr(insertIndex)) {
        arr(insertIndex + 1) = arr(insertIndex)

        insertIndex -= 1
      }
      //退出 while  说明插入的位置就找到了
      arr(insertIndex + 1) = insertVal


      //	println(s"第${i}轮的结果是")
      //	println(arr.mkString(" "))
    }
  }
}


/*
插入排序的思路:
插入排序（Insertion Sorting）的基本思想是：把 n 个待排序的元素看成为一个有序表和一个无序表，开始时有
序表中只包含一个元素，无序表中包含有 n-1 个元素，排序过程中每次从无序表中取出第一个元素，把它的排序码
依次与有序表元素的排序码进行比较，将它插入到有序表中的适当位置，使之成为新的有序表。


*/
