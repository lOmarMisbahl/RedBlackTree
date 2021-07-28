import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Scanner;
import static java.lang.System.exit;

public class Main {
    public static void main(String[] args) throws Exception{
        RedBlackTree t = new RedBlackTree();
        File file = new File("E:\\Assignments\\6th\\Red Black Tree\\search.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));
        String s;
        Node node;
        while ((s = br.readLine()) != null){
            node = new Node(null,s);
            t.insert(node);
        }
        br.close();
        Scanner sc = new Scanner(System.in);
        while (true){
            System.out.println("1-Insert a new word\n" +
                    "2-Print the tree size\n" +
                    "3-Search for a word\n" +
                    "4-Print Dictionary\n" +
                    "0-exit");
            int x = sc.nextInt();
            switch (x){
                case 1:
                    System.out.println("Enter a word: ");
                    sc.nextLine();
                    s = sc.nextLine();
                    node = new Node(null,s);
                    t.insert(node);
                    break;
                case 2:
                    System.out.println("Tree size: "+t.getSize()+"\n");
                    break;
                case 3:
                    System.out.println("Enter a word: ");
                    sc.nextLine();
                    s = sc.nextLine();
                    if(t.search(t.getRoot(),s)==1){
                        System.out.println("Word Found!\n");
                    }
                    else
                        System.out.println("Word Not Found!\n");
                    break;
                case 4:
                    t.inOrderTraversal(t.getRoot());
                    System.out.println("\n");
                    break;
                case 0:
                    exit(0);
                default:
                    System.out.println("Please enter a valid number!\n");
            }

        }

    }
}
