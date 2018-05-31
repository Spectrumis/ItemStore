package itemzhop;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.ComboBoxListCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.time.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

public class Controller {
    @FXML private VBox leftBox;
    @FXML private ComboBox<String> sortSelect = new ComboBox<String>();

    @FXML private TableView<TableItems> storeTable = new TableView<>() ;
    @FXML private ListView<Item> listView;

    @FXML private TextField txt_search;

    ArrayList<Sales> myItem=new ArrayList<>( );

    BinarySearchTree tree = new BinarySearchTree();
    ObservableList data = FXCollections.observableList(myItem);
    ObservableList<String> options = FXCollections.observableArrayList(
            "İsime Göre ",
                    "Fiyatı En Az Olan önce ",
                    "Fiyatı En Pahalı Olan",
                    "Zamanı En Az Kalan",
                    "Zamanı En Çok Olan"
            );
    @FXML
    private void comboAction(ActionEvent event) {

        System.out.println(sortSelect.getValue());
        //TODO burda olan seçime göre veri yapısı ve üzerinde arama algoritması çalışacak

    }


    private void createSortLists(){

        sortSelect.setItems(options);
        sortSelect.setCellFactory(ComboBoxListCell.forListView(options));
    }


    private void addItemsInTable(ObservableList<TableItems> data){

        TableColumn itemName = new TableColumn("Ürün Adi");
        itemName.setMinWidth(100);
        itemName.setCellValueFactory(new PropertyValueFactory<TableItems, String>("name"));

        TableColumn seller = new TableColumn("Satıcı");
        seller.setMinWidth(100);
        seller.setCellValueFactory(new PropertyValueFactory<TableItems, String>("seller"));

        TableColumn price = new TableColumn("Teklif");
        price.setMinWidth(20);
        price.setCellValueFactory(new PropertyValueFactory<TableItems, String>("price"));


        TableColumn maxPrice = new TableColumn("Son Fiyat");
        maxPrice.setMinWidth(20);
        maxPrice.setCellValueFactory(new PropertyValueFactory<TableItems, String>("maxPrice"));


        TableColumn time = new TableColumn("Kalan Süre");
        time.setMinWidth(50);
        time.setCellValueFactory(new PropertyValueFactory<TableItems, String>("time"));



        // Table cell coloring
        time.setCellFactory(new Callback<TableColumn<TableItems, String>, TableCell<TableItems, String>>() {
            @Override
            public TableCell<TableItems, String> call(TableColumn<TableItems, String> param) {
                return new TableCell<TableItems, String>() {

                    @Override
                    public void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        if (!isEmpty()) {
                            this.setTextFill(Color.RED);
                            // Get fancy and change color based on data
                            if(item.contains("@"))
                                this.setTextFill(Color.BLUEVIOLET);
                            setText(item);

                        }
                    }

                };
            }
        });



        storeTable.setEditable(true);
        storeTable.getColumns().addAll(itemName, seller, price, maxPrice, time);
        storeTable.getItems().addAll(data);


    }


    @FXML
    public void initialize() throws IOException {

         final ObservableList<TableItems> data =
                FXCollections.observableArrayList();

         Random rand = new Random();
        BufferedReader getUserData = new BufferedReader(new FileReader("sales.csv"));
        String[] tmpItemStr;
        String line;
        Sales Sale;

        while ((line = getUserData.readLine()) != null) {
            tmpItemStr = line.split(",");

            Sale = createSale(tmpItemStr);


            myItem.add(Sale);
            //add to binary tree
            data.add(new TableItems(Sale.getItemName(),
                                    Sale.getSeller(),
                                    Sale.getPrice(),
                                    Sale.getMaxPrice(),
                                    Sale.getRemainingTime()));
            tree.insert(Sale);
        }
        addItemsInTable(data);
        createSortLists();

    }


    /**
     * pars csv line and create Sales objecjt with this paramters
     * @param tmpItemStr a line of csve file
     * @return Sales object that a line for a item's sale
     */
    private Sales createSale(String[] tmpItemStr) {
        long timeData;
        int time;       //time left as integer in seconds
        int seconds = 0;
        int minutes = 0;
        int hours = 0;
        int days = 0;

        NumberFormat formatter = new DecimalFormat("00");       //for time column


        timeData = Long.parseLong(tmpItemStr[5]);      //time convertion

        long currentTime = (new Date()).getTime()/1000;


        timeData = timeData - currentTime;
        time = (int) timeData;
        if(timeData > 0) {

            seconds = (int) ((timeData) % 60);
            minutes = (int) ((timeData / 60) % 60);
            hours = (int) ((timeData / (60 * 60)) % 24);
            days = (int) ((timeData / (60 * 60 * 24)) % 7);
        }

        return new Sales(Integer.parseInt(tmpItemStr[0]),       //id
                tmpItemStr[1],                          //item name
                tmpItemStr[2],                          //seller
                Integer.parseInt(tmpItemStr[3]),        //price
                Integer.parseInt(tmpItemStr[4]),        //max price
                time,
                (days + " gün " + formatter.format(hours) + ":" +
                        formatter.format(minutes) + ":" + formatter.format(seconds)),         //time in seconds
                tmpItemStr[6]);                          //last bidder name
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
        Sales  Sale = tree.search(txt_search.getText());
        //Sales Sale = tree.search(txt_search.getText());
        myItem.clear();
        myItem.add(Sale);

        listView.refresh();
    }

    // Java program to demonstrate insert operation in binary search tree
    class BinarySearchTree {

        /* Class containing left and right child of current node and key value*/
        class Node {
            Sales key;
            Node left, right;

            public Node(Sales Sale) {
                key = Sale;
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
        void insert(Sales key) {
            root = insertRec(root, key);
        }

        /* A recursive function to insert a new key in BST */
        Node insertRec(Node root, Sales key) {

            /* If the tree is empty, return a new node */
            if (root == null) {
                root = new Node(key);
                return root;
            }

            /* Otherwise, recur down the tree */
            if (key.getItemName().compareTo(root.key.getItemName()) <0)
                root.left = insertRec(root.left, key);
            else if (key.getItemName().compareTo(root.key.getItemName())>0)
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

        Sales search(String target){
            return search(root,  target);
        }

        Sales search(Node root, String target){
            if (target.compareTo(root.key.getItemName()) < 0)
                return search(root.left, target);
            if (target.compareTo(root.key.getItemName()) > 0)
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