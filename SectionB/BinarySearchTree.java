package SectionB;

public class BinarySearchTree {

    // Node class
    class Node {
        int data;
        Node left, right;

        public Node(int data) {
            this.data = data;
            this.left = null;
            this.right = null;
        }
    }

    private Node root;

    // Constructor
    public BinarySearchTree() {
        root = null;
    }

    // Create a new tree from a given array
    public void createNewList(int[] elements) {
        root = null;
        for (int val : elements) {
            root = insert(root, val);
        }
        System.out.println("New list created and inserted into BST.");
    }

    // Public insert method
    public void insert(int data) {
        root = insert(root, data);
        System.out.println("Inserted node " + data + " into BST.");
    }

    // Internal recursive insert
    private Node insert(Node node, int data) {
        if (node == null) return new Node(data);
        if (data < node.data)
            node.left = insert(node.left, data);
        else if (data > node.data)
            node.right = insert(node.right, data);
        return node;
    }

    // Public delete method
    public void delete(int key) {
        root = delete(root, key);
        System.out.println("Deleted node " + key + " from BST.");
    }

    // Internal recursive delete
    private Node delete(Node node, int key) {
        if (node == null) return null;

        if (key < node.data) {
            node.left = delete(node.left, key);
        } else if (key > node.data) {
            node.right = delete(node.right, key);
        } else {
            // Node to be deleted found
            if (node.left == null) return node.right;
            if (node.right == null) return node.left;

            // Node with two children: get inorder successor
            node.data = minValue(node.right);
            node.right = delete(node.right, node.data);
        }

        return node;
    }

    // Find minimum value (used in delete)
    private int minValue(Node node) {
        int min = node.data;
        while (node.left != null) {
            node = node.left;
            min = node.data;
        }
        return min;
    }

    // Postorder traversal
    public void postOrderTraversal() {
        System.out.print("Postorder Traversal: ");
        postOrder(root);
        System.out.println();
    }

    private void postOrder(Node node) {
        if (node == null) return;
        postOrder(node.left);
        postOrder(node.right);
        System.out.print(node.data + " ");
    }

    // Inorder traversal
    public void inOrderTraversal() {
        System.out.print("Inorder Traversal: ");
        inOrder(root);
        System.out.println();
    }

    private void inOrder(Node node) {
        if (node == null) return;
        inOrder(node.left);
        System.out.print(node.data + " ");
        inOrder(node.right);
    }

    // Preorder traversal
    public void preOrderTraversal() {
        System.out.print("Preorder Traversal: ");
        preOrder(root);
        System.out.println();
    }

    private void preOrder(Node node) {
        if (node == null) return;
        System.out.print(node.data + " ");
        preOrder(node.left);
        preOrder(node.right);
    }
}
