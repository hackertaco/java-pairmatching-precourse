package pairmatching;

import java.util.List;

public class PairController {
    private final PairService pairService;

    public PairController(PairService pairService){
        this.pairService = pairService;
    }

    public void start(){
        getCrews();
    }

    private void getCrews(){
        List<String> backCrews = pairService.getCrews("src/main/resources/backend-crew.md");
        List<String> frontCrews = pairService.getCrews("src/main/resources/frontend-crew.md");
    }
}
