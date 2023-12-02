package pairmatching;


import java.util.List;

import static pairmatching.Message.PAIR_MATCHED;

public class OutputView {
    private final String decorator = "#############################################";
    private final String selectFeatureMessage = "기능을 선택하세요.";
    private final String ERROR_PREFIX = "[ERROR] ";

    public void showFeatures(){
        System.out.println(selectFeatureMessage);
        for(Feature feature: Feature.values()){
            System.out.println(feature.toString());
        }
    }

    public void printError(String errorMessage){
        System.out.println(ERROR_PREFIX+errorMessage);
    }

    public void showInfo(){
        System.out.println(decorator);
        System.out.println(showCourse());
        System.out.println("미션:");
        for(Level level: Level.values()){
            System.out.println(showLevelMission(level));
        }
        System.out.println(decorator);
    }

    public void showProcessMessage(){
        System.out.println("  과정, 레벨, 미션을 선택하세요.");
        System.out.println("  ex) 백엔드, 레벨1, 자동차경주");
    }

    public void showResult(List<Match> matchList){
        System.out.println(PAIR_MATCHED);
        System.out.println(matchList);
        for(Match match: matchList){
            System.out.println(match);
            System.out.println(match.toString());
        }

    }
    private String showCourse(){
        StringBuilder sb = new StringBuilder();
        sb.append("과정:");
        for(Course course : Course.values()){
            sb.append(" ");
            sb.append(course.getName());
            sb.append(" ");
            sb.append("|");
        }
        sb.deleteCharAt(sb.length()-1);
        return sb.toString();
    }

    private String showLevelMission(Level level){
        return level.toString();
    }
}
