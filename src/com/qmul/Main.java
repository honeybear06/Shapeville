package com.qmul;

import com.qmul.ui.*;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * Main application class for Shapeville - an educational geometry learning system.
 * This class serves as the entry point and manages navigation between different panels
 * and question screens of the application.
 */
public class Main {

    private static final Map<Integer, QuestionPanel1> questionPanelCache1 = new HashMap<>();
    private static final Map<Integer, QuestionPanel4> questionPanelCache4 = new HashMap<>();
    private static final Map<Integer, QuestionPanel3> questionPanelCache3 = new HashMap<>();

    public static int totalScore1;
    public static int finishedQuizCount1;

    public static int finishedQuizCount2;

    public static int totalScore3;
    public static int finishedQuizCount3;

    public static int totalScore4;
    public static int finishedQuizCount4;

    public static int totalScore5;
    public static int finishedQuizCount5;

    public static int totalScore6;
    public static int finishedQuizCount6;


    /**
     * Application entry point. Sets the default locale to English and launches the UI.
     *
     * @param args Command line arguments (not used)
     */
    public static void main(String[] args){
        Locale.setDefault(Locale.ENGLISH);
        SwingUtilities.invokeLater(() -> {
            try {
                mainPage();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        });
    }

    /**
     * Creates and configures the main application window and initializes all UI panels.
     * Sets up navigation between different screens and manages user interactions.
     *
     * @throws IOException If image resources cannot be loaded
     */
    public static void mainPage() throws IOException {

        JFrame frame = new JFrame("Shapeville");

        frame.setSize(900, 640);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);


        Container contentPane = frame.getContentPane();
        contentPane.setLayout(null);


        MainMenuPanel panel1 = new MainMenuPanel("src/image/background/mainPanel.png");

        panel1.quitButton.addActionListener(e -> {
            System.exit(0);
        });



        SecondPanel panel2 = new SecondPanel("src/image/background/selectPanel.png");

        panel1.startButton.addActionListener(e -> {
            contentPane.remove(panel1);
            contentPane.add(panel2);
            contentPane.revalidate();
            contentPane.repaint();
        });


        QuestionPanel2 panel = new QuestionPanel2("src/image/background/finalPanel.png", 1);



        panel2.playButton.addActionListener(e -> {
            contentPane.remove(panel2);
            int currentCard = panel2.cardIndex + 1;

            try {
                switch (currentCard) {
                    case 1 :
                        SelectPanel selectPanel1 = new SelectPanel("src/image/background/selectPanel.png", "task1");
                        contentPane.add(selectPanel1);

                        selectPanel1.playButton.addActionListener(f -> {
                            contentPane.remove(selectPanel1);
                            try {
                                int nextCard = selectPanel1.cardIndex + 1;
                                showQuestionPanel1(contentPane, panel2, panel1, selectPanel1, nextCard);
                            } catch (IOException ex) {
                                ex.printStackTrace();
                            }
                            contentPane.revalidate();
                            contentPane.repaint();
                        });

                        selectPanel1.backButton.addActionListener(h -> {
                            contentPane.remove(selectPanel1);
                            contentPane.add(panel2);
                            contentPane.revalidate();
                            contentPane.repaint();
                        });
                        break;
                    case 2 :
                        //showQuestionPanel(contentPane, panel2, panel1, 2);
                        // angle
                        contentPane.add(panel);
                        Main.finishedQuizCount2++;
                        panel.endButton.addActionListener(h -> {
                            panel.submitButton.setEnabled(false);
                            panel.endQuiz();  // The methods you already have
                            contentPane.remove(panel);
                            contentPane.add(panel2);
                            contentPane.revalidate();
                            contentPane.repaint();
                        });

                        panel.homeButton.addActionListener(h -> {
                            contentPane.remove(panel);
                            contentPane.add(panel1);
                            contentPane.revalidate();
                            contentPane.repaint();
                        });

                        break;
                    case 3 :
                        SelectPanel selectPanel3 = new SelectPanel("src/image/background/selectPanel.png", "task3");
                        contentPane.add(selectPanel3);

                        selectPanel3.playButton.addActionListener(f -> {
                            contentPane.remove(selectPanel3);
                            try {
                                int nextCard = selectPanel3.cardIndex + 1 + 10;
                                showQuestionPanel4(contentPane, panel2, panel1, selectPanel3, nextCard);
                            } catch (IOException ex) {
                                ex.printStackTrace();
                            }
                            contentPane.revalidate();
                            contentPane.repaint();
                        });

                        selectPanel3.backButton.addActionListener(h -> {
                            contentPane.remove(selectPanel3);
                            contentPane.add(panel2);
                            contentPane.revalidate();
                            contentPane.repaint();
                        });

                        break;// yuan mianji zhouchang  3 minutes
                    case 4 :
                        showSelectPanel(contentPane, panel2, panel1, "task4");  // yuan mianji zhouchang  3 minutes
                        break;
                    case 5 :
                        showSelectPanel(contentPane, panel2, panel1, "bonus1"); //   3 minutes
                        break;
                    case 6 :
                        showSelectPanel(contentPane, panel2, panel1, "bonus2"); //   3 minutes
                        break;
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }

            contentPane.revalidate();
            contentPane.repaint();
        });



        panel2.backButton.addActionListener(e -> {
            contentPane.remove(panel2);
            contentPane.add(panel1);
            contentPane.revalidate();
            contentPane.repaint();
        });



        frame.add(panel1);
        frame.setVisible(true);
    }

    /**
     * Displays a question panel of type 1 with the specified question ID.
     * Uses caching to avoid recreating panels for questions that have already been shown.
     *
     * @param contentPane The container to which the question panel will be added
     * @param returnPanel The panel to return to when the question is completed
     * @param homePanel The main home panel
     * @param selectPanel The selection panel from which this question was launched
     * @param questionId The ID of the question to display
     * @throws IOException If image resources cannot be loaded
     */
    private static void showQuestionPanel1(Container contentPane, JPanel returnPanel, JPanel homePanel, SelectPanel selectPanel, int questionId) throws IOException {
        QuestionPanel1 questionPanel;


        if (questionPanelCache1.containsKey(questionId)) {
            questionPanel = questionPanelCache1.get(questionId);
        } else {

            questionPanel = new QuestionPanel1("src/image/background/finalPanel.png", questionId);
            questionPanelCache1.put(questionId, questionPanel);
            Main.finishedQuizCount1++;


            questionPanel.endButton.addActionListener(h -> {
                questionPanel.submitButton.setEnabled(false);
                questionPanel.endQuiz();  // The methods you already have
                contentPane.remove(questionPanel);
                contentPane.add(selectPanel);
                contentPane.revalidate();
                contentPane.repaint();
            });

            questionPanel.homeButton.addActionListener(h -> {
                contentPane.remove(questionPanel);
                contentPane.add(homePanel);
                contentPane.revalidate();
                contentPane.repaint();
            });
        }


        if (questionPanel.getParent() != contentPane) {
            contentPane.add(questionPanel);
        }

        contentPane.revalidate();
        contentPane.repaint();
    }

    /**
     * Displays a question panel of type 4 with the specified question ID.
     * Uses caching to avoid recreating panels for questions that have already been shown.
     *
     * @param contentPane The container to which the question panel will be added
     * @param returnPanel The panel to return to when the question is completed
     * @param homePanel The main home panel
     * @param selectPanel The selection panel from which this question was launched
     * @param questionId The ID of the question to display
     * @throws IOException If image resources cannot be loaded
     */
    private static void showQuestionPanel4(Container contentPane, JPanel returnPanel, JPanel homePanel, SelectPanel selectPanel, int questionId) throws IOException {
        QuestionPanel4 questionPanel;


        if (questionPanelCache4.containsKey(questionId)) {
            questionPanel = questionPanelCache4.get(questionId);
        } else {

            questionPanel = new QuestionPanel4("src/image/background/finalPanel.png", questionId);
            questionPanelCache4.put(questionId, questionPanel);
            Main.finishedQuizCount3++;


            questionPanel.endButton.addActionListener(h -> {
                questionPanel.submitButton.setEnabled(false);
                questionPanel.endQuiz();  // The methods you already have
                contentPane.remove(questionPanel);
                contentPane.add(selectPanel);
                contentPane.revalidate();
                contentPane.repaint();
            });

            questionPanel.homeButton.addActionListener(h -> {
                contentPane.remove(questionPanel);
                contentPane.add(homePanel);
                contentPane.revalidate();
                contentPane.repaint();
            });
        }


        if (questionPanel.getParent() != contentPane) {
            contentPane.add(questionPanel);
        }

        contentPane.revalidate();
        contentPane.repaint();
    }

    /**
     * Displays a selection panel for a specific task.
     * Sets up navigation buttons and their event handlers.
     *
     * @param contentPane The container to which the selection panel will be added
     * @param returnPanel The panel to return to when back button is clicked
     * @param homePanel The main home panel
     * @param taskName The name of the task to create selection panel for
     * @throws IOException If image resources cannot be loaded
     */
    private static void showSelectPanel(Container contentPane, JPanel returnPanel, JPanel homePanel, String taskName) throws IOException {
        SelectPanel selectPanel = new SelectPanel("src/image/background/selectPanel.png", taskName);
        contentPane.add(selectPanel);

        selectPanel.playButton.addActionListener(f -> {
            contentPane.remove(selectPanel);
            try {
                int nextCard = selectPanel.cardIndex + 1;
                switch (taskName) {
                    case "bonus1" :
                        showQuestionPanel3(contentPane, selectPanel, homePanel, nextCard, 5);
                        break;
                    case "bonus2" :
                        showQuestionPanel3(contentPane, selectPanel, homePanel, nextCard, 6);
                        break;
                    case "task3" :
                        showQuestionPanel3(contentPane, selectPanel, homePanel, nextCard,3);
                        break;
                    case "task4" :
                        showQuestionPanel3(contentPane, selectPanel, homePanel, nextCard,4);
                        break;
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            contentPane.revalidate();
            contentPane.repaint();
        });

        selectPanel.backButton.addActionListener(h -> {
            contentPane.remove(selectPanel);
            contentPane.add(returnPanel);
            contentPane.revalidate();
            contentPane.repaint();
        });
    }

    /**
     * Displays a question panel of type 3 with the specified question ID and mode.
     * Uses caching to avoid recreating panels for questions that have already been shown.
     *
     * @param contentPane The container to which the question panel will be added
     * @param returnPanel The panel to return to when the question is completed
     * @param homePanel The main home panel
     * @param questionId The ID of the question to display
     * @param mode The mode of the question panel (3-6, determining question type)
     * @throws IOException If image resources cannot be loaded
     */
    private static void showQuestionPanel3(Container contentPane, JPanel returnPanel, JPanel homePanel, int questionId, int mode) throws IOException {
        QuestionPanel3 questionPanel;


        if (questionPanelCache3.containsKey(mode * 10 + questionId)) {
            questionPanel = questionPanelCache3.get(mode * 10 + questionId);
        } else {

            questionPanel = new QuestionPanel3("src/image/background/finalPanel.png", questionId,mode);
            questionPanelCache3.put(mode * 10 + questionId, questionPanel);
            switch (mode) {
                case 4 :
                    Main.finishedQuizCount4++;
                    break;
                case 5 :
                    Main.finishedQuizCount5++;
                    break;
                case 6 :
                    Main.finishedQuizCount6++;
                    break;
            }

            questionPanel.endButton.addActionListener(h -> {
                questionPanel.submitButton.setEnabled(false);
                questionPanel.endQuiz();  // The methods you already have
                contentPane.remove(questionPanel);
                contentPane.add(returnPanel);
                contentPane.revalidate();
                contentPane.repaint();
            });

            questionPanel.homeButton.addActionListener(h -> {
                contentPane.remove(questionPanel);
                contentPane.add(homePanel);
                contentPane.revalidate();
                contentPane.repaint();
            });
        }


        if (questionPanel.getParent() != contentPane) {
            contentPane.add(questionPanel);
        }

        contentPane.revalidate();
        contentPane.repaint();
    }


}