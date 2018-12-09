package SelectSortVisualizer;

import InsertSortVisualizer.AlgoVisHelper;

import javax.swing.*;
import java.awt.*;

public class AlgoFrame extends JFrame{

    private int canvasWidth;
    private int canvasHeight;

    public AlgoFrame(String title, int canvasWidth, int canvasHeight){

        super(title);

        this.canvasWidth = canvasWidth;
        this.canvasHeight = canvasHeight;

        AlgoCanvas canvas = new AlgoCanvas();
        setContentPane(canvas);
        pack();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        setVisible(true);
    }

    public AlgoFrame(String title){

        this(title, 1024, 768);
    }

    public int getCanvasWidth(){return canvasWidth;}
    public int getCanvasHeight(){return canvasHeight;}

    // TODO: 设置自己的数据
    private SelectSortData data;
    public void render(SelectSortData data){
        this.data = data;
        repaint();
    }

    private class AlgoCanvas extends JPanel{

        public AlgoCanvas(){
            // 双缓存
            super(true);
        }

        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);

            Graphics2D g2d = (Graphics2D)g;

            // 抗锯齿
            RenderingHints hints = new RenderingHints(
                    RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);
            hints.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
            g2d.addRenderingHints(hints);

            // 具体绘制
            // TODO： 绘制自己的数据data
            int w = canvasWidth/data.size();//每个矩形的宽度
            for(int i=0;i<data.size();i++){
                if(i==data.minIndex)
                    SelectSortVisualizer.AlgoVisHelper.setColor(g2d, SelectSortVisualizer.AlgoVisHelper.DeepPurple);//将最小值绘制成紫色
                else{
                    if(i<=data.sortedIndex){
                        SelectSortVisualizer.AlgoVisHelper.setColor(g2d, SelectSortVisualizer.AlgoVisHelper.Red);//将已排序部分绘制成红色
                    }else
                        SelectSortVisualizer.AlgoVisHelper.setColor(g2d, SelectSortVisualizer.AlgoVisHelper.Grey);//将未排序部分绘制成灰色
                    if( i == data.currIndex )
                        SelectSortVisualizer.AlgoVisHelper.setColor(g2d, SelectSortVisualizer.AlgoVisHelper.LightBlue);//当前元素绘制成浅蓝色
                }
                AlgoVisHelper.fillRectangle(g2d, i * w, canvasHeight - data.get(i), w - 1, data.get(i));
            }
        }

        @Override
        public Dimension getPreferredSize(){
            return new Dimension(canvasWidth, canvasHeight);
        }
    }
}


