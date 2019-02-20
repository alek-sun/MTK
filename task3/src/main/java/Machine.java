import java.io.*;
import java.util.*;

public class Machine {
    Map<Integer, Map<Character, List<Integer>>> authomat;
    Stack<Configure> stack;
    BufferedReader autoInput;
    BufferedReader inputSequence;
    List<Integer> finalStates;
    Integer startState;

    public Machine(String authomatFileName, String sequenceFileName, Integer startState) throws FileNotFoundException {

        autoInput = new BufferedReader(new FileReader(new File(authomatFileName)));
        inputSequence = new BufferedReader(new FileReader(new File(sequenceFileName)));
        stack = new Stack<>();
        authomat = new HashMap<>();
        finalStates = new ArrayList<>();
        this.startState = startState;
    }

    boolean run() throws IOException {
        parseFinalStates();
        parseAuthomat();

        String machineLine = inputSequence.readLine();
        Configure curConfig;
        char c;
        int pos;
        Map<Character, List<Integer>> allTransitions;
        List<Integer> statesNumbers;

        stack.push(new Configure(0, startState));
        while (!stack.isEmpty()){
            curConfig = stack.pop();
            pos = curConfig.getPosition();
            if (finalStates.contains(curConfig.getCurState()) && pos == machineLine.length()){
                return true;
            }

            if (pos >= machineLine.length()) {
                continue;
            }
            c = machineLine.charAt(curConfig.getPosition());
            allTransitions = authomat.get(curConfig.getCurState());
            if (allTransitions == null){
                continue;
            }
            statesNumbers = allTransitions.get(c);
            if (statesNumbers == null){
                stack.push(new Configure(pos+1, curConfig.getCurState()));
                continue;
            }
            for (Integer state : statesNumbers
                 ) {
                stack.push(new Configure(pos+1, state));
            }
        }
        return false;
    }

    private void parseAuthomat() throws IOException {
        String cur;
        String curState = startState.toString();
        Map<Character, List<Integer>> allTransitions = new HashMap<>();

        while ((cur = autoInput.readLine()) != null){
            String[] maps = cur.split(":");
            String[] stateChar = maps[0].split(" ");

            if (!stateChar[0].equals(curState)){
                authomat.put(Integer.parseInt(curState), allTransitions);
                allTransitions = new HashMap<>();
            }

            ArrayList<Integer> statesNumbers = new ArrayList<>();
            if (maps.length > 1) {
                String[] states = maps[1].split(" ");
                for (String s : states
                ) {
                    if (s.isEmpty()) continue;
                    statesNumbers.add(Integer.parseInt(s));
                }
            }

            curState = stateChar[0];
            allTransitions.put(stateChar[1].charAt(0), statesNumbers);
        }
        authomat.put(Integer.parseInt(curState), allTransitions);
    }

    private void parseFinalStates() throws IOException {
        String finalStatesStr = autoInput.readLine();
        String[] statesArr = finalStatesStr.split(" ");
        for (String s : statesArr
             ) {
            finalStates.add(Integer.parseInt(s));
        }
    }
}
