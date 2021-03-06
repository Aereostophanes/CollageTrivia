import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

class Controller implements ActionListener {
    private View view;

    Controller(View v) {
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
            view.leaderBoardScreen("Start");
        } else if(e.getActionCommand().equals(Constants.ACTIONS.LEADERBOARD_CATEGORY.name())) {
            view.leaderBoardScreen(b.getText());
        } else if (e.getActionCommand().equals(Constants.ACTIONS.SETTINGS.name())) {
            if (view.isTimerDisabled()) {
                view.settings("Enable Timer");
            } else {
                view.settings("Disable Timer");
            }
        } else if (e.getActionCommand().equals(Constants.ACTIONS.START.name())) {
            view.initializeCategories();
        } else if (e.getActionCommand().equals(Constants.ACTIONS.USERNAME.name())) {
            view.userNameScreen();
        } else if (e.getActionCommand().equals(Constants.ACTIONS.HOME.name())) {
            view.initialize();
        } else if(e.getActionCommand().equals(Constants.ACTIONS.DISABLE.name())) {
            view.disableTimer();
        } else if (e.getActionCommand().equals(Constants.ACTIONS.ADD_SCORE.name())) {
            view.addScore();
        }
    }
}