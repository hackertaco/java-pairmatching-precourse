package pairmatching;

import java.util.Objects;

public class MatchIndex {
    private static MatchIndex instance;
    private final Course course;
    private final Level level;

    public MatchIndex(Course course, Level level){
        this.course = course;
        this.level = level;
    }

    public static MatchIndex getInstance(Course course, Level level){
        if(instance == null){
            instance = new MatchIndex(course, level);
        }
        return instance;
    }

    public boolean isSame(Course course, Level level){
        if(course.equals(this.course.getName()) && level.equals(this.level)){
            return true;
        }
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MatchIndex that = (MatchIndex) o;
        return course == that.course && level == that.level;
    }

    @Override
    public int hashCode() {
        return Objects.hash(course, level);
    }
}
