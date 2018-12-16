package MergeSortVisualizer.BottomToUp;

import InsertSortVisualizer.AlgoVisHelper;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.MouseAdapter;
import java.util.Arrays;

public class AlgoVisualizer {

    // TODO: 创建自己的数据
    private MergeSortData data;        // 数据
    private AlgoFrame frame;    // 视图
    private final int DELAY = 40;

    public AlgoVisualizer(int sceneWidth, int sceneHeight,int size,int randomBound){

        // 初始化数据
        // TODO: 初始化数据
        data = new MergeSortData(size,randomBound);
        // 初始化视图
        EventQueue.invokeLater(() -> {
            frame = new AlgoFrame("Welcome", sceneWidth, sceneHeight);
            // TODO: 根据情况决定是否加入键盘鼠标事件监听器
//            frame.addKeyListener(new AlgoKeyListener());
//            frame.addMouseListener(new AlgoMouseListener());
            new Thread(() -> {
                run();
            }).start();
        });
    }

    // 动画逻辑
    public void run(){
        setData(-1, -1, -1);
        for (int sz = 1; sz < data.size(); sz *= 2)
            for (int i = 0; i < data.size() - sz; i += sz+sz)
                // 对 arr[i...i+sz-1] 和 arr[i+sz...i+2*sz-1] 进行归并
                merge(i, i+sz-1, Math.min(i+sz+sz-1,data.size()-1));
        this.setData(0, data.size()-1, data.size()-1);
    }

    private void merge(int l, int mid, int r){
        int[] aux = Arrays.copyOfRange(data.numbers, l, r+1);
        // 初始化，i指向左半部分的起始索引位置l；j指向右半部分起始索引位置mid+1
        int i = l, j = mid+1;
        for( int k = l ; k <= r; k ++ ){
            if( i > mid ){  // 如果左半部分元素已经全部处理完毕
                data.numbers[k] = aux[j-l];
                j ++;
            }else if( j > r ){   // 如果右半部分元素已经全部处理完毕
                data.numbers[k] = aux[i-l];

                i ++;
            }else if( aux[i-l] < aux[j-l] ){  // 左半部分所指元素 < 右半部分所指元素
                data.numbers[k] = aux[i-l];
                i ++;
            }else{  // 左半部分所指元素 >= 右半部分所指元素
                data.numbers[k] = aux[j-l];
                j ++;
            }
            setData(l, r, k);
        }
    }

    private void setData(int l, int r, int mergeIndex){
        data.l = l;
        data.r = r;
        data.mergeIndex = mergeIndex;

        frame.render(data);
        AlgoVisHelper.pause(DELAY);
    }

    // TODO: 根据情况决定是否实现键盘鼠标等交互事件监听器类
    private class AlgoKeyListener extends KeyAdapter{ }
    private class AlgoMouseListener extends MouseAdapter{ }

    public static void main(String[] args) {

        int sceneWidth = 800;
        int sceneHeight = 800;

        // TODO: 根据需要设置其他参数，初始化visualizer
        AlgoVisualizer visualizer = new AlgoVisualizer(sceneWidth, sceneHeight,50,sceneHeight);
    }
}
