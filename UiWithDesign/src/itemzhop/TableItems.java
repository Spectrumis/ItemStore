package itemzhop;

public class TableItems {
    public TableItems(String name, String seller, String time, int price) {
        this.name = name;
        this.seller = seller;
        this.time = time;
        this.price = price;
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

    public String getName() {
        return name;
    }

    public String seller;
    public String time;
    public int price;
    public String name;

}