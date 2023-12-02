package pairmatching;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PairService {
    public List<String> getCrews(String path){
        File originCrew = new File(path);
        List<String> crews = new ArrayList<>();
        try (BufferedReader reader =
                     new BufferedReader(new FileReader(originCrew))) {
            reader.lines().forEach(crews::add);
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        return crews;
    }
}
