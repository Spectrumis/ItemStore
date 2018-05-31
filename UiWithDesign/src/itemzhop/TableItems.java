package itemzhop;

public class TableItems {

    private String name;
    private String seller;
    private String time;
    private int price;
    private int maxPrice;


    public TableItems(String name, String seller, int price, int maxPrice, String time) {
        this.name = name;
        this.seller = seller;
        this.time = time;
        this.price = price;
        this.maxPrice = maxPrice;
    }



    public String getSeller() {
        return seller;
    }

    public String getTime() {
        return time;
    }

    public int getPrice() {
        return price;
    }

    public int getMaxPrice(){ return maxPrice; }

    public String getName() { return name;}

}