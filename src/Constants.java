public class Constants {
    // Number of questions in a round
    public static int NUM_QUESTIONS_ROUND = 10;
    // Max time per question
    public static int MAX_TIME = 10;
    // Number of questions
    public static int NUM_QUESTIONS = 25;
    // Number of questions - random
    public static int NUM_QUESTIONS_RANDOM = 100;
    // Number of answers
    public static int NUM_ANSWERS = 4;
    // Number of categories
    public static int NUM_CATEGORIES = 6;
    // Path to the src directory
    public static String PATH = System.getProperty("user.dir") + "/src/";
    // Categories
    public static String RANDOM = "Random";
    public static String DISNEY = "Disney";
    public static String MANGA = "Manga";
    public static String VEGAN = "Vegan";
    public static String MUSIC = "Music";
    public static String ANIMALS = "Animals";
    // Actions enum
    public enum ACTIONS {
        ANSWER,
        START,
        SETTINGS,
        LEADERBOARD,
        CATEGORY
    }
    // Categories enum
    public static enum CATEGORIES {
        DISNEY(0), MANGA(1), MUSIC(2), VEGAN(3), ANIMALS(4), RANDOM(5);
        private int value;
        CATEGORIES(int value) {
            this.value = value;
        }
        public int getValue() { return value; }
    }
    // Category names
    public static String[] CATEGORY_NAMES = {"Disney", "Manga", "Music", "Vegan", "Animals", "Random"};
    // Rows and cols
    public static int ROWS = 7;
    public static int COLS = 1;
    // SIze
    public static int WIDTH = 1000;
    public static int HEIGHT = 800;
}