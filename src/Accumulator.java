public class Accumulator{
    private int sum;

    public Accumulator(int sum){
        this.sum = sum;
    }

    public int getSum(){
        return this.sum;
    }

    public void add(int num){
        this.sum += num;
    }
}