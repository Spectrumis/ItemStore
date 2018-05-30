package itemzhop;

public class TableItems {
    public TableItems(String name, String seller, String time, int cost) {
        this.name = name;
        this.seller = seller;
        this.time = time;
        this.cost = cost;
    }

    public final String name;

    public String getName() {
        return name;
    }
    public String getSeller() {
        return seller;
    }
    public String getTime() {
        return time;
    }

    public final String seller;
    public final String time;
    public final int cost;
}