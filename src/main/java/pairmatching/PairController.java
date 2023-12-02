package pairmatching;

import java.util.List;

import static pairmatching.Message.INVALID_FEATURE;

public class PairController {
    private final PairService pairService;
    private final OutputView outputView;

    private final InputView inputView;

    public PairController(PairService pairService, OutputView outputView, InputView inputView){
        this.pairService = pairService;
        this.outputView = outputView;
        this.inputView = inputView;
    }

    public void start(){
        getCrews();
        // 2. 기능 선택 메세지

        while(true){
            try{
                if(selectFeature().equals("Q")){
                    break;
                }

            }catch (IllegalArgumentException exception){
                outputView.printError(exception.getMessage());
            }
        }

    }

    private void getCrews(){
        List<String> backCrews = pairService.getCrews("src/main/resources/backend-crew.md");
        List<String> frontCrews = pairService.getCrews("src/main/resources/frontend-crew.md");
    }

    private String selectFeature(){
        outputView.showFeatures();
        String selectedFeature = inputView.getInput();
        boolean isValidDivision = Feature.validFeatureDivision(selectedFeature);
        if(!isValidDivision){
            throw new IllegalArgumentException(INVALID_FEATURE);
        }
        return selectedFeature;
    }
}
