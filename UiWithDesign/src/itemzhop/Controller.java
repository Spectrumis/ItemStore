package itemzhop;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Controller {
    @FXML private VBox leftBox;
    @FXML private TableView<Item> storeTable ;
    @FXML private ListView<Item> listView;
    @FXML private TextField txt_search;
    ArrayList<Item> myItem=new ArrayList<>( );

    BinarySearchTree tree = new BinarySearchTree();
    ObservableList data = FXCollections.observableList(myItem);



    //storeTable içerisine items.csv içerisinde yer alan ürünlerin tamamını çekeceğiz ve her satırı bir eleman olarak ekleyeceğiz


    @FXML
    public void initialize() throws IOException {

        BufferedReader getUserData = new BufferedReader(new FileReader("items.csv"));
        String[] tmpItemStr;
        String line;
        Item tempItem;

        while ((line = getUserData.readLine()) != null) {
            tmpItemStr = line.split(",");
            tempItem=new Item();
            tempItem.name=tmpItemStr[0];
            tempItem.description=tmpItemStr[1];
            tempItem.defaultPrice=0;
            myItem.add(tempItem);
            //add to binary tree
            tree.insert(tempItem);
        }


        listView.setItems(data);

    }

    //on click list alpha button and list all items in auction house
    @FXML
    void listAlpha(){
        myItem.clear();
        tree.inorder();
        listView.refresh();
    }

    //click on search button and search this item in items list
    @FXML
    void searchItem() throws IOException {
        Item  item = tree.search(txt_search.getText());
        myItem.clear();
        myItem.add(item);

        listView.refresh();
    }

    // Java program to demonstrate insert operation in binary search tree
    class BinarySearchTree {

        /* Class containing left and right child of current node and key value*/
        class Node {
            Item key;
            Node left, right;

            public Node(Item item) {
                key = item;
                left = right = null;
            }
        }

        // Root of BST
        Node root;

        // Constructor
        BinarySearchTree() {
            root = null;
        }

        // This method mainly calls insertRec()
        void insert(Item key) {
            root = insertRec(root, key);
        }

        /* A recursive function to insert a new key in BST */
        Node insertRec(Node root, Item key) {

            /* If the tree is empty, return a new node */
            if (root == null) {
                root = new Node(key);
                return root;
            }

            /* Otherwise, recur down the tree */
            if (key.getName().compareTo(root.key.getName()) <0)
                root.left = insertRec(root.left, key);
            else if (key.getName().compareTo(root.key.getName())>0)
                root.right = insertRec(root.right, key);

            /* return the (unchanged) node pointer */
            return root;
        }

        // This method mainly calls InorderRec()
        void inorder()  {
            inorderRec(root);
        }

        // A utility function to do inorder traversal of BST
        void inorderRec(Node root) {
            if (root != null) {
                inorderRec(root.left);
                myItem.add(root.key);
                //System.out.println(root.key);
                inorderRec(root.right);
            }
        }

        Item search(String target){
            return search(root,  target);
        }

        Item search(Node root, String target){
            if (target.compareTo(root.key.getName()) < 0)
                return search(root.left, target);
            if (target.compareTo(root.key.getName()) > 0)
                return search(root.right, target);
            else
                return root.key;

        }
    }




    public void loadItems(ActionEvent actionEvent) throws IOException {
        Parent home_page_parent= FXMLLoader.load(getClass().getResource("profilePage.fxml"));
        Scene home_page_scene=new Scene(home_page_parent);
        Stage app_stage=(Stage)((Node) actionEvent.getSource()).getScene().getWindow();
        app_stage.hide();
        app_stage.setScene(home_page_scene);
        app_stage.show();


    }


}