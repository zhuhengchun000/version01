
//Array(7, 3, 10, 12, 5, 1, 9)	做成一个二叉排序树
object BinarySortTreeDemo {
  def main(args: Array[String]): Unit = {


    //测试一把
    val arr = Array(7, 3, 10, 12, 5, 1, 9, 2)
    //创建一颗二叉排序树
    val binarySortTree = new BinarySortTree
    for (item <- arr) {
      binarySortTree.add(new Node(item))
    }
    //遍历二叉排序树


    binarySortTree.infixOrder() // 1,3,5,7,9,10,12


    //删除
    //	binarySortTree.delNode(2)
    //	binarySortTree.delNode(5)
    //	binarySortTree.delNode(9)
    //	binarySortTree.delNode(12)


    binarySortTree.delNode(10)


    println("删除后~~~")
    binarySortTree.infixOrder()
  }
}


//定义节点
class Node(var value: Int) {
  var left: Node = null
  var right: Node = null


  //查找某个节点,根据值
  def search(value: Int): Node = {
    //先判断当前节点是否是要删除的节点
    if (value == this.value) {
      return this

    } else if (value < this.value) { //向左去找
      if (this.left == null) {
        return null
      } else {
        return this.left.search(value)
      }
    } else {
      if (this.right == null) {
        return null
      } else { //递归向右子树查找
        return this.right.search(value)
      }
    }


  }


  //找某个节点的父节点
  def searchParent(value: Int): Node = {
    //思路
    //1.  先判断当前节点的左子节点或者右子节点是否是这个值
    if ((this.left != null && this.left.value == value) ||
      (this.right != null && this.right.value == value)) {
      return this

    } else {
      if (this.left != null && value < this.value) { //说明需要向左边去递归查找
        return this.left.searchParent(value)
      } else if (this.right != null && value > this.value) { //说明需要向右边去递归查找
        return this.right.searchParent(value)
      } else {
        null
      }
    }
  }


  //添加方法
  def add(node: Node): Unit = {
    if (node == null) { //如果节点为空，返回
      return
    }
    //如果要插入的节点的值小于当前节点的值
    if (node.value < this.value) {
      if (this.left == null) {
        //说明该节点下没有左子节点
        this.left = node
      } else {
        //递归的进行插入
        this.left.add(node)
      }
    } else { //如果要插入的节点的值不小于当前节点的值

      if (this.right == null) {
        this.right = node
      } else {
        //递归的进行插入
        this.right.add(node)
      }
    }
  }


  //中序遍历
  def infixOrder(): Unit = {
    //向左递归输出左子树
    if (this.left != null) {
      this.left.infixOrder()
    }
    //先输出当前节点值
    printf("节点信息  value=%d \n", value)
    //向右边递归输出右子树
    if (this.right != null) {
      this.right.infixOrder()
    }


  }
}


//定义我们的二叉排序树

class BinarySortTree {
  var root: Node = null


  //删除某个右子树的最小值的节点，并返回最小值
  def delRightTreeMin(node: Node): Int = {
    var target = node
    //使用 while  循环找到右子树的最小值
    while (target.left != null) {
      target = target.left
    }
    val minValue = target.value
    //删除最小值对应的节点
    delNode(minValue)
    return minValue
  }


  //查找节点
  def search(value: Int): Node = {
    if (root != null) {
      return root.search(value)
    } else {
      return null
    }
  }


  //查找父节点的方法
  def searchParent(value: Int): Node = {
    if (root != null) {
      return root.searchParent(value)
    } else {
      return null
    }
  }


  //删除节点


  def delNode(value: Int): Unit = {
    if (root == null) { //如果是空树，就不删除
      return
    }
    //先看有没有要删除节点
    var targetNode = search(value)
    if (targetNode == null) { //没有要删除的节点，就直接返回
      return
    }
    //查找 targetNode  的父节点
    var parentNode = searchParent(value)
    //1.  先考虑的是叶子节点
    if (targetNode.left == null && targetNode.right == null) {
      //判断删除的节点是 parentNode 的左子节点，还是右子节点
      if (parentNode.left != null && parentNode.left.value == value) {

        parentNode.left = null
      } else {
        parentNode.right = null
      }
    } else if (targetNode.left != null && targetNode.right != null) { // targetNode 只有两个子节点
      val value = delRightTreeMin(targetNode.right)
      targetNode.value = value


    } else { //只有 targetNode 只有一个子节点
      //判断 targetNode  是 parentNode  的左子节点还是右子节点
      if (targetNode.left != null) { //要删除的节点的左子节点不为空
        //判断 targetNode  是 parentNode  的左还是右
        if (parentNode.left.value == value) {
          parentNode.left = targetNode.left
        } else {
          parentNode.right = targetNode.left
        }
      } else {
        //判断 targetNode  是 parentNode  的左还是右
        if (parentNode.left.value == value) {
          parentNode.left = targetNode.right
        } else {
          parentNode.right = targetNode.right
        }
      }


    }


  }


  def add(node: Node): Unit = {
    if (root == null) { //空树
      root = node
    } else {
      root.add(node)
    }
  }


  //中序遍历
  def infixOrder(): Unit = {
    if (root != null) {
      root.infixOrder()
    } else {
      println("当前二叉树为空，不能遍历")
    }
  }
}
