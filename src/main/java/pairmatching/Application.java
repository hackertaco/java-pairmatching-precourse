package pairmatching;

public class Application {
    public static void main(String[] args) {
        PairController pairController = new PairController(new PairService());
        pairController.start();
    }
}
