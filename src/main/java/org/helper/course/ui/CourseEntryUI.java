package org.helper.course.ui;

import org.helper.course.CourseHelper;
import org.helper.course.model.Course;
import org.helper.course.model.Goal;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CourseEntryUI extends JFrame {

    private CourseHelper helper;

    private JLabel identifierLabel;
    private JTextField identifierText;

    private JLabel interestedLabel;
    private final ButtonGroup yesNoGroup = new ButtonGroup();
    private JRadioButton radioYes;
    private JRadioButton radioNo;

    private JCheckBox fallCheck;
    private JCheckBox springCheck;

    private JLabel goalLabel;
    private Choice goalChoice;

    private JLabel latestCourseLabel;
    private JLabel middleCourseLabel;
    private JLabel lastCourseLabel;

    private JButton submitButton;
    private JButton calculateButton;

    public CourseEntryUI(CourseHelper courseHelper) {

        helper = courseHelper;

        getContentPane().setLayout(null);
        setSize(526, 395);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Enter course details");

        identifierLabel = new JLabel("Course Identifier (ex. CS101)");
        identifierLabel.setBounds(10, 11, 282, 14);
        getContentPane().add(identifierLabel);

        identifierText = new JTextField();
        identifierText.setBounds(10, 36, 86, 20);
        getContentPane().add(identifierText);
        identifierText.setColumns(10);

        interestedLabel = new JLabel("Interested");
        interestedLabel.setBounds(10, 67, 117, 14);
        getContentPane().add(interestedLabel);

        radioYes = new JRadioButton("Yes");
        yesNoGroup.add(radioYes);
        radioYes.setBounds(10, 88, 48, 23);
        getContentPane().add(radioYes);

        radioNo = new JRadioButton("No");
        yesNoGroup.add(radioNo);
        radioNo.setSelected(true);
        radioNo.setBounds(60, 88, 48, 23);
        getContentPane().add(radioNo);

        fallCheck = new JCheckBox("Fall");
        fallCheck.setBounds(10, 114, 48, 23);
        getContentPane().add(fallCheck);

        springCheck = new JCheckBox("Spring");
        springCheck.setBounds(60, 114, 97, 23);
        springCheck.setSelected(true);
        getContentPane().add(springCheck);

        goalLabel = new JLabel("Goal");
        goalLabel.setBounds(10, 144, 46, 14);
        getContentPane().add(goalLabel);

        goalChoice = new Choice();
        goalChoice.setBounds(10, 164, 147, 20);
        goalChoice.add("Goal 3 with Lab");
        goalChoice.add("Goal 3 without Lab");
        goalChoice.add("Goal 5");
        goalChoice.add("Goal 6 Fine Arts");
        goalChoice.add("Goal 6 Humanities");
        goalChoice.add("Goal 9");
        goalChoice.add("Goal 10");
        goalChoice.select(0);
        getContentPane().add(goalChoice);

        latestCourseLabel = new JLabel("");
        latestCourseLabel.setBounds(10, 219, 500, 14);
        getContentPane().add(latestCourseLabel);

        middleCourseLabel = new JLabel("");
        middleCourseLabel.setBounds(10, 239, 500, 14);
        getContentPane().add(middleCourseLabel);

        lastCourseLabel = new JLabel("");
        lastCourseLabel.setBounds(10, 259, 500, 14);
        getContentPane().add(lastCourseLabel);

        submitButton = new JButton("Submit");
        submitButton.setBounds(312, 322, 89, 23);
        submitButton.addActionListener(new ActionListener() {
           public void actionPerformed(ActionEvent e) {
                if(!fallCheck.isSelected() && !springCheck.isSelected()) {
                    JOptionPane.showMessageDialog(null, "You must at least select fall or spring semester");
                } else {
                    boolean interested = radioYes.isSelected();
                    boolean fall = fallCheck.isSelected();
                    boolean spring = springCheck.isSelected();
                    Course c = new Course(identifierText.getText(), interested, fall, spring);

                    int option = goalChoice.getSelectedIndex();
                    Goal goal = null;

                    if(option == 0) {
                        goal = Goal.GOAL3_LAB;
                    } else if(option == 1) {
                        goal = Goal.GOAL3_NO_LAB;
                    } else if(option == 2) {
                        goal = Goal.GOAL5;
                    } else if(option == 3) {
                        goal = Goal.GOAL6_FINE_ARTS;
                    } else if(option == 4) {
                        goal = Goal.GOAL6_HUMANITIES;
                    } else if(option == 5) {
                        goal = Goal.GOAL9;
                    } else if(option == 6) {
                        goal = Goal.GOAL10;
                    } else {
                        JOptionPane.showMessageDialog(null, "You need to select a valid group");
                    }

                    if(goal != null) {
                        helper.addCourse(c, goal);
                        lastCourseLabel.setText(middleCourseLabel.getText());
                        middleCourseLabel.setText(latestCourseLabel.getText());
                        latestCourseLabel.setText((c.toString()));
                    } else {
                        JOptionPane.showMessageDialog(null, "You need to select a valid group");
                    }

                }
           }
        });
        getContentPane().add(submitButton);

        calculateButton = new JButton("Calculate");
        calculateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

            }
        });
        calculateButton.setBounds(411, 322, 89, 23);
        getContentPane().add(calculateButton);
    }
}
