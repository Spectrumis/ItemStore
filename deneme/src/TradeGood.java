public class TradeGood extends Item  {

    public TradeGood(String name, String description) {
        super(name, description);
    }
    @Override
    public String getName(){
        return super.getName() + "TradeGood";
    }
}

