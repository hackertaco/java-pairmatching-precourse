package pairmatching;

import camp.nextstep.edu.missionutils.Randoms;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static pairmatching.Message.CANNOT_MATCH;

public class MatchGenerator {
    private static final Map<MatchIndex, List<Match>> matchInformation = new HashMap<>();

    private MatchGenerator(Course course, Level level, List<String> backCrews, List<String> frontCrews){
        MatchIndex index = MatchIndex.getInstance(course, level);
        List<Match> matchList = new ArrayList<>();
        // Match를 생성한다.
        if(course.getName().equals("백엔드")){
            matchList.addAll(process(course, level, backCrews));
        }else{
            matchList.addAll(process(course, level, frontCrews));
        }
        matchInformation.put(index, matchList);
    }

    public static List<Match> createMatch(Course course, Level level, List<String> backCrews, List<String> frontCrews){
        if(isAlreadyExist(course, level)){
            return null;
        }
        MatchGenerator matchGenerator = new MatchGenerator(course, level, backCrews, frontCrews);
        return getMatch(course, level);
    }


    public static List<Match> viewMatch(Course course, Level level){
        MatchIndex index = MatchIndex.getInstance(course, level);
        if(isAlreadyExist(course, level)){
            return null;
        }
        return matchInformation.get(index);
    }
    public static void initializeAll(){
        matchInformation.clear();
    }
    public static void initializeAItem(Course course, Level level){
        MatchIndex index = MatchIndex.getInstance(course, level);
        matchInformation.remove(index);
    }
    private List<Match> createOneMatch(List<String> crews){
        List<Match> resultMatch = new ArrayList<>();
        List<String> shuffledCrew = Randoms.shuffle(crews); // 섞인 크루 이름 목록
        int pair = 2;
        for (int i = 0; i < shuffledCrew.size(); i += pair) {
            int end = Math.min(shuffledCrew.size(), i + pair);
            if (i %2 == 1 && i == shuffledCrew.size()-3) {
                end = shuffledCrew.size();
            }
            Match sliced = new Match(shuffledCrew.subList(i, end));
            resultMatch.add(sliced);
        }
        return resultMatch;
    }

    private boolean validate(Course course, Level level, List<Match> resultMatch){
        for(Map.Entry<MatchIndex, List<Match>> entry: matchInformation.entrySet()){
            MatchIndex index = entry.getKey();
            if(index.isSame(course, level)){
                return entry.getValue().stream().anyMatch(val ->
                        resultMatch.stream().anyMatch(re -> re.contains(val))
                );
            }
        }

        return false;
    }

    private List<Match> process(Course course, Level level, List<String> crews){
        for(int i = 0; i<3; i++){
            List<Match> matched = createOneMatch(crews);
            if(validate(course, level, matched)){
                continue;
            }
            return matched;
        }
        throw new IllegalArgumentException(CANNOT_MATCH);
    }
    private static List<Match> getMatch(Course course, Level level){
        MatchIndex index = MatchIndex.getInstance(course, level);

        return matchInformation.get(index);
    }
    private static boolean isAlreadyExist(Course course, Level level){
        // 이미 있는지 확인
        MatchIndex index = new MatchIndex(course, level);
        if(matchInformation.containsKey(index)){
            return true;
        }
        return false;
    }
}
