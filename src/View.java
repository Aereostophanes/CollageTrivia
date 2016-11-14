import com.sun.tools.internal.jxc.ap.Const;

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

    public View() {
        controller = new Controller(this);
        initialize();
        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (time == 0) {
                    Answer[] answers = model.getCurrentCategory().getCurrentQuestion().getAnswers();
                    Answer answer = answers[0];
                    for (Answer a : model.getCurrentCategory().getCurrentQuestion().getAnswers()) {
                        if (!a.getIsCorrect()) {
                            answer = a;
                            break;
                        }
                    }
                    goToNextQuestion(answer);
                } else {
                    time -= 1;
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
    }

    public Model getModel() { return model; }

    public void initialize() {
        time = Constants.MAX_TIME;
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

        add(screen);
        setVisible(true);

        if (timer != null) {
            timer.stop();
            time = Constants.MAX_TIME;
        }
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
        timer.start();

        getContentPane().removeAll();
        getContentPane().add(screen);
        revalidate();
    }

    public void goToNextQuestion(Answer answer) {
        if (answer.getIsCorrect()) model.getScore().updateScore();

        if (model.getCurrentCategory().getCurrent() == Constants.NUM_QUESTIONS_ROUND - 1) {
            System.out.println("The score is: " + model.getScore().getScore());
            initialize();
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
            timer.restart();

            getContentPane().removeAll();
            getContentPane().add(screen);
            revalidate();
        }
    }

    public static void main(String[] args) { View v = new View(); }

    class GameTime extends JLabel {
        public GameTime(String t, int position) {
            super(t, position);
        }
    }
}
