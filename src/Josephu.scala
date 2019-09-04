import util.control.Breaks._


object Josephu {
  def main(args: Array[String]): Unit = {
    //创建 BoyGame
    val boyGame = new BoyGame
    boyGame.addBoy(70)
    boyGame.showBoy()
    //测试 countBoy 方法
    boyGame.countBoy(41, 30, 70)
  }
}


//定义 Boy 类
class Boy(bNo: Int) {
  val no: Int = bNo
  var next: Boy = null
}


//编写核心类 BoyGame
class BoyGame {
  //定义一个初始的头结点,
  var first: Boy = new Boy(-1)

  //添加小孩【形成一个单向环形的链表】
  //nums  ：  表示共有几个小孩
  def addBoy(nums: Int): Unit = {
    if (nums < 1) {
      println("nums 的值不正确")
      return
    }
    //在形成环形链表时，需要一个辅助指针
    var curBoy: Boy = null


    for (no <- 1 to nums) {
      //更加编号创建小孩对象

      val boy = new Boy(no)
      //如果是第一个小孩
      if (no == 1) {
        first = boy
        first.next = first //  形成一个环形的链表
        curBoy = first
      } else {
        curBoy.next = boy
        boy.next = first
        curBoy = boy
      }
    }
  }


  //编写方法 countBoy,  完成游戏
  //startNo  从第几个人开始数
  //countNum  数几下
  //nums:  一共多少人
  def countBoy(startNo: Int, countNum: Int, nums: Int): Unit = {
    //对参数进行判断
    if (first.next == null || startNo < 1 || startNo > nums) {
      println("参数有误，重新输入!")
      return
    }
    //完成游戏的思路

    /*
    完成游戏的思路分析->实现代码


    1)  在 first  前面  设计一个辅助指针（helper）  ,  即将 helper  指针定位到  first  前面
    2)  将 first  指针移动到  startNo	这个小孩(helper  对应移动)
    3)  开始数  countNum  个数[first  和  helper  会对应的移动]
    4)  删除 first  指向的这个小孩节点
    5)  思路


    */
    var helper = first
    //1)即将 helper  指针定位到  first  前面
    breakable {
      while (true) {
        if (helper.next == first) {
          break()
        }
        helper = helper.next
      }
    }
    //2)将 first  指针移动到  startNo	这个小孩(helper  对应移动)
    for (i <- 0 until startNo - 1) {
      first = first.next
      helper = helper.next
    }


    //  开始数数，按照给定的值，每数到一个小孩就出圈,  直到环形链表只有一个节点
    breakable {
      while (true) {
        if (helper == first) {
          //只有一个人
          break()
        }
        //3)  开始数  countNum  个数[first  和  helper  会对应的移动]
        for (i <- 0 until countNum - 1) { // 3
          first = first.next
          helper = helper.next
        }
        //输出出圈的人的信息
        printf("小孩%d 出圈\n", first.no)
        //将 first  指向的节点删除
        first = first.next
        helper.next = first
      }
    }
    //当 while 结束后,  只有一个人
    printf("最后留在圈的人是  小孩编号为  %d\n", first.no)


  }


  //遍历单向的环形链表

  def showBoy(): Unit = {
    if (first.next == null) {
      println("没有任何小孩~")
      return
    }
    //因为 first 不能动，还是借助一个辅助指针完成遍历
    var curBoy = first


    breakable {
      while (true) {
        printf("小孩编号  %d\n", curBoy.no)
        if (curBoy.next == first) {
          break()
        }
        curBoy = curBoy.next //curBoy 后移
      }
    }
  }
}
