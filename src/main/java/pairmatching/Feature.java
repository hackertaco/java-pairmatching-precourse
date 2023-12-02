package pairmatching;

import java.util.Arrays;

public enum Feature {
    First("1", "페어 매칭"),
    SECOND("2", "페어 조회"),
    THIRD("3", "페어 초기화"),
    END("Q", "종료");

    private final String division;
    private final String featureName;

    Feature(String division, String featureName){
        this.division = division;
        this.featureName = featureName;
    }


    @Override
    public String toString() {
        return division + ". " +featureName;
    }

    public static boolean validFeatureDivision(String inputDivision){
        return Arrays.stream(Feature.values()).filter(feature -> feature.division.equals(inputDivision)).count() > 0;
    }
}
