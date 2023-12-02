package pairmatching;


public class OutputView {
    private final String decorator = "#############################################";
    private final String selectFeatureMessage = "기능을 선택하세요.";
    private final String ERROR_PREFIX = "[ERROR] ";

    public void showFeatures(){
        System.out.println(selectFeatureMessage);
        for(Feature feature: Feature.values()){
            System.out.println(feature.toString());;
        }
    }

    public void printError(String errorMessage){
        System.out.println(ERROR_PREFIX+errorMessage);
    }
}
