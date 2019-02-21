import java.io.*;
import java.util.*;

public class Machine {
    Map<Integer, Map<Character, Integer>> rules;
    BufferedReader autoInput;
    BufferedReader inputSequence;
    List<Integer> finalStates;
    Integer startState;


    Machine(String rulesFileName, String sequenceFileName, Integer startState) throws FileNotFoundException {
        autoInput = new BufferedReader(new FileReader(new File(rulesFileName)));
        inputSequence = new BufferedReader(new FileReader(new File(sequenceFileName)));
        finalStates = new ArrayList<>();
        this.startState = startState;
        rules = new HashMap<>();
    }

    boolean run() throws IOException {
        parseFinalStates();
        parseRules();

        String machineLine = inputSequence.readLine();
        BufferedReader reader = new BufferedReader(new StringReader(machineLine));
        Character s;
        Integer curState = startState;
        int c;

        while ((c  = reader.read()) != -1){
            s = (char) c;
            curState = rules.get(curState).get(s);
        }
        return finalStates.contains(curState);
    }

    private void parseRules() throws IOException {
        String cur;
        String curState = startState.toString();
        Map<Character, Integer> transition = new HashMap<>();

        while ((cur = autoInput.readLine()) != null){
            String[] stateChar = cur.split(" ");

            if (!stateChar[0].equals(curState)){
                rules.put(Integer.parseInt(curState), transition);
                transition = new HashMap<>();
            }
            transition.put(stateChar[1].charAt(0), Integer.parseInt(stateChar[2]));
            curState = stateChar[0];
        }
        rules.put(Integer.parseInt(curState), transition);
    }

    private void parseFinalStates() throws IOException {
        String finalStatesStr = autoInput.readLine();
        String[] statesArr = finalStatesStr.split(" ");
        for (String s : statesArr
        ) {
            finalStates.add(Integer.parseInt(s));
        }
    }


   /* boolean run() throws IOException {
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
    }*/
}
