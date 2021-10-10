import by.polchernikova.quizer.Quiz;

import java.awt.*;
import java.awt.event.*;
import java.util.Map;
import java.util.stream.Collectors;
import javax.swing.*;

public class QuizerGUIWelcomeScreen extends JFrame {
    public QuizerGUIWelcomeScreen(Map<String, Quiz> quizzes) {
        super("Quizer");
        this.quizzes = quizzes;
        this.setBounds(0, 0, 500, 500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        String[] list_of_tests = quizzes.keySet().toArray(String[]::new);

        start_test.addActionListener(new ButtonActionListener());

        Container container = this.getContentPane();
        container.setLayout(new GridLayout(4, 2, 30, 10));
        container.setBounds(100, 100, 300, 300);
        container.add(new JList<>(list_of_tests));
        container.add(label);
        container.add(test_name_field);
        container.add(start_test);

        getRootPane().setBorder(BorderFactory.createEmptyBorder(50,50,50,50));
    }

    class ButtonActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
         String test_name = QuizerGUIWelcomeScreen.this.test_name_field.getText();
         QuizerGUI test_window = new QuizerGUI(quizzes.get(test_name), test_name);
         test_window.setVisible(true);
         test_name_field.setText("");
        }
    }

    private JLabel test_list = new JLabel();
    private Map<String, Quiz> quizzes;
    private JButton start_test = new JButton("Start test");
    private JTextField test_name_field = new JTextField("", 5);
    private JLabel label = new JLabel("Enter test name (from the list above):");
}
