
public class Main {
    //TODO gold operasyonları
    //TODO
    public static void main(String args[]) {
        Player sami = new Player(true,"Sami","05044014",2000);

        AuctionHouse market = new AuctionHouse();

        sami.sellItem(market,1,150,10);
    }

}
