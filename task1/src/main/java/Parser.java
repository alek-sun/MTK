import java.io.IOException;

public class Parser {
    Lexer lexer;
    Lexem curLexem;

    public Parser(Lexer lexer) {
        this.lexer = lexer;
    }

    public void calculateExpression(){

    }

    private int parseExpression() throws LexerException, IOException {
        int temp = parseTerm();
        while (curLexem/* = lexer.getLexem())*/.type == LexemType.PLUS
                                || curLexem.type == LexemType.MINUS){
            int sgn;
            if (curLexem.type == LexemType.PLUS){
                sgn = 1;
            } else {
                sgn = -1;
            }
            curLexem = lexer.getLexem();
            temp += parseTerm() * sgn;
        }
        return temp;
    }

    private int parseTerm() {
        return 0;
    }

    private int parseFactor() throws LexerException, IOException {
        int temp = parsePower();
        if (curLexem.type == LexemType.POW){
            curLexem = lexer.getLexem();
            return parseAtom();
        }
        return temp;
    }

    private int parseAtom() throws LexerException, IOException {
        if (curLexem.type == LexemType.NUMBER){
            return Integer.parseInt(curLexem.str);
        } else if (curLexem.type == LexemType.OPEN_BRACKET){
            parseExpression();
        }
        return 0;
    }

    private int parsePower() throws LexerException, IOException {
        if (curLexem.type == LexemType.MINUS) {
            return parseAtom() * -1;
        } else {
            return parseAtom();
        }
    }
}
