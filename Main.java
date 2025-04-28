import java.util.Scanner;
import SectionB.AVLTree.*;
import SectionB.BinarySearchTree.BinarySearchTree;
import SectionB.RebBlackTree.RedBlackTree;
import SectionB.Btrees.*;
import SectionB.Btrees.Btrees;

public class Main {
    public static void main(String[] args) {

        //Instances of the objects
        Scanner userChoice = new Scanner(System.in);
        BinarySearchTree bst = new BinarySearchTree();
        AVLTree avltree = new AVLTree();
        RedBlackTree redBlackTree = new RedBlackTree();
  

        // Instances of the objects

        int choice;
        do {
            System.out
                    .println("==============================TREE IMPLEMENTATION MENU================================");
            System.out.println("1. Binary Search Tree");
            System.out.println("2. AVL");
            System.out.println("3. Red Black Tree");
            System.out.println("4. B-trees");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            choice = userChoice.nextInt();

            switch (choice) {
                case 1:
                    bstSub(userChoice, bst);
                    break;
                case 2:
                    AvlSub(userChoice, avltree);
                    break;
                case 3:
                    BlackTreeSub(userChoice, redBlackTree);
                    break;
                case 4:
                    BtreesSub(userChoice);
                    break;
                default:
                    break;
            }

        } while (choice != 5);

    }

    public static void bstSub(Scanner input, BinarySearchTree bst) {
        int bstChoice;

        do {
            System.out.println("\n========================Binary Search Tree=============================");
            System.out.println("0. Create New List");
            System.out.println("1. Insert");
            System.out.println("2. Delete ");
            System.out.println("3. Print Inorder");
            System.out.println("4. Print Postorder");
            System.out.println("5. Print Preorder");
            System.out.println("6. Back to Menu");
            System.out.println("Enter Choice");
            bstChoice = input.nextInt();

            switch (bstChoice) {
                case 0:
                    System.out.print("How many elements do you want to insert?: ");
                    int count = input.nextInt();
                    int[] list = new int[count];

                    System.out.println("\nEnter your list: ");
                    for (int i = 0; i < count; i++) {
                        list[i] = input.nextInt();
                    }

                    bst.createNewList(list);
                    break;

                case 1:
                    System.out.println("Enter the number to be inserted: ");
                    int theNumberToInsert = input.nextInt();
                    bst.insert(theNumberToInsert);
                    break;

                case 2:

                    System.out.println("Enter number to Delete: ");
                    int numberToDelete = input.nextInt();
                    bst.delete(numberToDelete);
                    break;
                case 3:

                    bst.inOrderTraversal();
                    break;
                case 4:
                    bst.postOrderTraversal();
                    break;
                case 5:
                    bst.preOrderTraversal();
                    break;
                default:
                    break;
            }
        } while (bstChoice != 6);

    }



    public static void AvlSub(Scanner input, AVLTree avltree) {
        int avlChoice;

        do {

            System.out.println("\n======================AVL Trees=============================");
            System.out.println("0. Create List ");
            System.out.println("1. Insert");
            System.out.println("2. Delete ");
            System.out.println("3. Print Inorder");
            System.out.println("4. Print Postorder");
            System.out.println("5. Print Preorder");
            System.out.println("6. Back to Menu");
            System.out.println("Enter Choice");
            avlChoice = input.nextInt();

            switch (avlChoice) {
                case 0:
                    System.out.println("How many elements do you want to insert? ");
                    int count = input.nextInt();
                    int[] list = new int[count];

                    System.out.println("\nEnter your list: ");
                    for (int i = 0; i < count; i++) {
                        list[i] = input.nextInt();
                    }
                    avltree.createNewList(list);
                    break;

                case 1:
                    System.out.println("Enter number to be inserted: ");
                    int theNumberToInsert = input.nextInt();
                    avltree.insert(theNumberToInsert);
                    break;
                case 2:
                    System.out.println("Enter the number to be deleted : ");
                    int theNumberToDelete = input.nextInt();
                    avltree.delete(theNumberToDelete);
                    break;

                case 3:
                    System.out.println("Inorder traversal: \n");
                    avltree.inOrder();
                    break;

                case 4:
                    System.out.println("Postorder traversal: \n");
                    avltree.postOrder();
                    break;

                case 5:
                    System.out.println("Preorder traversal: \n");
                    avltree.preOrder();
                    break;
                default:
                    break;
            }

        } while (avlChoice != 6);
    }

    public static void BtreesSub(Scanner input) {
        int btreeChoice;

        System.out.println("Enter the minimum degree for the B-tree (this will be used to govern the size of the node):  ");
        int minimumDegree = input.nextInt();
        
        Btrees btrees = new Btrees(minimumDegree);

        do {
            System.out.println("\n============================= B-Trees ==================================");
            System.out.println("0. Create New List");
            System.out.println("1. Insert");
            System.out.println("2. Delete ");
            System.out.println("3. Print Inorder");
            System.out.println("4. Print Postorder");
            System.out.println("5. Print Preorder");
            System.out.println("6. Back to Menu");
            System.out.println("Enter Choice");
            btreeChoice = input.nextInt();

            switch(btreeChoice) {
                case 0:

                System.out.println("How many Elements do you want to insert?: ");
                int keepCount = input.nextInt();
                int[] elements = new int[keepCount];

                System.out.println("\nEnter the List of elements: ");
                for (int i = 0; i < keepCount; i ++ ){
                    elements[i] = input.nextInt();
                }

                btrees.insertList(elements);
                break;

                case 1:
                System.out.print("Enter the number to be inserted: ");
                int elementToInsert = input.nextInt();
                btrees.insert(elementToInsert);
                break;
                case 3:
                System.out.println("Inorder traversal:");
                btrees.traverse(); 
                break;
                default:
                System.out.println("Invalid choice. Please try again.");
                break;
                    
            }


        } while (btreeChoice != 6);
    }

    public static void BlackTreeSub(Scanner input, RedBlackTree redBlackTree) {
        int blacktreeChoice;

        do {

            System.out
                    .println("\n============================= Black and Red Trees ==================================");
            System.out.println("1. Insert");
            System.out.println("2. Delete ");
            System.out.println("3. Print Inorder");
            System.out.println("4. Print Postorder");
            System.out.println("5. Print Preorder");
            System.out.println("6. Back to Menu");
            System.out.println("Enter Choice: ");
            blacktreeChoice = input.nextInt();

            switch (blacktreeChoice) {
                case 1:
                    System.out.print("How many elements do you want to insert?: ");
                    int count = input.nextInt();
                    int[] list = new int[count];

                    System.out.print("\nEnter your list: ");
                    for (int i = 0; i < count; i++) {
                        list[i] = input.nextInt();
                    }

                    for (int value: list){
                        redBlackTree.insert(value);
                    }
                    System.out.print("Elements inserted into the Red-Black Tree.");

                    break;

                case 2:
                    System.out.print("Enter the number to be inserted :");
                    int deleteThatNumber = input.nextInt();
                    redBlackTree.delete(deleteThatNumber);
                    break;

                case 3:
                    redBlackTree.inorder();
                    break;

                case 4:
                    redBlackTree.postorder();
                    break;
                case 5:
                    redBlackTree.preorder();
                    break;
            }

        } while (blacktreeChoice != 6);

    }

}