import java.util.Scanner;
import SectionB.*;


public class Main {
    public static void main(String[] args) {

        Scanner userChoice = new Scanner(System.in);
        BinarySearchTree bst = new BinarySearchTree();
        //Instances of the objects

        int choice;
        do {
        System.out.println("==============================TREE IMPLEMENTATION MENU================================");
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
                AvlSub(userChoice);
                break;
            case 3:
                BlackTreeSub(userChoice);
                break;
            case 4:
                BtreesSub(userChoice);
                break;     
            default:
                break;
        }

        } while (choice !=5);
        
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
                    System.out.println("How many elements do you want to insert? ");
                    int count = input.nextInt();
                    int[] list = new int[count];
    
                    System.out.println("\nEnter your list: ");
                    for (int i = 0; i < count; i++) {
                        list[i] = input.nextInt();
                    }
    
                    bst.createNewList(list);
                    break;
    
                case 1:
                    System.out.println("Enter the number to be inserted :");
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
        }
        while(bstChoice !=6);

       

    }

    public static void AvlSub (Scanner input) {
        int avlChoice;

        do {

            System.out.println("\n======================AVL Trees=============================");
            System.out.println("1. Insert");
            System.out.println("2. Delete ");
            System.out.println("3. Print Inorder");
            System.out.println("4. Print Postorder");
            System.out.println("5. Print Preorder");
            System.out.println("6. Back to Menu");
            System.out.println("Enter Choice");
            avlChoice = input.nextInt();



            
        } while (avlChoice != 6);
    }
    
    public static void BtreesSub (Scanner input) {
        int btreeChoice;

        do {
            System.out.println("\n============================= B-Trees ==================================");
            System.out.println("1. Insert");
            System.out.println("2. Delete ");
            System.out.println("3. Print Inorder");
            System.out.println("4. Print Postorder");
            System.out.println("5. Print Preorder");
            System.out.println("6. Back to Menu");
            System.out.println("Enter Choice");
            btreeChoice = input.nextInt();
    

        } while (btreeChoice != 6);
    }
 
    public static void BlackTreeSub (Scanner input) {
        int blacktreeChoice;

        do {
            
            System.out.println("\n============================= Black and Red Trees ==================================");
            System.out.println("1. Insert");
            System.out.println("2. Delete ");
            System.out.println("3. Print Inorder");
            System.out.println("4. Print Postorder");
            System.out.println("5. Print Preorder");
            System.out.println("6. Back to Menu");
            System.out.println("Enter Choice: ");
            blacktreeChoice = input.nextInt();

        } while (blacktreeChoice != 6);


    }
    
}