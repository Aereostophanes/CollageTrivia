import java.util.Random;

class Question {
    private String question;
    private Answer[] answers;

    Question(String q, String[] a) {
        question = q;
        answers = new Answer[Constants.NUM_ANSWERS];

        for (int i = 0; i < Constants.NUM_ANSWERS; i++) {
            if (i == 0) {
                answers[i] = new Answer(a[i], true);
            } else {
                answers[i] = new Answer(a[i], false);
            }
        }
        randomizeAnswers();
    }

    String getQuestion() {
        return question;
    }

    Answer[] getAnswers() {
        return answers;
    }

    private void randomizeAnswers() {
        Random rand = new Random();
        int end = Constants.NUM_ANSWERS - 1;

        while (end > 0) {
            int index = rand.nextInt(end + 1);
            // Swap the random index with the end of the array
            Answer temp = answers[index];
            answers[index] = answers[end];
            answers[end] = temp;
            // Decrement the end variable to decrease the questions that get checked
            end--;
        }
    }
 }
