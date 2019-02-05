import java.io.IOException;

public class Parser {
    Lexer lexer;
    Lexeme curLexeme;
    int openedBracket;
    int closedBracket;

    public Parser(Lexer lexer) throws LexerException, IOException {
        this.lexer = lexer;
        openedBracket = 0;
        closedBracket = 0;
        curLexeme = lexer.getLexeme();
    }

    public int calculateExpression() throws LexerException, IOException {
        int res = -1;
        while (curLexeme.type != LexemeType.EOF){
            res = parseExpression();
            if (openedBracket != closedBracket) {
                throw new LexerException("Bracket state error");
            }
            System.out.println("Res = " + res);
        }
        return res;
    }

    private int parseExpression() throws LexerException, IOException {
        int temp = parseTerm();
        while (curLexeme.type == LexemeType.PLUS
                || curLexeme.type == LexemeType.MINUS){
            int sgn;
            if (curLexeme.type == LexemeType.PLUS){
                sgn = 1;
            } else {
                sgn = -1;
            }
            curLexeme = lexer.getLexeme();
            temp += parseTerm() * sgn;
        }
        if (curLexeme.type == LexemeType.CLOSE_BRACKET){
            closedBracket++;
            curLexeme = lexer.getLexeme();
        }
        return temp;
    }

    private int parseTerm() throws LexerException, IOException {
        int temp = parseFactor();
        while (curLexeme.type == LexemeType.MUL || curLexeme.type == LexemeType.DIV){
            Lexeme buf = curLexeme;
            curLexeme = lexer.getLexeme();
            if (buf.type == LexemeType.MUL){
                temp *= parseFactor();
            } else {
                temp /= parseFactor();
            }
        }
        return temp;
    }

    private int parseFactor() throws LexerException, IOException {
        int temp = parsePower();
        if (curLexeme.type == LexemeType.POW){
            curLexeme = lexer.getLexeme();
            return (int) Math.pow(temp, parseFactor());
        }
        return temp;
    }

    private int parseAtom() throws LexerException, IOException {
        if (curLexeme.type == LexemeType.NUMBER){
            int value = Integer.parseInt(curLexeme.str);
            curLexeme = lexer.getLexeme();
            return value;
        } else if (curLexeme.type == LexemeType.OPEN_BRACKET){
            openedBracket++;
            curLexeme = lexer.getLexeme();
            return parseExpression();
        } else if (curLexeme.type == LexemeType.CLOSE_BRACKET){
            closedBracket++;
            curLexeme = lexer.getLexeme();
            return 0;
        } else {
            throw new LexerException("Parser : unexpected char");
        }
    }

    private int parsePower() throws LexerException, IOException {
        if (curLexeme.type == LexemeType.MINUS) {
            return parseAtom() * -1;
        } else {
            return parseAtom();
        }
    }
}
