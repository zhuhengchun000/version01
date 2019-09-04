object ArrayTreeDemo {
  def main(args: Array[String]): Unit = {
    val arr = Array(1, 2, 3, 4, 5, 6, 7)


    val arrayTree = new ArrayTree(arr)


    //前序遍历我们的数组
    arrayTree.preOrder() //1,2,4,5,3,6,7
  }


}

/*
课后练习：请同学们完
成对数组以二叉树中序，

后序遍历方式的代码.


*/
class ArrayTree(val arr: Array[Int]) {


  //对 preOrder  进行一个重载
  def preOrder(): Unit = {

    this.preOrder(0)
  }


  def preOrder(index: Int): Unit = {
    if (arr == null || arr.length == 0) {
      println("数组为空，不能按照二叉树前序遍历")
    }
    println(arr(index))
    //向左递归遍历
    if ((index * 2 + 1) < arr.length) {
      preOrder(index * 2 + 1)
    }
    //向右边递归遍历
    if ((index * 2 + 2) < arr.length) {
      preOrder(index * 2 + 2)
    }
  }
}
