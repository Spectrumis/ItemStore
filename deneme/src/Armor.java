public class Armor extends Item  {

    public Armor(String name, String description) {
        super(name, description);
    }
    @Override
    public String getName(){
        return super.getName() + "Armor";
    }
}
