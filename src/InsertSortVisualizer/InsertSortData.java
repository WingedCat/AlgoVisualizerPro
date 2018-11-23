package InsertSortVisualizer;

public class InsertSortData {
    private int[] numbers;//data
    public int sortedIndex = -1;//已排序的边界
    public int currIndex = -1;//当前比较的边界

    /**
     * @param N {numbers数组的长度}
     * @param randomBound {生成的随机数的上限}
     */
    public InsertSortData(int N, int randomBound){
        numbers = new int[N];
        for(int i=0;i<N;i++)
            numbers[i] = (int) (Math.random()*randomBound)+1;//往numbers数组中添加随机数
    }

    public int size(){
        return numbers.length;
    }

    public int get(int index){
        if( index < 0 || index >= numbers.length)
            throw new IllegalArgumentException("Invalid index to access Sort Data.");
        return numbers[index];
    }

    public void swap(int i, int j) {
        if( i < 0 || i >= numbers.length || j < 0 || j >= numbers.length)
            throw new IllegalArgumentException("Invalid index to access Sort Data.");
        int t = numbers[i];
        numbers[i] = numbers[j];
        numbers[j] = t;
    }
}
