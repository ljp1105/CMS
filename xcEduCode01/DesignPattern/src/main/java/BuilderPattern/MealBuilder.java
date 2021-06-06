package BuilderPattern;

public class MealBuilder {
    public Model prepareVegMeal (){
        Model meal = new Model();
        meal.addItem(new VeBurger());
        meal.addItem(new Coke());
        return meal;
    }

    public Model prepareNonVegMeal (){
        Model meal = new Model();
        meal.addItem(new ChickenBurger());
        meal.addItem(new Pepsi());
        return meal;
    }
}
