package InsertSortVisualizer;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.MouseAdapter;

public class AlgoVisualizer {

    // TODO: 创建自己的数据
    private InsertSortData data;
    private final int DELAY = 40;//每次绘制暂停的时间
    private AlgoFrame frame;    // 视图

    public AlgoVisualizer(int sceneWidth, int sceneHeight,int size,int randomBound){

        // 初始化数据
        // TODO: 初始化数据
        data = new InsertSortData(size,randomBound);
        // 初始化视图
        EventQueue.invokeLater(() -> {
            frame = new AlgoFrame("插入排序演示", sceneWidth, sceneHeight);
            // TODO: 根据情况决定是否加入键盘鼠标事件监听器
//            frame.addKeyListener(new AlgoKeyListener());//插入排序不需要，注释掉
//            frame.addMouseListener(new AlgoMouseListener());
            new Thread(() -> {
                run();
            }).start();
        });
    }

    // 动画逻辑
    private void run(){

        // TODO: 编写自己的动画逻辑
        setData(-1,-1);
        //插入排序算法代码
        for( int i = 0 ; i < data.size() ; i ++ ){
            setData(i, i);
            for(int j = i ; j > 0 && data.get(j) < data.get(j-1) ; j --){
                data.swap(j,j-1);
                setData(i+1, j-1);
            }
        }
        setData(data.size(),-1);
    }

    private void setData(int sortedIndex,int currIndex){
        data.sortedIndex = sortedIndex;
        data.currIndex = currIndex;
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
        //设置数组数据为50，最大上限就是设置为窗口的高度
        AlgoVisualizer visualizer = new AlgoVisualizer(sceneWidth, sceneHeight,50,sceneHeight);
    }
}
