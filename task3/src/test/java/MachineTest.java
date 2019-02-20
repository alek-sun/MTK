import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class MachineTest {
    @Test
    public void test1() {
        try {
            //второй символ с конца 'a'
            Machine machine = new Machine("a1.txt", "s1.txt", 0);
            assertTrue(machine.run());

            machine = new Machine("a1.txt", "s2.txt", 0);
            assertTrue(machine.run());

            machine = new Machine("a1.txt", "s3.txt", 0);
            assertFalse(machine.run());

            machine = new Machine("a1.txt", "s4.txt", 0);
            assertFalse(machine.run());

            machine = new Machine("a1.txt", "s5.txt", 0);
            assertTrue(machine.run());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test2() {
        try {
            //заканчивается на 'ba'
            Machine machine = new Machine("a2.txt", "s6.txt", 0);
            assertFalse(machine.run());

            machine = new Machine("a2.txt", "s1.txt", 0);
            assertFalse(machine.run());

            machine = new Machine("a2.txt", "s3.txt", 0);
            assertTrue(machine.run());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
