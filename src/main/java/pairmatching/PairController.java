package pairmatching;

import java.util.List;

import static pairmatching.Message.INITIALLIZE;
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
        List<String> backCrews = getCrews("src/main/resources/backend-crew.md");
        List<String> frontCrews = getCrews("src/main/resources/backend-crew.md");

        run(backCrews, frontCrews);
    }

    private List<String> getCrews(String path){
        return pairService.getCrews(path);
    }
    private void run(List<String> backCrews, List<String> frontCrews){
        while(true){
            try{
                String input = selectFeature();
                if(input.equals("Q")){
                    break;
                }
                if(input.equals("3")){
                    MatchGenerator.initializeAll();
                    System.out.println(INITIALLIZE);
                    continue;
                }
                outputView.showInfo();
                selectProcess(input, backCrews, frontCrews);
            }catch (IllegalArgumentException exception){
                outputView.printError(exception.getMessage());
            }
        }
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

    private void selectProcess(String input, List<String> backCrews, List<String> frontCrews){
        while (true){
            try{
                outputView.showProcessMessage();
                String[] selectedProcess = inputView.getProcess().split(", ");

                pairService.validateSelectedProcess(selectedProcess);
                List<Match> matches = pairService.startMatch(input, selectedProcess, backCrews, frontCrews);
                if(!matches.isEmpty()){
                    showResult(matches);
                    break;
                }

            }catch (IllegalArgumentException exception){
                outputView.printError(exception.getMessage());
            }
        }
    }

    private void showResult(List<Match> matches){
        outputView.showResult(matches);
    }


}
