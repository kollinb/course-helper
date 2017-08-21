package org.helper.course;

import org.helper.course.ui.CourseEntryUI;

import javax.swing.*;
import java.awt.*;

public class Initialize {

    private static final boolean courseInputMode = true;
    private static CourseHelper helper = new CourseHelper();
    private static CourseCalculator calculator = new CourseCalculator();

    public static void main(String[] args) {

        if(courseInputMode) {
            EventQueue.invokeLater(new Runnable()
            {
                public void run()
                {
                    JFrame entryUI = new CourseEntryUI(helper);
                    entryUI.setVisible(true);
                }
            });
        } else {
            //populate overlap
        }
    }
}
