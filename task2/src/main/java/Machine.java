import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Map;

public class Machine {
    Integer curState;
    Integer readedCount;
    ArrayList<Integer> finalStates;
    Map<Integer, Map<Character, Integer>> rules;
    String input;


    Machine(Map<Integer, Map<Character, Integer>> rules, ArrayList<Integer> finalStates, String input){
        readedCount = 0;
        curState = 0;
        this.finalStates = finalStates;
        this.rules = rules;
        this.input = input;
    }

    boolean run() throws IOException {
        BufferedReader reader = new BufferedReader(new StringReader(input));
        Character s;
        int c;
        while ((c  = reader.read()) != -1){
            s = (char) c;
            curState = rules.get(curState).get(s);
        }
        if (finalStates.contains(curState)){
            return true;
        }
        return false;
    }
}
