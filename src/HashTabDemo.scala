package com.atguigu.chapter18.hashtab


import scala.io.StdIn
import util.control.Breaks._


object HashTabDemo {
  def main(args: Array[String]): Unit = {
    //创建 HashTab
    val hashTab = new HashTab(7)
    //写一个简单菜单
    var key = " "
    while (true) {
      println("add:	添加雇员")
      println("list:  显示雇员")

      println("find:  查找雇员")
      println("exit:  退出系统")


      key = StdIn.readLine()
      key match {
        case "add" => {
          println("输入 id")
          val id = StdIn.readInt()
          println("输入名字")
          val name = StdIn.readLine()
          val emp = new Emp(id, name)
          hashTab.add(emp)
        }
        case "find" => {
          println("输入要查找的雇员的 id")
          val id = StdIn.readInt()
          hashTab.findEmpById(id)
        }
        case "list" => {
          hashTab.list()
        }
      }


    }
  }
}


//创建 Emp 类
class Emp(eId: Int, eName: String) {
  val id = eId
  var name = eName
  var next: Emp = null
}


//创建 EmpLinkedList
class EmpLinkedList {
  //定义头指针,  这里 head  我们直接回指向一个雇员
  var head: Emp = null


  //添加雇员方法
  //假定，添加的雇员的 id 是自增的，即雇员分配的 id 总是从小到大
  //找到链表的最后加入即可
  def add(emp: Emp): Unit = {


    //如果是第一个雇员
    if (head == null) {
      head = emp
      return
    }
    //定义辅助指针
    var cur = head
    var pre = head
    var flag = false
    breakable {
      while (true) {
        if (cur.id < emp.id) {
          pre = cur
          cur = cur.next
        } else if (cur.id > emp.id) {
          flag = true
          break()
        } else {
          System.out.println("数据重复了不能添加")
          break()
        }
        if (cur == null) {
          flag = true
          break()
        }
      }
    }
    if (flag == true) {
      if (cur == head) {
        emp.next = head
        head = emp
      } else {
        emp.next = cur
        pre.next = emp
      }
    }

  }


  //遍历链表的方法
  def list(i: Int): Unit = {
    if (head == null) {
      println(s"第${i}条链表为空")
      return
    }


    print(s"第${i}条链表信息为\t")
    //定义辅助指针
    var cur = head
    breakable {
      while (true) {
        if (cur == null) {

          break()
        }
        //输出雇员信息
        printf(" => id=%d name=%s\t", cur.id, cur.name)
        cur = cur.next //
      }
    }
    println()
  }


  //如果有，返回 emp ,没有返回 null
  def findEmpById(id: Int): Emp = {
    //遍历
    if (head == null) {
      println("链表为空，没有数据~~")
      return null
    }


    var cur = head


    breakable {
      while (true) {
        if (cur == null) {
          break()
        }
        if (cur.id == id) {

          break()
        }
        cur = cur.next
      }
    }
    return cur
  }
}


//size = 700
class HashTab(val size: Int) { //size  会称为只读属性
  val empLinkedListArr: Array[EmpLinkedList] = new Array[EmpLinkedList](size)
  //初始化我们的 empLinkedListArr  的各个元素
  for (i <- 0 until size) {
    empLinkedListArr(i) = new EmpLinkedList
  }


  def add(emp: Emp): Unit = {
    //返回该员工，应该加入到那条链表
    val empLinkedListNo = hashFun(emp.id)
    empLinkedListArr(empLinkedListNo).add(emp)
  }


  def list(): Unit = { //遍历整个 hash 表
    for (i <- 0 until size) {
      empLinkedListArr(i).list(i)

    }
  }


  //编写一个 findEmpById
  def findEmpById(id: Int): Unit = {
    //返回该员工，应该加入到那条链表
    val empLinkedListNo = hashFun(id)
    val emp = this.empLinkedListArr(empLinkedListNo).findEmpById(id)
    if (emp != null) {
      printf(s"在第  $empLinkedListNo  找到 id=%d name=%s\n", id, emp.name)
    } else {
      printf("没有找到 id 为  %d \n", id)
    }
  }

  //散列函数,  可以定制
  def hashFun(id: Int): Int = {
    id % size
  }
}
