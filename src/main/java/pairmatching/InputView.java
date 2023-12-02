package pairmatching;

import camp.nextstep.edu.missionutils.Console;

import static pairmatching.Message.INVALID_FORMAT;

public class InputView {
    private final static String DIVIDER = ", ";
    private final static Integer PROCESS_LENGTH = 3;
    public String getInput(){
        return Console.readLine();
    }

    public String getProcess() {
        String input = getInput();
        if(hasDivider(input)){
            return input;
        }
        throw new IllegalArgumentException(INVALID_FORMAT);
    }
    private boolean hasDivider(String input){
        if(input.split(DIVIDER).length == PROCESS_LENGTH){
            return true;
        }
        return false;
    }
}
