public class Weapon extends Item  {

    public Weapon(String name, String description) {
        super(name, description);
    }
    @Override
    public String getName(){
        return super.getName() + "Weapon";
    }
}

