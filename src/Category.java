import java.io.*;
import java.nio.charset.Charset;
import java.util.Random;

public class Category {
    private String category;
    private Question[] questions;
    private int questionLength;
    private int current;

    public Category(String c) {
        category = c;
        current = 0;
        questionLength = c.equals(Constants.RANDOM) ? Constants.NUM_QUESTIONS_RANDOM : Constants.NUM_QUESTIONS;
        questions = new Question[questionLength];

        String s = Constants.PATH + c + ".txt";
        try (
                InputStream fis = new FileInputStream(s);
                InputStreamReader isr = new InputStreamReader(fis, Charset.forName("UTF-8"));
                BufferedReader br = new BufferedReader(isr);
        ) {
            int i = 0;
            String q;
            String[] a = new String[Constants.NUM_ANSWERS];
            String line = br.readLine();
            while (line != null && i < questionLength) {
                line = br.readLine();
                q = line;
                for (int j = 0; j < Constants.NUM_ANSWERS; j++) {
                    line = br.readLine();
                    a[j] = line;
                }
                questions[i] = new Question(q, a);
                i++;
            }
        } catch (IOException e) {
            System.err.println("Caught IOException: " + e.getMessage());
        }
        randomizeQuestions();
    }

    public Question getCurrentQuestion() {
        return questions[current];
    }

    public Question[] getQuestions() { return questions; }

    public int getCurrent() { return current; }

    public String getCategory() {
        return category;
    }

    public int getQuestionLength() {
        return questionLength;
    }

    public void nextQuestion() {
        current++;
    }

    public void randomizeQuestions() {
        Random rand = new Random();
        int end = questionLength - 1;

        while (end > 0) {
            int index = rand.nextInt(end);
            // Swap the random index with the end of the array
            Question temp = questions[index];
            questions[index] = questions[end];
            questions[end] = temp;
            // Decrement the end variable to decrease the questions that get checked
            end--;
        }
    }
}
