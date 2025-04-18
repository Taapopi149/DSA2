package SectionB.AVLTree;

public class AVLTree {
    private AVLNode root;

    //   get the height of node
    private int height(AVLNode node){
        if (node == null) return 0;
        return node.height;
    }

    // Get balance factor
    private int getBalance(AVLNode N) {
        if (N == null)
            return 0;
        return height(N.left) - height(N.right);
    }


    // Right rotate
    private AVLNode rightRotation(AVLNode y ){
        AVLNode x = y.left;
        AVLNode T2 = x.right;

        x.right = y;
        y.left = T2;

        y.height = Math.max(height(y.left), height(y.right)) + 1;
        x.height = Math.max(height(x.left), height(x.right)) + 1;

        return x;
    }

    //    left rotation
    private  AVLNode leftRotation(AVLNode x){
        AVLNode y = x.right;
        AVLNode T2 = y.left;

        y.left = x;
        x.right = T2;

        x.height = Math.max(height(x.left), height(x.right)) + 1;
        y.height = Math.max(height(y.left), height(y.right)) + 1;

        return y;
    }

    // Insert node
    public void insert(int key) {
        root = insert(root, key);
    }

    private AVLNode insert(AVLNode node, int key){
        if(node == null)
            return new AVLNode(key);

        if (key < node.key)
            node.left = insert(node.left,key);
        else if (key > node.key)
            node.right = insert(node.right, key);
        else
            return node;

        node.height = 1 + Math.max(height(node.left), height(node.right));

        int balance= getBalance(node);

        // Rotate accordingly
        if (balance > 1 && key < node.left.key)
            return rightRotation(node);
        if (balance < -1 && key > node.right.key)
            return leftRotation(node);
        if (balance > 1 && key > node.left.key) {
            node.left = leftRotation(node.left);
            return rightRotation(node);
        }
        if (balance < -1 && key < node.right.key) {
            node.right = rightRotation(node.right);
            return leftRotation(node);
        }
        return node;
    }

    // Find min value node
    private AVLNode minValueNode(AVLNode node) {
        AVLNode current = node;
        while (current.left != null)
            current = current.left;
        return current;
    }

    private boolean contains(AVLNode node, int key){
        if (node == null)
            return false;
        if (key == node.key)
            return true;
        if (key < node.key)
            return contains(node.left, key);
        else
            return contains(node.right, key);
    }

    public void delete(int key){
        if (contains(root, key)){
            root = delete(root, key);
                System.out.println("Element " + key + " was deleted from the AVL Tree");
        }
        else {
            System.out.println("Element " + key +" was not found in the AVL tree");
        }
    }

    private AVLNode delete(AVLNode root, int key){
        if (root == null)
            return root;

        if(key < root.key)
            root.left = delete(root.left, key);
        else if (key > root.key)
            root.right = delete(root.right, key);
        else {
            if ((root.left == null) || (root.right == null)) {
                AVLNode temp = root.left != null ? root.left : root.right;

                if (temp == null) {
                    temp = root;
                    root = null;
                } else
                    root = temp;
            } else {
                AVLNode temp = minValueNode(root.right);
                root.key = temp.key;
                root.right = delete(root.right, temp.key);
            }
        }
        if (root == null)
            return root;

        root.height = Math.max(height(root.left), height(root.right)) +1;

        int balance = getBalance(root);

        if (balance > 1 && getBalance(root.left) >= 0)
            return rightRotation(root);
        if (balance > 1 && getBalance(root.left) < 0) {
            root.left = leftRotation(root.left);
            return rightRotation(root);
        }
        if (balance < -1 && getBalance(root.right) <= 0)
            return leftRotation(root);
        if (balance < -1 && getBalance(root.right) > 0) {
            root.right = rightRotation(root.right);
            return leftRotation(root);
        }

        return root;
    }

    //    inorder traversal
    public void inorderTraversal(){
        inorder(root);
        System.out.println();
    }

    private void inorder(AVLNode node) {
        if (node != null){
            inorder(node.left);
            System.out.println(node.key + " ");
            inorder(node.right);
        }
    }

//    postorder traversal
    public void postorderTraversal(){
        postorder(root);
        System.out.println();
    }

    private void postorder(AVLNode node) {
        if (node != null){
            postorder(node.left);
            postorder(node.right);
            System.out.println(node.key + " ");

        }
    }

    //    Preorder traversal
    public void preorderTraversal(){
        preorder(root);
        System.out.println();
    }

    private void preorder(AVLNode node) {
        if (node != null) {
            System.out.print(node.key + " ");
            preorder(node.left);
            preorder(node.right);
        }
    }
    
}
