import java.io.IOException;
import java.io.StringReader;

public class Main {
    public static void main(String[] args) {
        StringReader reader = new StringReader("100-5*30+10");
        try {
            Lexer lexer = new Lexer(reader);
            Parser parser = new Parser(lexer);
            parser.calculateExpression();
        } catch (LexerException | IOException e) {
            e.printStackTrace();
        }
    }
}
