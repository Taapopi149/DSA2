package SectionB.RebBlackTree;

enum Color {RED, BLACK}

public class Node {
    int data;
    Color color;
    Node left, right, parent;

    Node(int data) {
        this.data = data;
        this.color = Color.RED;
        this.left = this.right = this.parent = null;
    }
}
