import java.io.IOException;

public class Parser {
    Lexer lexer;
    Lexem curLexem;

    public Parser(Lexer lexer) {
        this.lexer = lexer;
    }

    public void calculateExpression(){

    }

    private void parseExpression() throws LexerException, IOException {
        while ((curLexem = lexer.getLexem()).type == LexemType.PLUS
                                || curLexem.type == LexemType.MINUS){

        }
    }
}
