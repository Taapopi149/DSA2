

/*
 * 
Maximum number of keys = m - 1
Maximum number of children = m

Minimum number of keys (except root) = ⌈(m-1)/2⌉
Minimum number of children (except root) = ⌈m/2⌉
 * 
*/

// B-Tree of order 't' (minimum degree)
// Each node can have at most 2t - 1 keys, and 2t children


package SectionB.Btrees;

class BtreeNode {
    //node stores an array of keys
    int[] keys;
    int minimumDegree; // range for numbers of key 
    BtreeNode[] childern; // child pointers
    int n; // current number of keys
    boolean leaf; //check if node is leaf

    public BtreeNode (int minimumDegree, boolean leaf) {
        
        this.minimumDegree = minimumDegree;
        this.leaf = leaf;
        this.keys = new int[2*minimumDegree -1]; // maximum keys in a Node
        this.childern = new BtreeNode[2*minimumDegree];
        this.n = 0;

    }

}

public class Btrees {
    
    BtreeNode root;
    int minimumDegree;

    public Btrees(int minimumDegree){

        this.root = null;
        this.minimumDegree = minimumDegree;
    }

    //Insert a List
    public void insertList(int[] elements) {
        for (int element : elements) {
            insert(element); // Use the existing insert method for individual elements
        }
        System.out.println("All elements inserted into the B-tree.");
    }
    


    //insert new Key
    public void insert(int key){
        
        if (root == null) {
            root = new BtreeNode(minimumDegree, true);
            root.keys[0] = key;
            root.n=1;
        }  else {
            if (root.n == 2 * minimumDegree - 1) {
                BtreeNode newRoot = new BtreeNode(minimumDegree, false);
                newRoot.childern[0] = root;
                splitChild(newRoot, 0 , root);
                insertNonFull(newRoot, key);
                root = newRoot;
                
            } else {
                insertNonFull(root, key);
            }
        }

        System.out.println("Element inserted into the B-tree.");

    }
// Helper function to insert a key into a non-full node
private void insertNonFull(BtreeNode node, int key) {
    int i = node.n - 1;  // Start from the rightmost key in the node
    if (node.leaf) {  // If the node is a leaf
        // Shift keys to the right to make space for the new key
        while (i >= 0 && key < node.keys[i]) {
            node.keys[i + 1] = node.keys[i];
            i--;
        }
        node.keys[i + 1] = key;  // Insert the key in the correct position
        node.n += 1;  // Increase the number of keys
    } else {  // If the node is not a leaf (internal node)
        // Find the child node where the key should be inserted
        while (i >= 0 && key < node.keys[i]) {
            i--;
        }
        i++;  // Now i is the child index where the key should go

        // If the child is full, split it
        if (node.childern[i].n == 2 * minimumDegree - 1) {
            splitChild(node, i, node.childern[i]);  // Split the child
            if (key > node.keys[i]) {  // After split, determine which child to use
                i++;
            }
        }
        
        // Recursively insert the key into the appropriate child
        insertNonFull(node.childern[i], key);
    }
}


    private void splitChild(BtreeNode parent, int index, BtreeNode fullChild){
        BtreeNode newChild = new BtreeNode(fullChild.minimumDegree, fullChild.leaf);
        newChild.n = minimumDegree -1;

        for (int j = 0; j < minimumDegree - 1; j++){
            newChild.keys[j] = fullChild.keys[j + minimumDegree];
        }

        if (!fullChild.leaf) {
            for (int j = 0; j < minimumDegree; j++) {
                newChild.childern[j] = fullChild.childern[j + minimumDegree];

            }
        }

        fullChild.n = minimumDegree - 1;

        for (int j = parent.n; j >= index + 1; j --) {
            parent.childern[j + 1] = parent.childern[j];

        }

        parent.childern[index + 1 ]= newChild;

        for (int j = parent.n -1; j >= index; j --) {
            parent.keys[j + 1] = parent.keys[j];
        }
        parent.keys[index] = fullChild.keys[minimumDegree - 1];
        parent.n += 1;

    }

        // In-Order travesal
        public void traverse() {
            if (root != null) traverse(root);
        }
    
        private void traverse(BtreeNode node) {
            int i;
            for (i = 0; i < node.n; i++) {
                if (!node.leaf) {
                    traverse(node.childern[i]);
                }
                System.out.print(node.keys[i] + " ");
            }
            if (!node.leaf) {
                traverse(node.childern[i]);
            }        
        }
// Preorder traversal
public void preorder() {
    if (root != null) {
        preorder(root);
    }
}

private void preorder(BtreeNode node) {
    System.out.print("Node: ");
    for (int i = 0; i < node.n; i++) {
        System.out.print(node.keys[i] + " ");
    }
    System.out.println();
    
    // Traverse children recursively
    for (int i = 0; i <= node.n; i++) {
        if (node.childern[i] != null) {
            preorder(node.childern[i]);
        }
    }
}

// Postorder traversal
public void postorder() {
    if (root != null) {
        postorder(root);
    }
}

private void postorder(BtreeNode node) {
    // Traverse children recursively
    for (int i = 0; i <= node.n; i++) {
        if (node.childern[i] != null) {
            postorder(node.childern[i]);
        }
    }
    
    // Print node keys after visiting children
    System.out.print("Node: ");
    for (int i = 0; i < node.n; i++) {
        System.out.print(node.keys[i] + " ");
    }
    System.out.println();
}


}
