import java.util.ArrayList;

public class Score {
    private int score = 0;
    private int timer_score = 10;


    public ArrayList<Integer> score_array = new ArrayList<Integer>();

    public void setTimerScore(int time) { timer_score = time; }

    public int getTimerScore() { return timer_score; }

    public int getScore() { return score; }

    public ArrayList<Integer> getScoreArray() { return score_array; }

    public void updateScore(int s) { score += s; }

    public void updateCounter(int score) { score_array.add(score); }
}
