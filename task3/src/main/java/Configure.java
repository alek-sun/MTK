public class Configure {
    int position;
    Integer curState;

    public Configure(int position, Integer curState) {
        this.position = position;
        this.curState = curState;
    }

    public int getPosition() {
        return position;
    }

    public Integer getCurState() {
        return curState;
    }
}
