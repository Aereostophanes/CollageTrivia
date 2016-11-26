import java.util.ArrayList;

class Score {
    private int score = 0;
    private int timer_score = 10;


    private ArrayList<Integer> score_array = new ArrayList<Integer>();

    void setTimerScore(int time) { timer_score = time; }

    int getTimerScore() { return timer_score; }

    int getScore() { return score; }

    ArrayList<Integer> getScoreArray() { return score_array; }

    void updateScore(int s) { score += s; }

    void updateCounter(int score) { score_array.add(score); }
}
