import org.junit.Test;

import java.io.IOException;
import java.util.*;

import static org.junit.Assert.*;

public class TestMachine {

    @Test
    public void testLast3a() {
        HashMap<Integer, Map<Character, Integer>> rules = new HashMap<>();
        rules.put(0, Map.of(
                'a', 1,
                'b', 0
        ));
        rules.put(1, Map.of(
                'a', 2,
                'b', 0
        ));
        rules.put(2, Map.of(
                'a', 3,
                'b', 0
        ));
        rules.put(3, Map.of(
                'a', 3,
                'b', 0
        ));
        ArrayList<Integer> end = new ArrayList<>();
        end.add(3);
        
        try {
            Machine machine1 = new Machine(rules, end, "bbbbbaaa");
            assertTrue(machine1.run());
            Machine machine2 = new Machine(rules, end, "bbbbbaaaaa");
            assertTrue(machine2.run());
            Machine machine3 = new Machine(rules, end, "bbbbbaaabaa");
            assertFalse(machine3.run());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void testFirst3a() {
        HashMap<Integer, Map<Character, Integer>> rules = new HashMap<>();
        rules.put(0, Map.of(
                'a', 1,
                'b', 4
        ));
        rules.put(1, Map.of(
                'a', 2,
                'b', 4
        ));
        rules.put(2, Map.of(
                'a', 3,
                'b', 4
        ));
        rules.put(3, Map.of(
                'a', 3,
                'b', 3
        ));
        rules.put(4, Map.of(
                'a', 4,
                'b', 4
        ));
        ArrayList<Integer> end = new ArrayList<>();
        end.add(3);
        try {
            Machine machine1 = new Machine(rules, end, "aaabbbbb");
            assertTrue(machine1.run());
            Machine machine2 = new Machine(rules, end, "aaaaaabbbbb");
            assertTrue(machine2.run());
            Machine machine3 = new Machine(rules, end, "abbbbb");
            assertFalse(machine3.run());
            Machine machine4 = new Machine(rules, end, "bbbbaaabb");
            assertFalse(machine4.run());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void test2a() {

        // count(a) = 2
        HashMap<Integer, Map<Character, Integer>> rules = new HashMap<>();
        rules.put(0, Map.of(
                'a', 1,
                'b', 0
        ));
        rules.put(1, Map.of(
                'a', 2,
                'b', 1
        ));
        rules.put(2, Map.of(
                'a', 3,
                'b', 2
        ));
        rules.put(3, Map.of(
                'a', 3,
                'b', 3
        ));
        ArrayList<Integer> end = new ArrayList<>();
        end.add(2);
        try {
            Machine machine1 = new Machine(rules, end, "bbbbbbaa");
            assertTrue(machine1.run());
            Machine machine2 = new Machine(rules, end, "bbbaabbb");
            assertTrue(machine2.run());
            Machine machine4 = new Machine(rules, end, "bbbbbaabbaa");
            assertFalse(machine4.run());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
