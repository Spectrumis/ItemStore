public class ConsumableItem extends Item  {

    public ConsumableItem(String name, String description) {
        super(name, description);
    }
    @Override
    public String getName(){
        return super.getName() + "ConsumableItems";
    }
}
