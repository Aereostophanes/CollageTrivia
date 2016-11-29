class User {
    private int userScore;
    private String userName;
    private String userCategory;

    User(int s, String n, String c) {
        userScore = s;
        userName = n;
        userCategory = c;
    }

    int getUserScore() { return userScore; }

    String getUserName() { return userName; }

    String getUserCategory() { return userCategory; }
}
