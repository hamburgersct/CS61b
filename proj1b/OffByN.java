public class OffByN implements CharacterComparator{

    private int offset;

    public OffByN(int N){
        this.offset = N;
    }

    @Override
    public boolean equalChars(char x, char y) {
        if (Character.isLetter(x) && Character.isLetter(y)){
            return Math.abs(x - y) == offset;
        }
        return false;
    }
}
