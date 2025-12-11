import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
class Student {
    protected String name;
    protected int rollNo;
    protected int[] marks = new int[5];
    protected int total;
    protected double average;
    protected char grade;

    public void calculateGrade() {
        total = 0;
        for (int mark : marks) total += mark;
        average = total / 5.0;

        if (average >= 90) grade = 'A';
        else if (average >= 75) grade = 'B';
        else if (average >= 60) grade = 'C';
        else if (average >= 45) grade = 'D';
        else grade = 'F';
    }
}

public class StudentGradingSystemGUI extends JFrame implements ActionListener {

    JTextField nameField, rollField, markFields[] = new JTextField[5];
    JButton calcButton, clearButton, exitButton;
    JTextArea resultArea;

    public StudentGradingSystemGUI() {
        setTitle("Student Grading System - OOTS Java Project");
        setSize(500, 600);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        JLabel heading = new JLabel("Student Grading System");
        heading.setFont(new Font("Arial", Font.BOLD, 20));
        heading.setBounds(130, 20, 300, 30);
        add(heading);

        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setBounds(50, 80, 100, 25);
        add(nameLabel);
        nameField = new JTextField();
        nameField.setBounds(150, 80, 200, 25);
        add(nameField);

        JLabel rollLabel = new JLabel("Roll No:");
        rollLabel.setBounds(50, 120, 100, 25);
        add(rollLabel);
        rollField = new JTextField();
        rollField.setBounds(150, 120, 200, 25);
        add(rollField);

        JLabel marksLabel = new JLabel("Enter Marks (5 subjects):");
        marksLabel.setBounds(50, 160, 200, 25);
        add(marksLabel);

        for (int i = 0; i < 5; i++) {
            markFields[i] = new JTextField();
            markFields[i].setBounds(150, 190 + (i * 35), 200, 25);
            add(markFields[i]);
            JLabel subjLabel = new JLabel("Subject " + (i + 1) + ":");
            subjLabel.setBounds(50, 190 + (i * 35), 100, 25);
            add(subjLabel);
        }

     
        calcButton = new JButton("Calculate");
        calcButton.setBounds(50, 380, 120, 30);
        add(calcButton);
        calcButton.addActionListener(this);

        clearButton = new JButton("Clear");
        clearButton.setBounds(190, 380, 100, 30);
        add(clearButton);
        clearButton.addActionListener(this);

        exitButton = new JButton("Exit");
        exitButton.setBounds(310, 380, 100, 30);
        add(exitButton);
        exitButton.addActionListener(this);
        resultArea = new JTextArea();
        resultArea.setEditable(false);
        resultArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        JScrollPane scrollPane = new JScrollPane(resultArea);
        scrollPane.setBounds(50, 430, 380, 100);
        add(scrollPane);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == calcButton) {
            try {
                Student s = new Student();
                s.name = nameField.getText();
                s.rollNo = Integer.parseInt(rollField.getText());

                for (int i = 0; i < 5; i++) {
                    s.marks[i] = Integer.parseInt(markFields[i].getText());
                }

                s.calculateGrade();

                String remark;
                switch (s.grade) {
                    case 'A': remark = "Excellent Performance!"; break;
                    case 'B': remark = "Very Good Performance!"; break;
                    case 'C': remark = "Good, Keep Improving."; break;
                    case 'D': remark = "Needs Improvement."; break;
                    default: remark = "Failed. Try Again!";
                }

                resultArea.setText(
                        "----- Student Report -----\n" +
                        "Name: " + s.name + "\n" +
                        "Roll No: " + s.rollNo + "\n" +
                        "Total Marks: " + s.total + "\n" +
                        "Average: " + String.format("%.2f", s.average) + "\n" +
                        "Grade: " + s.grade + "\n" +
                        "Remarks: " + remark + "\n" +
                        "---------------------------"
                );
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Please enter valid input for all fields!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

        if (e.getSource() == clearButton) {
            nameField.setText("");
            rollField.setText("");
            for (JTextField tf : markFields) tf.setText("");
            resultArea.setText("");
        }

        if (e.getSource() == exitButton) {
            int choice = JOptionPane.showConfirmDialog(this, "Are you sure you want to exit?", "Exit", JOptionPane.YES_NO_OPTION);
            if (choice == JOptionPane.YES_OPTION) {
                dispose();
            }
        }
    }
    public static void main(String[] args) {
        new StudentGradingSystemGUI();
    }
}