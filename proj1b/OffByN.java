public class OffByN implements CharacterComparator{

    int N;

    OffByN (int N){
        this.N = N;
    }

    @Override
    public boolean equalChars(char x, char y){
        if (Math.abs(x - y) == N){
            return true;
        }
        return false;
    }
}
