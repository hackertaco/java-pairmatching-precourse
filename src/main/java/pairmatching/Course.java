package pairmatching;

import java.util.Arrays;

public enum Course {
    BACKEND("백엔드"),
    FRONTEND("프론트엔드");

    private String name;

    Course(String name){
        this.name = name;
    }
    public String getName(){
        return this.name;
    }

    public static boolean isCourse(String courseName){
       return Arrays.stream(Course.values()).filter(course -> course.getName().equals(courseName)).count() > 0;
    }
}
