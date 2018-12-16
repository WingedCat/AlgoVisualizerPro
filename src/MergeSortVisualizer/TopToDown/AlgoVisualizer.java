package MergeSortVisualizer.TopToDown;

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
    private void run(){

        // TODO: 编写自己的动画逻辑
        setData(-1,-1,-1);
        mergeSort(0,data.size()-1);
        setData(0,data.size()-1,data.size()-1);
    }

    private void setData(int l,int r,int mergeIndex){
        data.l = l;
        data.r = r;
        data.mergeIndex = mergeIndex;
        frame.render(data);
        AlgoVisHelper.pause(DELAY);
    }

    private void mergeSort(int l,int r){
        if(l>=r)
            return;
        setData(l,r,-1);
        int mid = (l+r)/2;
        mergeSort(l,mid);
        mergeSort(mid+1,r);
        merge(l,mid,r);
    }
    private void merge(int l,int mid,int r){
        int[] aux = Arrays.copyOfRange(data.numbers,l,r+1);
        int i=l,j=mid+1;
        for(int k=l;k<=r;k++){
            if(i>mid){
                data.numbers[k] = aux[j-l];
                j++;
            }else if(j>r){
                data.numbers[k] = aux[i-l];
                i++;
            }else if(aux[i-l]<aux[j-l]){
                data.numbers[k] = aux[i-l];
                i++;
            }else{
                data.numbers[k] = aux[j-l];
                j++;
            }
            setData(l,r,k);
        }
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
