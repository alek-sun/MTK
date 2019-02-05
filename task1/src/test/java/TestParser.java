import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.io.IOException;
import java.io.StringReader;
import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class TestParser {

    @Parameterized.Parameter()
    public String input;

    @Parameterized.Parameter(1)
    public int result;

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        Object[][] data = new Object[][] {
                { "    130+100*3", 430 },
                { "2-       6+1" , -3 },
                { "20/4 + 5", 10 },
                { "(30+1000)", 1030},
                { "2^4 + 5", 21 },
                { "2^ (2+1)", 8 },
                { "2^3^2", 512 },
                { "(2^3)^2", 64 },
                { "(((2^3))^2)", 64 },
                { "6/2*3", 9 },
                { "6/(2*3)", 1 },
                { "24/(2*3+2)", 3 },
        };
        return Arrays.asList(data);
    }

    @Test
    public void test() {
        try {
            Parser parser = new Parser(new Lexer(new StringReader(input)));
            assertEquals(parser.calculateExpression(), result);
        } catch (LexerException | IOException e) {
            e.printStackTrace();
        }

    }
}