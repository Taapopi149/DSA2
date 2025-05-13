

/*
 * 
When deleting we need to account for
-- Deleting from a leaf node
-- Deleting from internal node

-- B-Tree of order 't' (minimum degree)
-- Each node can have at most 2t - 1 keys, and 2t children

 * 
*/ 




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

 // ==================== used for Search =======================================================   
    public boolean search(int key) {
    if (root == null) {
        return false;
    }
    return search(root, key) != null;
}

// ================================== Insert Logic ==================================================

    //Insert a List
    //When a node has 2t - 1 keys, it is full and must be split before inserting more.

    public void insertList(int[] elements) {
        for (int element : elements) {
            insert(element); 
        }
        System.out.println("All elements inserted into the B-tree.");
    }
    


    //insert new Key
    public void insert(int key){
        
        if (root == null) {
            root = new BtreeNode(minimumDegree, true);
            root.keys[0] = key;
            root.n=1; //This line sets the number of keys currently present in the root node to 1.
        }  else {
            if (root.n == 2 * minimumDegree - 1) {
                BtreeNode newRoot = new BtreeNode(minimumDegree, false);
                newRoot.childern[0] = root; // old root becomes child
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


/*
 * 
Creating a new sibling node.
Moving the middle key up to the parent.
Splitting the keys and children between the old and new nodes.

 */


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

// =============================== delete Logic====================================================

public void delete(int key) {
    if (root == null) {
        System.out.println("The tree is empty.");
        return;
    }
    
    delete(root, key); // removes the key from the B-tree starting from the root.
    if (root.n == 0)  { // no keys left.
        if (root.leaf) {
            root = null;
        } else {
            root = root.childern[0]; // if the root is an internal node with no keys (which can happen if keys are borrowed or merged during deletion), we replace the root with its first child
        }
    }
    System.out.println("Element deleted from the B-tree.");
}

// Hepler Function 
private void delete(BtreeNode node, int key) {
    int idx = findKey(node, key);

    // Case 1: The key is in the node
    if (idx < node.n && node.keys[idx] == key) {
        if (node.leaf) {
            // Case 1a: Key is in a leaf node
            removeFromLeaf(node, idx);
        } else {
            // Case 1b: Key is in an internal node
            removeFromInternalNode(node, idx);
        }
    } else {
        // Key is not present in this node
        if (node.leaf) {
            System.out.println("The key is not in the tree.");
            return;
        }

        boolean flag = (idx == node.n); // if we are at the rightmost child
        BtreeNode child = node.childern[idx];
        
        if (child.n < minimumDegree) {
            fill(node, idx);
        } // child less then t keys, it underfilled, and we call fill(), Borrow from sibling, merge with a sibling.

        if (flag && idx > node.n) {
            delete(node.childern[idx - 1], key);
        } else {
            delete(node.childern[idx], key);
        }
    }
}


//Since leaf nodes do not have children, the process is simply about removing the key and shifting the remaining keys.
private void removeFromLeaf(BtreeNode node, int idx) {
    for (int i = idx + 1; i < node.n; i++) {
        node.keys[i - 1] = node.keys[i];
    }
    node.n--;
}

//This method is responsible for deleting a key from an internal node (not a leaf) while ensuring the B-tree remains valid after deletion.
private void removeFromInternalNode(BtreeNode node, int idx) {
    int key = node.keys[idx];
    if (node.childern[idx].n >= minimumDegree) {
        int pred = getPred(node, idx);
        node.keys[idx] = pred;
        delete(node.childern[idx], pred);
    } else if (node.childern[idx + 1].n >= minimumDegree) {
        int succ = getSucc(node, idx);
        node.keys[idx] = succ;
        delete(node.childern[idx + 1], succ);
    } else {
        merge(node, idx);
        delete(node.childern[idx], key);
    }
}
// The method is used when deleting a key from an internal node and that key has a left child.
private int getPred(BtreeNode node, int idx) {
    BtreeNode cur = node.childern[idx];
    while (!cur.leaf) {
        cur = cur.childern[cur.n];
    }
    return cur.keys[cur.n - 1];
}

//This method helps find the successor of the key to be deleted, which is needed when the key to be deleted is in an internal node (not a leaf).
private int getSucc(BtreeNode node, int idx) {
    BtreeNode cur = node.childern[idx + 1];
    while (!cur.leaf) {
        cur = cur.childern[0];
    }
    return cur.keys[0];
}

//This method determines how to fix the underfull node, either by borrowing a key from a sibling or by merging the child with its sibling.
private void fill(BtreeNode node, int idx) {
    if (idx != 0 && node.childern[idx - 1].n >= minimumDegree) {
        borrowFromPrev(node, idx);
    } else if (idx != node.n && node.childern[idx + 1].n >= minimumDegree) {
        borrowFromNext(node, idx);
    } else {
        if (idx != node.n) {
            merge(node, idx);
        } else {
            merge(node, idx - 1);
        }
    }
}

// This method handles the case when a node’s left sibling has extra keys and can lend one to an underfilled child.
private void borrowFromPrev(BtreeNode node, int idx) {
    BtreeNode child = node.childern[idx];
    BtreeNode sibling = node.childern[idx - 1];

    for (int i = child.n - 1; i >= 0; i--) {
        child.keys[i + 1] = child.keys[i];
    }
    if (!child.leaf) {
        for (int i = child.n; i >= 0; i--) {
            child.childern[i + 1] = child.childern[i];
        }
    }

    child.keys[0] = node.keys[idx - 1];
    if (!child.leaf) {
        child.childern[0] = sibling.childern[sibling.n];
    }
    node.keys[idx - 1] = sibling.keys[sibling.n - 1];
    child.n += 1;
    sibling.n -= 1;
}

// when a child node has fewer than the minimum number of keys (t - 1), and its right sibling has enough keys to lend one.
private void borrowFromNext(BtreeNode node, int idx) {
    BtreeNode child = node.childern[idx];
    BtreeNode sibling = node.childern[idx + 1];

    child.keys[child.n] = node.keys[idx];
    if (!child.leaf) {
        child.childern[child.n + 1] = sibling.childern[0];
    }

    node.keys[idx] = sibling.keys[0];

    for (int i = 1; i < sibling.n; i++) {
        sibling.keys[i - 1] = sibling.keys[i];
    }
    if (!sibling.leaf) {
        for (int i = 1; i <= sibling.n; i++) {
            sibling.childern[i - 1] = sibling.childern[i];
        }
    }

    child.n += 1;
    sibling.n -= 1;
}


// call this when a child and its immediate sibling both have only the minimum number of keys
private void merge(BtreeNode node, int idx) {
    BtreeNode child = node.childern[idx];
    BtreeNode sibling = node.childern[idx + 1];

    child.keys[minimumDegree - 1] = node.keys[idx];
    for (int i = 0; i < sibling.n; i++) {
        child.keys[i + minimumDegree] = sibling.keys[i];
    }
    if (!child.leaf) {
        for (int i = 0; i <= sibling.n; i++) {
            child.childern[i + minimumDegree] = sibling.childern[i];
        }
    }

    for (int i = idx + 1; i < node.n; i++) {
        node.keys[i - 1] = node.keys[i];
    }

    for (int i = idx + 2; i <= node.n; i++) {
        node.childern[i - 1] = node.childern[i];
    }

    child.n += sibling.n + 1;
    node.n--;
}

// ============================= Search ========================================================

private int findKey(BtreeNode node, int key) {
    int idx = 0;
    while (idx < node.n && node.keys[idx] < key) {
        idx++;
    }
    return idx;
}

// =============================== Printing Logic =================================================


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

private BtreeNode search(BtreeNode node, int key) {
    int i = 0;
    
    // Find the first key greater than or equal to key
    while (i < node.n && key > node.keys[i]) {
        i++;
    }

    // If the found key is equal to key, return this node
    if (i < node.n && node.keys[i] == key) {
        return node;
    }

    // If the key is not found and this is a leaf node
    if (node.leaf) {
        return null;
    }

    // Go to the appropriate child
    return search(node.childern[i], key);
}



}
