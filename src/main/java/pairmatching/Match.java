package pairmatching;

import java.util.List;
import java.util.Objects;

public class Match {
    List<String> matchedNames;
    public Match(List<String> matchedNames){
        this.matchedNames = matchedNames;
    }

    public void validate(){

    }
    public List<String> getMatchedNames(){
        return matchedNames;
    }
    public boolean contains(Match match){
        if(this.matchedNames.contains(match.getMatchedNames())){
            return true;
        }
        return false;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Match match = (Match) o;
        return Objects.equals(matchedNames, match.matchedNames);
    }

    @Override
    public int hashCode() {
        return Objects.hash(matchedNames);
    }

    @Override
    public String toString() {
        return String.join(" : ", matchedNames);
    }
}
