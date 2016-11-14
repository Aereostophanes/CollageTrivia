import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Controller implements ActionListener {
    private View view;

    public Controller(View v) {
        view = v;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        JButton b = (JButton) e.getSource();
        if (e.getActionCommand().equals(Constants.ACTIONS.ANSWER.name())) {
            view.goToNextQuestion((Answer) b);
        } else if (e.getActionCommand().equals(Constants.ACTIONS.CATEGORY.name())) {
            view.startGame(b.getText());
        } else if (e.getActionCommand().equals(Constants.ACTIONS.LEADERBOARD.name())) {

        } else if (e.getActionCommand().equals(Constants.ACTIONS.SETTINGS.name())) {
            //view.print("SETTINGS");
        } else if (e.getActionCommand().equals(Constants.ACTIONS.START.name())) {
            view.initializeCategories();
        }
    }
}
