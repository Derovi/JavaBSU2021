import by.polchernikova.quizer.Quiz;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class QuizerGUI extends JFrame {
    public QuizerGUI(Quiz quiz, String test_name) {
        super(test_name);
        this.test = quiz;
        this.setBounds(0, 0, 500, 500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.container = this.getContentPane();
        container.setLayout(new GridLayout(3, 2, 20, 20));
        container.add(label);
        container.add(answer);
        submit.addActionListener(new SubmitButtonListener());
        container.add(submit);

        label.setText(test.nextTask().getText());
        getRootPane().setBorder(BorderFactory.createEmptyBorder(50,50,50,50));

    }

    class SubmitButtonListener implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            test.provideAnswer(answer.getText());
            answer.setText("");
            if(test.isFinished()) {
                try {
                    label.setText("Your mark is: " + Double.toString(test.getMark()));
                    submit.setText("Close");
                    submit.addActionListener(new CloseTestButtonListener());
                } catch (Exception ex) {
                    label.setText("Ooops, something went wrong");
                }
            } else {
                label.setText(test.nextTask().getText());
            }
        }
    }

    class CloseTestButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            setVisible(false);
            dispose();
        }
    }


    private JButton submit = new JButton("Submit");
    private JTextField answer = new JTextField("", 5);
    private JLabel label = new JLabel();
    private Container container;
    private Quiz test;
}
