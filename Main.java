import java.util.Scanner;
import SectionB.AVLTree.AVLTree;
import SectionB.BinarySearchTree.BinarySearchTree;
import SectionB.RebBlackTree.RedBlackTree;
import SectionB.Btrees.Btrees;

public class Main {

    public static void main(String[] args) {
        Scanner userChoice = new Scanner(System.in);

        BinarySearchTree bst = new BinarySearchTree();
        AVLTree avlTree = new AVLTree();
        RedBlackTree redBlackTree = new RedBlackTree();

        int choice;
        do {
            System.out.println("\n============================== TREE MENU ==============================");
            System.out.println("1. Binary Search Tree");
            System.out.println("2. AVL Tree");
            System.out.println("3. Red-Black Tree");
            System.out.println("4. B-Tree");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            choice = userChoice.nextInt();

            switch (choice) {
                case 1 -> bstSub(userChoice, bst);
                case 2 -> avlSub(userChoice, avlTree);
                case 3 -> redBlackTreeSub(userChoice, redBlackTree);
                case 4 -> bTreeSub(userChoice);
                case 5 -> System.out.println("Exiting the program. Goodbye!");
                default -> System.out.println("Invalid choice. Please try again.");
            }

        } while (choice != 5);

        userChoice.close();
    }

    // Reusable utility to get list from user
    private static int[] getListInput(Scanner input, int count) {
        int[] list = new int[count];
        System.out.println("Enter the elements:");
        for (int i = 0; i < count; i++) {
            list[i] = input.nextInt();
        }
        return list;
    }

    public static void bstSub(Scanner input, BinarySearchTree bst) {
        int bstChoice;
        do {
            System.out.println("\n=========== Binary Search Tree ===========");
            System.out.println("0. Create New List");
            System.out.println("1. Insert");
            System.out.println("2. Delete");
            System.out.println("3. Inorder Traversal");
            System.out.println("4. Postorder Traversal");
            System.out.println("5. Preorder Traversal");
            System.out.println("6. Back to Main Menu");
            System.out.print("Enter choice: ");
            bstChoice = input.nextInt();

            switch (bstChoice) {
                case 0 -> {
                    System.out.print("How many elements to insert? ");
                    int[] list = getListInput(input, input.nextInt());
                    bst.createNewList(list);
                }
                case 1 -> {
                    System.out.print("Enter element to insert: ");
                    bst.insert(input.nextInt());
                }
                case 2 -> {
                    System.out.print("Enter element to delete: ");
                    bst.delete(input.nextInt());
                }
                case 3 -> bst.inOrderTraversal();
                case 4 -> bst.postOrderTraversal();
                case 5 -> bst.preOrderTraversal();
                case 6 -> System.out.println("Returning to main menu...");
                default -> System.out.println("Invalid choice.");
            }

        } while (bstChoice != 6);
    }

    public static void avlSub(Scanner input, AVLTree avlTree) {
        int avlChoice;
        do {
            System.out.println("\n=========== AVL Tree ===========");
            System.out.println("0. Create New List");
            System.out.println("1. Insert");
            System.out.println("2. Delete");
            System.out.println("3. Inorder Traversal");
            System.out.println("4. Postorder Traversal");
            System.out.println("5. Preorder Traversal");
            System.out.println("6. Back to Main Menu");
            System.out.print("Enter choice: ");
            avlChoice = input.nextInt();

            switch (avlChoice) {
                case 0 -> {
                    System.out.print("How many elements to insert? ");
                    int[] list = getListInput(input, input.nextInt());
                    avlTree.createNewList(list);
                }
                case 1 -> {
                    System.out.print("Enter element to insert: ");
                    avlTree.insert(input.nextInt());
                }
                case 2 -> {
                    System.out.print("Enter element to delete: ");
                    avlTree.delete(input.nextInt());
                }
                case 3 -> avlTree.inOrder();
                case 4 -> avlTree.postOrder();
                case 5 -> avlTree.preOrder();
                case 6 -> System.out.println("Returning to main menu...");
                default -> System.out.println("Invalid choice.");
            }

        } while (avlChoice != 6);
    }

    public static void redBlackTreeSub(Scanner input, RedBlackTree rbTree) {
        int rbChoice;
        do {
            System.out.println("\n=========== Red-Black Tree ===========");
            System.out.println("1. Insert");
            System.out.println("2. Delete");
            System.out.println("3. Inorder Traversal");
            System.out.println("4. Postorder Traversal");
            System.out.println("5. Preorder Traversal");
            System.out.println("6. Back to Main Menu");
            System.out.print("Enter choice: ");
            rbChoice = input.nextInt();

            switch (rbChoice) {
                case 1 -> {
                    System.out.print("How many elements to insert? ");
                    int[] list = getListInput(input, input.nextInt());
                    for (int value : list) {
                        rbTree.insert(value);
                    }
                }
                case 2 -> {
                    System.out.print("Enter element to delete: ");
                    rbTree.delete(input.nextInt());
                }
                case 3 -> rbTree.inorder();
                case 4 -> rbTree.postorder();
                case 5 -> rbTree.preorder();
                case 6 -> System.out.println("Returning to main menu...");
                default -> System.out.println("Invalid choice.");
            }

        } while (rbChoice != 6);
    }

    public static void bTreeSub(Scanner input) {
        System.out.print("Enter the minimum degree (t) of the B-tree: ");
        int t = input.nextInt();
        Btrees bTree = new Btrees(t);
        int bChoice;

        do {
            System.out.println("\n=========== B-Tree ===========");
            System.out.println("0. Create New List");
            System.out.println("1. Insert");
            System.out.println("2. Delete");
            System.out.println("3. Inorder Traversal");
            System.out.println("4. Postorder Traversal");
            System.out.println("5. Preorder Traversal");
            System.out.println("6. Search");
            System.out.println("7. Back to Main Menu");
            System.out.print("Enter choice: ");
            bChoice = input.nextInt();

            switch (bChoice) {
                case 0 -> {
                    System.out.print("How many elements to insert? ");
                    int[] elements = getListInput(input, input.nextInt());
                    bTree.insertList(elements);
                }
                case 1 -> {
                    System.out.print("Enter element to insert: ");
                    bTree.insert(input.nextInt());
                }
                case 2 -> {
                    System.out.print("Enter element to delete: ");
                    bTree.delete(input.nextInt());
                }
                case 3 -> bTree.traverse();
                case 4 -> bTree.postorder();
                case 5 -> bTree.preorder();
                case 6 -> {
                    System.out.print("Enter element to search: ");
                    int key = input.nextInt();
                    boolean found = bTree.search(key);
                    if (found) {
                        System.out.println("Element " + key + " found in B-tree.");
                    } else {
                        System.out.println("Element " + key + " not found in B-tree.");
                    }

                }
                case 7 -> System.out.println("Returning to main menu...");
                default -> System.out.println("Invalid choice.");
            }

        } while (bChoice != 6);
    }
}
