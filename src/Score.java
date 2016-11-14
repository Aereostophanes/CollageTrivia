public class Score {
    private int score = 0;

    public int getScore() { return score; }

    public void updateScore() { score += 10; }

    public void reset() { score = 0; }
}
