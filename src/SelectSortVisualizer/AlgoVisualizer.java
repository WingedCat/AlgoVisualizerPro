package SelectSortVisualizer;

import InsertSortVisualizer.AlgoVisHelper;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.MouseAdapter;

public class AlgoVisualizer {

    // TODO: 创建自己的数据
    private SelectSortData data;        // 数据
    private AlgoFrame frame;    // 视图
    private final int DELAY = 40;

    public AlgoVisualizer(int sceneWidth, int sceneHeight,int size,int randomBound){

        // 初始化数据
        // TODO: 初始化数据
        data = new SelectSortData(size,randomBound);
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
        setData(-1,-1,0);
        //选择排序算法
        for(int i=0;i<data.size();i++){
            int minIndex = i;
            setData(i,-1,minIndex);
            for(int j=i+1;j<data.size();j++){
                setData(i,j,minIndex);
                if(data.get(j)<data.get(minIndex)) {
                    minIndex = j;
                    setData(i, j, minIndex);
                }
            }
            data.swap(i,minIndex);
            setData(i+1, -1, -1);
        }
        setData(data.size(),-1,-1);
    }

    private void setData(int sortedIndex,int currIndex,int minIndex){
        data.sortedIndex = sortedIndex;
        data.currIndex = currIndex;
        data.minIndex = minIndex;
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
