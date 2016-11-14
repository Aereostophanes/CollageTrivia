import javax.swing.*;
import java.awt.*;

public class Answer extends JButton {
    private String answer;
    private boolean isCorrect;

    public Answer(String a, boolean c) {
        answer = a;
        isCorrect = c;

        setActionCommand(Constants.ACTIONS.ANSWER.name());
        setText(a);

        // Set the style of the button
        setFont(new Font("Arial", Font.BOLD, 14));
        setBackground(Color.BLUE);
        setForeground(Color.BLACK);
        setFocusPainted(false);
        setBorderPainted(false);
        //setOpaque(true);
    }


    public String getAnswer() {
        return answer;
    }

    public boolean getIsCorrect() { return isCorrect; }
}
