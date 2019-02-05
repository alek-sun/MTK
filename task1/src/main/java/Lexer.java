import java.io.IOException;
import java.io.Reader;

public class Lexer {
    Reader reader;
    int cur;

    public Lexer(Reader reader) throws IOException {
        this.reader = reader;
        cur = reader.read();
    }

    Lexeme getLexeme() throws LexerException, IOException {
        while (cur == ' '){
            cur = reader.read();
        }
        StringBuilder builder = new StringBuilder();
        while (Character.isDigit(cur)){
            builder.append((char)cur);
            cur = reader.read();
        }
        String lexemeStr = builder.toString();
        if (!lexemeStr.equals("")){
            return new Lexeme(lexemeStr, LexemeType.NUMBER);
        }
        switch (cur){
            case '-' : {
                cur = reader.read();
                return new Lexeme("-", LexemeType.MINUS);
            }
            case '+' : {
                cur = reader.read();
                return new Lexeme("+", LexemeType.PLUS);
            }
            case '*' : {
                cur = reader.read();
                return new Lexeme("*", LexemeType.MUL);
            }
            case '/' : {
                cur = reader.read();
                return new Lexeme("/", LexemeType.DIV);
            }
            case '^' : {
                cur = reader.read();
                return new Lexeme("^", LexemeType.POW);
            }
            case '(' : {
                cur = reader.read();
                return new Lexeme("(", LexemeType.OPEN_BRACKET);
            }
            case ')' : {
                cur = reader.read();
                return new Lexeme(")", LexemeType.CLOSE_BRACKET);
            }
            case -1 : {
                return new Lexeme("", LexemeType.EOF);
            }
            default:
                throw new LexerException("Unexpected char");
        }
    }
}
