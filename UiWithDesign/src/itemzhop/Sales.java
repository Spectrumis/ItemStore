package itemzhop;

import java.util.UUID;

/**
 * for each item's sale
 * each object shows a new item
 */
public  class Sales implements Comparable<Sales> {

    private int id;
    private int price;
    private int maxPrice;
    private int time;
    private String remainingTime;
    private String itemName;
    private String seller;
    private String lastBidder;


    private static int idStart = 1000;      //star from 1000 to INF


    public Sales(String itemName,
                 String seller,
                 int maxPrice){

        this.id = generateID();

        this.itemName = itemName;
        this.seller = seller;
        this.price = 0;
        this.maxPrice = maxPrice;
        this.remainingTime = Integer.toString(180000);        //2 days in seconds
        this.time = 180000;
        this.lastBidder = "nll";            //nll : empty
    }




    public Sales(int id,
                 String itemName,
                 String seller ,
                 int price,
                 int maxPrice,
                 int time,
                 String remainingTime,
                 String lastBidder) {
        this.id = id;
        this.itemName = itemName;
        this.seller = seller;
        this.price = price;
        this.maxPrice = maxPrice;
        this.time = time;
        this.remainingTime = remainingTime;
        this.lastBidder = lastBidder;
    }

    public Sales(String name) {
        this.itemName  = name;
    }


    //getters
    public int getId() {

        return id;
    }


    public int getTime() {

        return time;
    }


    public int getPrice() {

        return price;
    }


    public int getMaxPrice() {

        return maxPrice;
    }


    public String getRemainingTime() {

        return remainingTime;
    }


    public String getItemName() {

        return itemName;
    }


    public String getSeller() {

        return seller;
    }


    public String getLastBidder() {

        return lastBidder;
    }


    private int generateID() {
        return ++idStart;
    }

    //setter


    public void setPrice(int price) {

        this.price = price;
    }



    public void setRemainingTime(String remainingTime) {

        this.remainingTime = remainingTime;
    }



    public void setLastBidder(String lastBidder) {

        this.lastBidder = lastBidder;
    }


    @Override
    public String toString() {
        return  itemName.toString()+"  -  "+seller.toString()+"  -  "+ price + lastBidder.toString();
    }

    @Override
    public int compareTo(Sales o) {
        return this.getItemName().compareTo( o.getItemName() );
    }
    @Override
    public boolean equals(Object o){
        Sales sale = (Sales) o;
        System.out.println(this.getItemName());
        if(this.getItemName().equals(sale.getItemName())){
            System.out.println(this.getItemName());
            return true;
        }

        return false;
    }


}
