import java.io.IOException;
import java.io.Reader;

public class Lexer {
    Reader reader;
    int cur;

    public Lexer(Reader reader) throws IOException {
        this.reader = reader;
        cur = reader.read();
    }


    Lexem getLexem() throws LexerException, IOException {
        while (cur == ' '){
            cur = reader.read();
        }
        StringBuilder builder = new StringBuilder();
        while (Character.isDigit(cur)){
            builder.append(cur);
            cur = reader.read();
        }
        String lexemStr = builder.toString();
        if (!lexemStr.equals("")){
            return new Lexem(lexemStr, LexemType.NUMBER);
        }
        switch (cur){
            case '-' : {
                cur = reader.read();
                return new Lexem("-", LexemType.MINUS);
            }
            case '+' : {
                cur = reader.read();
                return new Lexem("+", LexemType.PLUS);
            }
            case '*' : {
                cur = reader.read();
                return new Lexem("*", LexemType.MUL);
            }
            case '/' : {
                cur = reader.read();
                return new Lexem("/", LexemType.DIV);
            }
            case '^' : {
                cur = reader.read();
                return new Lexem("^", LexemType.POW);
            }
            case '(' : {
                cur = reader.read();
                return new Lexem("(", LexemType.OPEN_BRACKET);
            }
            case ')' : {
                cur = reader.read();
                return new Lexem(")", LexemType.CLOSE_BRACKET);
            }
            case -1 : {
                return new Lexem("", LexemType.EOF);
            }
            default:
                throw new LexerException("Unexpected char");
        }
    }
}
