import scala.util.control.Breaks.{break, breakable}


object DoubleLinkedListDemo {
  def main(args: Array[String]): Unit = {
    //测试单向链表的添加和遍历
    val hero1 = new HeroNode2(1, "宋江", "及时雨")
    val hero2 = new HeroNode2(3, "宋江 3", "及时雨 3")
    val hero3 = new HeroNode2(4, "宋江 4", "及时雨 4")
    val hero4 = new HeroNode2(2, "宋江 2", "及时雨 2")
    //创建一个单向链表
    val doubleLinkedList = new DoubleLinkedList
    doubleLinkedList.add(hero1)
    doubleLinkedList.add(hero2)
    doubleLinkedList.add(hero3)
    doubleLinkedList.add(hero4)


    doubleLinkedList.list()


    val hero5 = new HeroNode2(2, "卢俊义", "玉麒麟")
    doubleLinkedList.update(hero5)
    println("--------------------------")
    doubleLinkedList.list()


    //删除测试
    doubleLinkedList.del(2)
    doubleLinkedList.del(3)
    doubleLinkedList.del(4)
    println("删除后")
    doubleLinkedList.list()
    //加入
    doubleLinkedList.add(hero2)
    println("~~~~~~~~~~~~")
    doubleLinkedList.list()
  }
}


class DoubleLinkedList {
  //先初始化一个头结点,  头结点一般不会动
  val head = new HeroNode2(0, "", "")

  //添加-遍历-修改-删除
  //编写添加方法
  //第一种方法在添加英雄时，直接添加到链表的尾部
  def add(heroNode: HeroNode2): Unit = {
    //因为头结点不能动,  因此我们需要哟有一个临时结点，作为辅助
    var temp = head
    //找到链表的最后
    breakable {
      while (true) {
        if (temp.next == null) { //说明 temp 已经是链表最后
          break()
        }
        //如果没有到最后
        temp = temp.next
      }
    }
    //当退出 while 循环后，temp 就是链表的最后
    temp.next = heroNode
    heroNode.pre = temp
  }


  //遍历方法一样,  不用
  def list(): Unit = {


    //判断当前链表是否为空


    if (head.next == null) {
      println("链表为空!!")
      return
    }
    //因为头结点不能动,  因此我们需要哟有一个临时结点，作为辅助
    //因为 head  结点数据，我们不关心，因此这里  temp=head.next
    var temp = head.next
    breakable {
      while (true) {
        //判断是否到最后
        if (temp == null) {
          break()
        }
        printf("结点信息  no=%d name=%s nickname=%s\n",
          temp.no, temp.name, temp.nickname)
        temp = temp.next
      }
    }
  }


  //修改节点的值,  根据 no 编号为前提修改,  即 no 不能改
  //课后思考题：  如果将这个节点替换，如何实现
  def update(newHeroNode: HeroNode2): Unit = {
    if (head.next == null) {
      println("链表为空")
      return
    }
    //先找到节点
    var temp = head.next
    var flag = false
    breakable {
      while (true) {
        if (temp == null) {
          break()
        }
        if (temp.no == newHeroNode.no) {
          //找到.
          flag = true
          break()
        }
        temp = temp.next //
      }
    }
    //判断是否找到
    if (flag) {
      temp.name = newHeroNode.name
      temp.nickname = newHeroNode.nickname
    } else {
      printf("没有找到  编号为%d  节点，不能修改\n", newHeroNode.no)
    }


  }


  //删除
  //思路，因为双向链表可以实现自我删除
  def del(no: Int): Unit = {
    //判断当前链表是否为空
    if (head.next == null) {
      println("链表空")
      return
    }

    var temp = head.next
    var flag = false //  标志变量用于确定是否有要删除的节点
    breakable {
      while (true) {
        if (temp == null) {
          break()
        }
        if (temp.no == no) {
          //找到了
          flag = true
          break()
        }
        temp = temp.next //temp 后移
      }
    }


    if (flag) {
      //可以删除
      //temp.next = temp.next.next
      temp.pre.next = temp.next
      //思考
      if (temp.next != null) {
        temp.next.pre = temp.pre
      }
    } else {
      printf("要删除的 no=%d  不存在\n", no)
    }
  }


}


//先创建 HeroNode
class HeroNode2(hNo: Int, hName: String, hNickname: String) {
  var no: Int = hNo
  var name: String = hName
  var nickname: String = hNickname
  var pre: HeroNode2 = null // pre  默认为 null
  var next: HeroNode2 = null //next  默认为 null
}
