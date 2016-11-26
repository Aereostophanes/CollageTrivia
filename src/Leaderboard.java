import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;

class Leaderboard {
    private HashMap<String, ArrayList<User>> leaderBoard = new HashMap<>();

    Leaderboard() {
        for (String s : Constants.CATEGORY_NAMES) {
            leaderBoard.put(s, new ArrayList<>());
        }
    }

    void addUser(User u) {
        ArrayList<User> users = leaderBoard.get(u.getUserCategory());
        users.add(u);
        Collections.sort(users, (o1, o2) -> o2.getUserScore() - o1.getUserScore());
        leaderBoard.put(u.getUserCategory(), users);
    }

    ArrayList<User> getUsersByCategory(String c) { return leaderBoard.get(c); }

    public ArrayList<User> getAllUsers() {
        ArrayList<User> users = new ArrayList<>();

        for (String s : Constants.CATEGORY_NAMES) {
            for (User u : leaderBoard.get(s)) {
                users.add(u);
            }
        }

        return users;
    }

    HashMap<String, ArrayList<User>> getLeaderBoard() { return leaderBoard; }
}
