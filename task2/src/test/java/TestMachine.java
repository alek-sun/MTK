import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class TestMachine {

    @Test
    public void testLast3a() {

        try {
            Machine machine = new Machine("a1.txt", "s1.txt", 0);
            assertTrue(machine.run());

            machine = new Machine("a1.txt", "s2.txt", 0);
            assertFalse(machine.run());

            machine = new Machine("a1.txt", "s3.txt", 0);
            assertFalse(machine.run());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void testFirst3a() {
        try {
            Machine machine = new Machine("a2.txt", "s3.txt", 0);
            assertTrue(machine.run());

            machine = new Machine("a2.txt", "s2.txt", 0);
            assertFalse(machine.run());

            machine = new Machine("a2.txt", "s1.txt", 0);
            assertFalse(machine.run());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void test2a() {
        try {
            Machine machine = new Machine("a3.txt", "s4.txt", 0);
            assertTrue(machine.run());

            machine = new Machine("a3.txt", "s2.txt", 0);
            assertFalse(machine.run());

            machine = new Machine("a3.txt", "s1.txt", 0);
            assertFalse(machine.run());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
