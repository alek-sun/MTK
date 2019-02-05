import java.util.Objects;

public class Lexem {
    String str;
    LexemType type;

    public Lexem(String str, LexemType type) {
        this.str = str;
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Lexem lexem = (Lexem) o;
        return Objects.equals(str, lexem.str) &&
                type == lexem.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(str, type);
    }
}
