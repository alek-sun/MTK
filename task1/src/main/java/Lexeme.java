import java.util.Objects;

public class Lexeme {
    String str;
    LexemeType type;

    public Lexeme(String str, LexemeType type) {
        this.str = str;
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Lexeme lexeme = (Lexeme) o;
        return Objects.equals(str, lexeme.str) &&
                type == lexeme.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(str, type);
    }
}
