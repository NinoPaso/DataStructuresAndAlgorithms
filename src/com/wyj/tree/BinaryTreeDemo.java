package com.wyj.tree;

/**
 * @ClassName BinaryTree
 * @Description TODO:实现二叉树的前中后顺序遍历
 * @Author 86133
 * @Date 2020/10/6 11:44
 * @Version 1.0
 **/
public class BinaryTreeDemo {
    public static void main(String[] args) {
        // 创建节点 构造二叉树
        Node node1 = new Node(1,"a");
        Node node2 = new Node(2,"b");
        Node node3 = new Node(3,"c");
        Node node4 = new Node(4,"d");
        Node node5 = new Node(5,"e");

        BinaryTree tree = new BinaryTree(node1);
        node1.setLeft(node2);
        node1.setRight(node3);
        node3.setLeft(node4);
        node3.setRight(node5);

        tree.preOrder();
        System.out.println("-------------------------");
        tree.infixOrder();
        System.out.println("-------------------------");
        tree.postOrder();
        System.out.println("-------------------------");
        System.out.println(tree.preSerach(5));
        System.out.println(tree.infixSerach(2));
        System.out.println(tree.postSerach(1));
    }
}

/**
 * 二叉树的实现
 */
class BinaryTree {
    private Node root; //根节点

    // 构造方法
    public BinaryTree(Node root) {
        this.root = root;
    }

    // 调用节点的遍历方法
    public void preOrder() {
        if (null != this.root) {
            this.root.preOrder();
        } else {
            System.out.println("二叉树为空");
        }
    }

    public void infixOrder() {
        if (null != this.root) {
            this.root.infixOrder();
        } else {
            System.out.println("二叉树为空");
        }
    }

    public void postOrder() {
        if (null != this.root) {
            this.root.postOrder();
        } else {
            System.out.println("二叉树为空");
        }
    }

    public Node preSerach(int no) {
        return this.root.preSerach(no);
    }

    public Node infixSerach(int no) {
        return this.root.infixSerach(no);
    }
    public Node postSerach(int no) {
        return this.root.postSerach(no);
    }
}

/**
 * 二叉树的节点
 */
class Node {
    private int no;
    private String name;
    // 左右子节点
    private Node left;
    private Node right;

    // 构造方法
    public Node(int no, String name) {
        this.no = no;
        this.name = name;
    }

    // 前序遍历
    public void preOrder() {
        System.out.println(this);
        // 如果左子节点不为空
        if (this.left != null) {
            this.left.preOrder();// 左节点再充当父节点 依次递归
        }
        // 向右递归
        if (null != this.right) {
            this.right.preOrder();
        }
    }

    // 中序遍历
    public void infixOrder() {
        // 首先判断左子节点是否为空
        if (null != this.left) {
            this.left.infixOrder(); // 先向左不断递归 找到最左
        }
        System.out.println(this);
        // 左递归方法栈帧到底，继续pop反向递归右子节点
        if (null != this.right) {
            this.right.infixOrder();
        }
    }

    // 后序遍历 左右中
    public void postOrder() {
        // 首先判断左子节点是否为空
        if (null != this.left) {
            this.left.postOrder();
        }
        // 递归到底后 pop判断右子节点是否为空
        if (null != this.right) {
            this.right.postOrder();
        }
        System.out.println(this);
    }

    // 在前中后遍历排序的基础上进行查找
    public Node preSerach(int no) {
        if (this.no == no) { // 必须  它是中途的判断 即终止条件
            System.out.println("如果找到，我一定会执行...");
            return this;
        }
        // 定义返回节点
        Node resultNode = null;
        // 如果左子节点不为空
        if (this.left != null) {
            resultNode = this.left.preSerach(no);// 左节点再充当父节点 依次递归
        }
        if (null != resultNode) {

            return resultNode; // 这里的返回 返回最后不为空的时候的判断 在进入方法前还需要判断当前节点
        }
        // 向右递归
        if (null != this.right) {
            resultNode = this.right.preSerach(no);
        }
        return resultNode;
    }

    // 在前中后遍历排序的基础上进行查找
    public Node infixSerach(int no) {
        // 定义返回节点
        Node resultNode = null;
        // 如果左子节点不为空
        if (this.left != null) {
            resultNode = this.left.infixSerach(no);// 左节点再充当父节点 依次递归
        }
        if (null != resultNode) {
            return resultNode; // 这里的返回 返回最后不为空的时候的判断 在进入方法前还需要判断当前节点
        }
        if (this.no == no) { // 必须  它是中途的判断
            System.out.println("如果找到，我一定会执行...");
            return this;
        }
        // 向右递归
        if (null != this.right) {
            resultNode = this.right.infixSerach(no);
        }
        return resultNode;
    }

    // 在前中后遍历排序的基础上进行查找
    public Node postSerach(int no) {
        // 定义返回节点
        Node resultNode = null;
        // 如果左子节点不为空
        if (this.left != null) {
            resultNode = this.left.postSerach(no);// 左节点再充当父节点 依次递归
        }
        if (null != resultNode) {
            return resultNode; // 这里的返回 返回最后不为空的时候的判断 在进入方法前还需要判断当前节点
        }
        // 向右递归
        if (null != this.right) {
            resultNode = this.right.postSerach(no);
        }
        if (null != resultNode) {
            return resultNode; // 这里的返回 返回最后不为空的时候的判断 在进入方法前还需要判断当前节点
        }
        if (this.no == no) { // 必须  它是实际 节点判断 终止条件
            System.out.println("如果找到，我一定会执行...");
            return this;
        }
        return resultNode;
    }

    // toString方法
    @Override
    public String toString() {
        return "Node{" +
                "no=" + no +
                ", name='" + name + '\'' +
                '}';
    }

    // getter/setter

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }
}
