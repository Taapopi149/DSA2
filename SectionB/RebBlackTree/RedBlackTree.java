package SectionB.RebBlackTree;

public class RedBlackTree {

    private Node root;
    private final Node NIL;

    public RedBlackTree() {
        NIL = new Node(0);
        NIL.color = Color.BLACK;
        root = NIL;
    }

    //  left rotation
    private void leftRotation(Node x) {
        Node y = x.right;
        x.right = y.right;
        if (y.left != NIL) y.left.parent = x;
        y.parent = x.parent;
        if (x.parent == null) root = y;
        else if (x == x.parent.left) x.parent.left = y;
        else x.parent.right = y;
        y.left = x;
        x.parent = y;
    }

    //  right rotation
    private void rightRotation(Node y) {
        Node x = y.left;
        y.left = x.right;
        if (x.right != NIL) x.right.parent = y;
        x.parent = y.parent;
        if (y.parent == null) root = x;
        else if (y == y.parent.left) y.parent.left = x;
        else y.parent.right = x;
        x.right = y;
        y.parent = x;
    }

    public void insert(int key) {
        Node node = new Node(key);
        node.left = node.right = NIL;

        Node y = null;
        Node x = root;

        while (x != NIL) {
            y = x;
            if (node.data < x.data) x = x.left;
            else x = x.right;
        }

        node.parent = y;
        if (y == null) root = node;
        else if (node.data < y.data) y.left = node;
        else y.right = node;

        node.color = Color.RED;
        insertFix(node);
    }

    private void insertFix(Node k) {
        while (k.parent != null && k.parent.color == Color.RED) {
            if (k.parent == k.parent.parent.left) {
                Node u = k.parent.parent.right; // uncle
                if (u.color == Color.RED) {
                    k.parent.color = Color.BLACK;
                    u.color = Color.BLACK;
                    k.parent.parent.color = Color.RED;
                    k = k.parent.parent;
                } else {
                    if (k == k.parent.right) {
                        k = k.parent;
                        leftRotation(k);
                    }
                    k.parent.color = Color.BLACK;
                    k.parent.parent.color = Color.RED;
                    rightRotation(k.parent.parent);
                }
            } else {
                Node u = k.parent.parent.left;
                if (u.color == Color.RED) {
                    k.parent.color = Color.BLACK;
                    u.color = Color.BLACK;
                    k.parent.parent.color = Color.RED;
                    k = k.parent.parent;
                } else {
                    if (k == k.parent.left) {
                        k = k.parent;
                        rightRotation(k);
                    }
                    k.parent.color = Color.BLACK;
                    k.parent.parent.color = Color.RED;
                    leftRotation(k.parent.parent);
                }
            }
        }
        root.color = Color.BLACK;
    }

    // In-order traversal
    public void inorder() {
        inorderHelper(root);
        System.out.println();
    }

    private void inorderHelper(Node node) {
        if (node != NIL) {
            inorderHelper(node.left);
            System.out.print(node.data + " ");
            inorderHelper(node.right);
        }
    }

    //    Pre-order traversal
    public void preorder() {
        preorderHelper(root);
        System.out.println();
    }

    private void preorderHelper(Node node) {
        if (node != NIL) {
            System.out.print(node.data + " ");
            preorderHelper(node.left);
            preorderHelper(node.right);
        }
    }

    // Post-order traversal
    public void postorder() {
        postorderHelper(root);
        System.out.println();
    }

    private void postorderHelper(Node node) {
        if (node != NIL) {
            postorderHelper(node.left);
            postorderHelper(node.right);
            System.out.print(node.data + " ");
        }
    }

    // Transplant helper
    private void transplant(Node u, Node v) {
        if (u.parent == null) root = v;
        else if (u == u.parent.left) u.parent.left = v;
        else u.parent.right = v;
        v.parent = u.parent;
    }

    // Find minimum node
    private Node minimum(Node node) {
        while (node.left != NIL) node = node.left;
        return node;
    }

    // Delete node
    public void delete(int key) {
        Node z = root;
        while (z != NIL) {
            if (z.data == key) break;
            else if (key < z.data) z = z.left;
            else z = z.right;
        }

        if (z == NIL) {
            System.out.println("Key not found in the tree");
            return;
        }

        Node y = z;
        Color yOriginalColor = y.color;
        Node x;

        if (z.left == NIL) {
            x = z.right;
            transplant(z, z.right);
        } else if (z.right == NIL) {
            x = z.left;
            transplant(z, z.left);
        } else {
            y = minimum(z.right);
            yOriginalColor = y.color;
            x = y.right;
            if (y.parent == z) x.parent = y;
            else {
                transplant(y, y.right);
                y.right = z.right;
                y.right.parent = y;
            }

            transplant(z, y);
            y.left = z.left;
            y.left.parent = y;
            y.color = z.color;
        }

        if (yOriginalColor == Color.BLACK) deleteFix(x);
    }

    private void deleteFix(Node x) {
        while (x != root && x.color == Color.BLACK) {
            if (x == x.parent.left) {
                Node w = x.parent.right;
                if (w.color == Color.RED) {
                    w.color = Color.BLACK;
                    x.parent.color = Color.RED;
                    leftRotation(x.parent);
                    w = x.parent.right;
                }
                if (w.left.color == Color.BLACK && w.right.color == Color.BLACK) {
                    w.color = Color.RED;
                    x = x.parent;
                } else {
                    if (w.right.color == Color.BLACK) {
                        w.left.color = Color.BLACK;
                        w.color = Color.RED;
                        rightRotation(w);
                        w = x.parent.right;
                    }
                    w.color = x.parent.color;
                    x.parent.color = Color.BLACK;
                    w.right.color = Color.BLACK;
                    leftRotation(x.parent);
                    x = root;
                }
            } else {
                Node w = x.parent.left;
                if (w.color == Color.RED) {
                    w.color = Color.BLACK;
                    x.parent.color = Color.RED;
                    rightRotation(x.parent);
                    w = x.parent.left;
                }
                if (w.left.color == Color.BLACK && w.right.color == Color.BLACK) {
                    w.color = Color.RED;
                    x = x.parent;
                } else {
                    if (w.left.color == Color.BLACK) {
                        w.right.color = Color.BLACK;
                        w.color = Color.RED;
                        leftRotation(w);
                        w = x.parent.left;
                    }
                    w.color = x.parent.color;
                    x.parent.color = Color.BLACK;
                    w.left.color = Color.BLACK;
                    rightRotation(x.parent);
                    x = root;
                }
            }
        }
        x.color = Color.BLACK;
    }
}
