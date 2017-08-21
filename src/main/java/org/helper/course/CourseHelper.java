package org.helper.course;

import org.helper.course.io.CatalogParser;
import org.helper.course.model.Course;
import org.helper.course.model.Goal;

import java.util.ArrayList;

public class CourseHelper {

    private ArrayList<Course> courses;
    private CatalogParser catalog = new CatalogParser();

    public CourseHelper() {
        courses = new ArrayList<Course>();
    }

    public ArrayList<Course> getCourses() {
        return courses;
    }

    public ArrayList<Course> getCoursesByGoal(Goal goal) {
        ArrayList<Course> coursesForGoal = new ArrayList<Course>();
        for(Course c : courses) {
            if(c.getGoals().contains(goal))
                coursesForGoal.add(c);
        }

        return coursesForGoal;
    }

    public Course getCourseByIdentifier() {

        return null;
    }

    public String addCourse(Course course, Goal goal) {
        boolean added = false;
        for (Course c : courses) {
            if (c.getIdentifier().equalsIgnoreCase(course.getIdentifier())) {
                if (!c.getGoals().contains(goal)) {
                    c.addGoal(goal);
                    added = true;
                }
            }
        }

        if(!added) {
            course.addGoal(goal);
            String[] info = catalog.getExtraInfo(course.getIdentifier());
            course.setFullName(info[0]);
            course.setDescription(info[1]);
            courses.add(course);
        }

        return goal.toString();
    }
}
