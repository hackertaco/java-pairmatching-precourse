package pairmatching;

import camp.nextstep.edu.missionutils.Console;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static pairmatching.Message.*;

public class PairService {
    public List<String> getCrews(String path){
        File originCrew = new File(path);
        List<String> crews = new ArrayList<>();
        try (BufferedReader reader =
                     new BufferedReader(new FileReader(originCrew))) {
            reader.lines().forEach(crews::add);
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        return crews;
    }

    public void validateSelectedProcess(String[] selectedProcess){
        validateSelectedCourse(selectedProcess[0]);
        validateSelectedLevel(selectedProcess[1]);
        validateSelectedMission(selectedProcess[2]);
    }
    public List<Match> startMatch(String input, String[] selectedProcess, List<String> backCrews, List<String> frontCrews){
        if(input.equals("1")){
            return createMatch(selectedProcess, backCrews, frontCrews);
        }
        if(input.equals("2")){
            return viewMatch(selectedProcess, backCrews, frontCrews);
        }
        return null;
    }
    private List<Match> viewMatch(String[] selectedProcess, List<String> backCrews, List<String> frontCrews){
        Course course = Arrays.stream(Course.values())
                .filter(c -> c.getName().equals(selectedProcess[0])).findAny().get();
        Level level = Level.findLevelByMissionName(selectedProcess[2]);

        List<Match> matchGenerator = MatchGenerator.viewMatch(course, level);
        if(matchGenerator == null){
            throw new IllegalArgumentException(NOT_EXIST);
        }
        return matchGenerator;
    }
    private List<Match> createMatch(String[] selectedProcess, List<String> backCrews, List<String> frontCrews){
        Course course = Arrays.stream(Course.values())
                .filter(c -> c.getName().equals(selectedProcess[0])).findAny().get();
        Level level = Level.findLevelByMissionName(selectedProcess[2]);

        List<Match> matchGenerator = MatchGenerator.createMatch(course, level, backCrews, frontCrews);
        if(matchGenerator == null){
            System.out.println(ALREADY_MATCHED);
            System.out.println("네 | 아니오");
            if(Console.readLine().equals("네")){
                MatchGenerator.initializeAItem(course, level);
                matchGenerator = MatchGenerator.createMatch(course, level, backCrews, frontCrews);
            }else{
                return null;
            }
        }
        System.out.println("matchGenerator");
        return matchGenerator;
    }
    private void validateSelectedCourse(String course){
        if(!Course.isCourse(course)){
            throw new IllegalArgumentException(INVALID_COURSE_NAME);
        }
    }
    private void validateSelectedLevel(String level){
        if(!Level.isLevelName(level)){
            throw new IllegalArgumentException(INVALID_LEVEL_NAME);
        }
    }
    private void validateSelectedMission(String mission){
        if(!Level.isMissionName(mission)){
            throw new IllegalArgumentException(INVALID_MISSION_NAME);
        }
    }

}
