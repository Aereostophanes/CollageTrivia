public class User {
    private int userScore;
    private String userName;
    private String userCategory;

    public User(int s, String n, String c) {
        userScore = s;
        userName = n;
        userCategory = c;
    }

    public int getUserScore() { return userScore; }

    public String getUserName() { return userName; }

    public String getUserCategory() { return userCategory; }
}
