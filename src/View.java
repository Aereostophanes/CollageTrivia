import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class View extends JFrame {
    private Model model;
    private Controller controller;
    private JPanel screen;
    private Timer timer;
    private int time;
    private int progress;
    private JProgressBar progressBar;
    public boolean disabled = false;

    public View() {
        controller = new Controller(this);
        initialize();
        if (!disabled) {
            timer = new Timer(1000, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (time == 1) {
                        Answer[] answers = model.getCurrentCategory().getCurrentQuestion().getAnswers();
                        Answer answer = answers[0];
                        for (Answer a : model.getCurrentCategory().getCurrentQuestion().getAnswers()) {
                            if (!a.isCorrect()) {
                                answer = a;
                                break;
                            }
                        }
                        goToNextQuestion(answer);
                    } else {
                        time -= 1;
                        model.getScore().setTimerScore(time);

                        Component[] components = screen.getComponents();
                        for (Component c : components) {
                            if (c instanceof GameTime) {
                                screen.remove(c);
                                break;
                            }
                        }
                        screen.add(new GameTime(Integer.toString(time), JLabel.CENTER));
                        getContentPane().removeAll();
                        getContentPane().add(screen);
                        revalidate();
                    }
                }
            });
        } else {
            Answer[] answers = model.getCurrentCategory().getCurrentQuestion().getAnswers();
            Answer answer = answers[0];
            for (Answer a : model.getCurrentCategory().getCurrentQuestion().getAnswers()) {
                if (!a.isCorrect()) {
                    answer = a;
                    break;
                }
            }
            goToNextQuestion(answer);
            getContentPane().removeAll();
            getContentPane().add(screen);
            revalidate();

        }
    }

    public boolean isTimerDisabled() { return disabled; }

    public void initialize() {
        time = Constants.MAX_TIME + 1;
        progress = 0;
        screen = new JPanel();
        progressBar = new JProgressBar();
        progressBar.setValue(progress);
        JButton start = new JButton("Start");
        start.setActionCommand(Constants.ACTIONS.START.name());
        JButton settings = new JButton("Settings");
        settings.setActionCommand(Constants.ACTIONS.SETTINGS.name());

        start.addActionListener(controller);
        settings.addActionListener(controller);

        screen.add(start);
        screen.add(settings);

        setTitle("Collage!");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(Constants.WIDTH, Constants.HEIGHT);

        getContentPane().removeAll();
        getContentPane().add(screen);
        revalidate();

        if (!isShowing()) {
            add(screen);
            setVisible(true);

            if (timer != null) {
                timer.stop();
                time = Constants.MAX_TIME;
            }
        }
    }

    public void settings(String action) {
        screen = new JPanel();
        JButton home = new JButton("Back");
        home.setActionCommand(Constants.ACTIONS.HOME.name());
        home.addActionListener(controller);
        JButton time = new JButton(action);

        time.setActionCommand(Constants.ACTIONS.DISABLE.name());
        time.addActionListener(controller);

        screen.add(home);
        screen.add(time);

        setTitle("Settings!");

        getContentPane().removeAll();
        getContentPane().add(screen);
        revalidate();
    }

    public void disableTimer() {
        if (!disabled) {
            disabled = true;
            settings("Enable Timer");
            JLabel label = new JLabel("Timer disabled.");
            screen.add(label);
        } else {
            disabled = false;
            settings("Disable Timer");
            JLabel label = new JLabel("Timer enabled.");
            screen.add(label);
        }
        getContentPane().removeAll();
        getContentPane().add(screen);
        revalidate();
    }

    public void initializeCategories() {
        screen = new JPanel();
        JButton[] categories = new JButton[Constants.NUM_CATEGORIES];
        for (int i = 0; i < Constants.NUM_CATEGORIES; i++) {
            categories[i] = new JButton(Constants.CATEGORY_NAMES[i]);
            categories[i].setActionCommand(Constants.ACTIONS.CATEGORY.name());
            categories[i].addActionListener(controller);
            screen.add(categories[i]);
        }
        getContentPane().removeAll();
        getContentPane().add(screen);
        revalidate();
    }

    public void startGame(String s) {
        int categoryIndex = 0;
        screen = new JPanel(new GridLayout(Constants.ROWS, Constants.COLS, 2, 2));

        if (s.equals(Constants.DISNEY)) {
            categoryIndex = Constants.CATEGORIES.DISNEY.getValue();
        } else if (s.equals(Constants.MANGA)) {
            categoryIndex = Constants.CATEGORIES.MANGA.getValue();
        } else if (s.equals(Constants.MUSIC)) {
            categoryIndex = Constants.CATEGORIES.MUSIC.getValue();
        } else if (s.equals(Constants.VEGAN)) {
            categoryIndex = Constants.CATEGORIES.VEGAN.getValue();
        } else if (s.equals(Constants.ANIMALS)) {
            categoryIndex = Constants.CATEGORIES.ANIMALS.getValue();
        } else if (s.equals(Constants.RANDOM)) {
            categoryIndex = Constants.CATEGORIES.RANDOM.getValue();
        }

        model = new Model(categoryIndex);
        Question question = model.getCurrentCategory().getCurrentQuestion();
        int questionIndex = model.getCurrentCategory().getCurrent();

        JLabel questionLabel = new JLabel(Integer.toString(questionIndex + 1) + ". " + question.getQuestion(), JLabel.CENTER);
        screen.add(questionLabel);

        for (Answer a : question.getAnswers()) {
            a.addActionListener(controller);
            screen.add(a);
        }

        screen.add(progressBar);
        screen.add(new GameTime(Integer.toString(Constants.MAX_TIME), JLabel.CENTER));

        if (!disabled) timer.start();

        getContentPane().removeAll();
        getContentPane().add(screen);
        revalidate();
    }

    public void goToNextQuestion(Answer answer) {
        if (answer.isCorrect()) {
            if (!disabled) {
                int current_score = model.getScore().getTimerScore();
                model.getScore().updateScore(current_score);
                model.getScore().updateCounter(current_score);
            } else {
                model.getScore().updateScore(10);
                model.getScore().updateCounter(10);
            }
            model.getScore().setTimerScore(10);
        } else {
            model.getScore().updateCounter(0);
            model.getScore().setTimerScore(10);
        }

        if (model.getCurrentCategory().getCurrent() == Constants.NUM_QUESTIONS_ROUND - 1) {
            displayScoreTable();
            getContentPane().removeAll();
            getContentPane().add(screen);
            revalidate();
        } else {
            time = Constants.MAX_TIME;
            screen = new JPanel(new GridLayout(Constants.ROWS, Constants.COLS, 2, 2));
            model.getCurrentCategory().nextQuestion();
            Question question = model.getCurrentCategory().getCurrentQuestion();
            int questionIndex = model.getCurrentCategory().getCurrent();

            JLabel questionLabel = new JLabel(Integer.toString(questionIndex + 1) + ". " + question.getQuestion(), JLabel.CENTER);
            screen.add(questionLabel);

            for (Answer a : question.getAnswers()) {
                a.addActionListener(controller);
                screen.add(a);
            }

            progress += (100 / Constants.NUM_QUESTIONS_ROUND);
            progressBar.setValue(progress);
            screen.add(progressBar);
            screen.add(new GameTime(Integer.toString(Constants.MAX_TIME), JLabel.CENTER));

            if (!disabled) timer.restart();

            getContentPane().removeAll();
            getContentPane().add(screen);
            revalidate();
        }
    }

    public void displayScoreTable() {
        screen = new JPanel(new FlowLayout());

        if (timer != null) {
            timer.stop();
            time = Constants.MAX_TIME;
        }

        String[][] data = new String[Constants.TABLE_ROWS][Constants.TABLE_COLS];
        String[] col = new String[]{"Question Number", "Question", "Score"};
        JTable table = new JTable(data, col);
        int count = 0;

        for (int row = 0; row < data.length; row++) {
            if (row == 0) {
                data[row][0] = "#";
                data[row][1] = "QUESTION";
                data[row][2] = "SCORE";
            } else if (row == data.length - 1) {
                data[row][0] = "Your Total Score Is:";
                data[row][1] = Integer.toString(model.getScore().getScore());
                if (model.getScore().getScore() >= Constants.THRESHOLD) {
                    data[row][2] = "  : - ) ";
                } else {
                    data[row][2] = "  : - ( ";
                }
            } else {
                data[row][0] = Integer.toString(count + 1);
                data[row][1] = model.getCurrentCategory().getQuestions()[count].getQuestion();
                data[row][2] = Integer.toString(model.getScore().getScoreArray().get(count));
                count++;
            }

            table.getColumnModel().getColumn(0).setPreferredWidth(Constants.QUESTION_NUM_WIDTH);
            table.getColumnModel().getColumn(1).setPreferredWidth(Constants.QUESTION_WIDTH);
            table.getColumnModel().getColumn(2).setPreferredWidth(Constants.SCORE_WIDTH);
            table.setRowHeight(row, Constants.ROW_HEIGHT);
        }
        table.setShowHorizontalLines(true);
        table.setShowVerticalLines(true);
        table.setGridColor(Color.PINK);

        JButton back = new JButton("Back");
        back.setActionCommand(Constants.ACTIONS.HOME.name());
        back.addActionListener(controller);

        JButton username = new JButton("Add Score to Leadership Board");
        username.setActionCommand(Constants.ACTIONS.USERNAME.name());
        username.addActionListener(controller);

        screen.add(table);
        screen.add(back);
        screen.add(username);
    }

    public void usernameScreen() {
        screen = new JPanel(new FlowLayout());
        String username;
        JTextField usertext = new JTextField(6);
        JButton addscore = new JButton("Add My Score");
        addscore.setActionCommand(Constants.ACTIONS.LEADERBOARD.name());
        addscore.addActionListener(controller);
        screen.add(usertext);
        screen.add(addscore);
        username = usertext.getText();
        getContentPane().removeAll();
        getContentPane().add(screen);
        revalidate();
    }

    public static void main(String[] args) { View v = new View(); }

    class GameTime extends JLabel {
        public GameTime(String t, int position) {
            super(t, position);
        }
    }
}
