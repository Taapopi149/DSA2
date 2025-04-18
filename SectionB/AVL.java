package SectionB;


public class AVL {
     
    // Node structure
    class Node {
        
        int value;
        int height;
        Node left;
        Node right;
        
        public Node(int value) {
            this.value = value;
            this.height = 1;
            this.left = null;
            this.right = null;
        }
    }

    //return height of node 
    int Height(Node key){
        if (key == null) return 0;
        else return key.height;
    }

    //computes the balance factor
    int balance(Node key) {
        if(key == null ) return 0;
        else return (Height(key.right) - Height(key.left));
    }

    //UpdateHeight updates the hieght of the node
    void UpdateHeight(Node curnode) {

        // l - left, r - right
        int l = Height(curnode.left);
        int r = Height(curnode.right);

        curnode.height = Math.max(l, r) + 1;
    }

    //left rotation
    Node rotateLeft(Node x) {
        
        Node y = x.right;
        Node T2 = y.left;

        y.left = x;
        x.right = T2;

        UpdateHeight(x);
        UpdateHeight(y);

        return y;

    }

    //right rotate
    Node rotateRight(Node y){
        Node x = y.left;
        Node T2 = x.right;

        x.right = y;
        y.left = T2;

        UpdateHeight(y);
        UpdateHeight(x);

        return x;
    }

    

}
