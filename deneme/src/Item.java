public  class Item {

    public String getName() {
        return name;
    }

    private String name;

    public Item(String name, String description) {
        this.name = name;
        this.description = description;
    }

    private String description;

}
