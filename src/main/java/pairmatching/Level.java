package pairmatching;

import java.util.Arrays;
import java.util.List;

public enum Level {
    LEVEL1("레벨1", Arrays.asList("자동차경주", "로또", "숫자야구게임")),
    LEVEL2("레벨2", Arrays.asList("장바구니", "결제", "지하철노선도")),
    LEVEL3("레벨3", Arrays.asList()),
    LEVEL4("레벨4", Arrays.asList("성능개선", "배포")),
    LEVEL5("레벨5", Arrays.asList()),
    ;

    private final String levelName;
    private final List<String> missionName;

    Level(String levelName, List<String> missionName){
       this.levelName = levelName;
       this.missionName = missionName;
    }
    public String getLevelName(){
        return this.levelName;
    }
    public static Level findLevelByMissionName(String missionName){
        return Arrays.stream(Level.values()).filter(l->l.missionName.contains(missionName)).findAny().get();
    }

    public static boolean isLevelName(String levelName){
        return Arrays.stream(Level.values()).filter(level -> level.levelName.equals(levelName)).count() > 0;
    }

    public static boolean isMissionName(String mission){
        return Arrays.stream(Level.values()).filter(level -> level.missionName.contains(mission)).count() > 0;
    }

    @Override
    public String toString() {
        return "- " + levelName + ": " + String.join(" | ", missionName);
    }

}
