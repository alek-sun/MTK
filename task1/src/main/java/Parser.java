import java.io.IOException;

public class Parser {
    Lexer lexer;
    Lexem curLexem;

    public Parser(Lexer lexer) throws LexerException, IOException {
        this.lexer = lexer;
        curLexem = lexer.getLexem();
    }

    public void calculateExpression() throws LexerException, IOException {
        while (curLexem.type != LexemType.EOF){
            System.out.println("Res = " + parseExpression());
        }
    }

    private int parseExpression() throws LexerException, IOException {
        int temp = parseTerm();
        while (curLexem.type == LexemType.PLUS
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

    private int parseTerm() throws LexerException, IOException {
        int temp = parseFactor();
        while (curLexem.type == LexemType.MUL || curLexem.type == LexemType.DIV){
            Lexem buf = curLexem;
            curLexem = lexer.getLexem();
            if (buf.type == LexemType.MUL){
                temp *= parseFactor();
            } else {
                temp /= parseFactor();
            }
        }
        return temp;
    }

    private int parseFactor() throws LexerException, IOException {
        int temp = parsePower();
        if (curLexem.type == LexemType.POW){
            curLexem = lexer.getLexem();
            return (int) Math.pow(temp, parseFactor());
        }
        return temp;
    }

    private int parseAtom() throws LexerException, IOException {
        if (curLexem.type == LexemType.NUMBER){
            int value = Integer.parseInt(curLexem.str);
            curLexem = lexer.getLexem();
            return value;
        } else if (curLexem.type == LexemType.OPEN_BRACKET){
            curLexem = lexer.getLexem();
            return parseExpression();
        }

        return 0; ////////////////
    }

    private int parsePower() throws LexerException, IOException {
        if (curLexem.type == LexemType.MINUS) {
            return parseAtom() * -1;
        } else {
            return parseAtom();
        }
    }
}
