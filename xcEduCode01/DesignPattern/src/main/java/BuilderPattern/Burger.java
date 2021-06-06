package BuilderPattern;

public abstract class Burger implements Item{
    public Packing packing(){
        return new Wragger();
    }
    public abstract float price();
}
