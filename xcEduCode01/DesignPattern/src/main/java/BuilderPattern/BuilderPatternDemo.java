package BuilderPattern;

public class BuilderPatternDemo {
    public static void main(String[] args) {
        MealBuilder mealBuilder = new MealBuilder();
        Model vegMeal = mealBuilder.prepareNonVegMeal();
        vegMeal.showItems();


        MealBuilder mealBuilder1 = new MealBuilder();
        Model vegMeal1 = mealBuilder.prepareVegMeal();
        vegMeal.showItems();
    }
}
