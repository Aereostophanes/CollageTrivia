class Constants {
    // Number of questions in a round
    static int NUM_QUESTIONS_ROUND = 10;
    // Max number of users displayed in leaderboard
    static int SIZE_USERS = 20;
    // Max time per question
    static int MAX_TIME = 10;
    // Number of questions
    static int NUM_QUESTIONS = 25;
    // Number of questions - random
    static int NUM_QUESTIONS_RANDOM = 100;
    // Average score threshold
    static int THRESHOLD = 70;
    // Number of answers
    static int NUM_ANSWERS = 4;
    // Number of categories
    static int NUM_CATEGORIES = 6;
    // Path to the src directory
    static String PATH = System.getProperty("user.dir") + "/src/Resources/";
    // Categories
    static String RANDOM = "Random";
    static String DISNEY = "Disney";
    static String MANGA = "Manga";
    static String VEGAN = "Vegan";
    static String MUSIC = "Music";
    static String ANIMALS = "Animals";
    // Actions enum
    enum ACTIONS {
        ANSWER,
        START,
        SETTINGS,
        LEADERBOARD,
        CATEGORY,
        USERNAME,
        HOME,
        DISABLE,
        ADD_SCORE,
        LEADERBOARD_CATEGORY
    }
    // Categories enum
    enum CATEGORIES {
        DISNEY(0), MANGA(1), MUSIC(2), VEGAN(3), ANIMALS(4), RANDOM(5);
        private int value;
        CATEGORIES(int value) {
            this.value = value;
        }
        public int getValue() { return value; }
    }
    // Category names
    static String[] CATEGORY_NAMES = {"Disney", "Manga", "Music", "Vegan", "Animals", "Random"};
    // Rows and cols
    static int ROWS = 7;
    static int COLS = 1;
    // Size
    static int WIDTH = 1000;
    static int HEIGHT = 800;
    // Table size
    static int QUESTION_NUM_WIDTH = 200;
    static int QUESTION_WIDTH = 700;
    static int SCORE_WIDTH = 50;
    static int ROW_HEIGHT = 30;
    static int TABLE_ROWS = 12;
    static int TABLE_COLS = 3;
}