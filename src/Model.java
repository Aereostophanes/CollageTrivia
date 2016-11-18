public class Model {
    private Score score;
    private Category[] categories;
    private int current;

    public Model(int c) {
        score = new Score();
        categories = new Category[Constants.NUM_CATEGORIES];
        current = c;

        String categoryNames[] = {Constants.DISNEY, Constants.MANGA, Constants.MUSIC, Constants.VEGAN, Constants.ANIMALS, Constants.RANDOM};
        for (int i = 0; i < Constants.NUM_CATEGORIES; i++) {
            categories[i] = new Category(categoryNames[i]);
        }
    }

    public Score getScore() { return score; }

    public Category getCurrentCategory() { return categories[current]; }
}
