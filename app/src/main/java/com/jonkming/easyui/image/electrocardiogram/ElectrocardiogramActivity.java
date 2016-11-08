package com.jonkming.easyui.image.electrocardiogram;


import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.SurfaceView;

import com.jonkming.easyui.R;
import com.jonkming.easyui.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 心电图
* @Title: ElectrocardiogramActivity.java
* @Package com.jonkming.easyui.image.electrocardiogram
* @author HuangMingming
* @date 2016/11/8 20:26
* @version V1.0
*/
public class ElectrocardiogramActivity extends BaseActivity {
    private SurfaceView sfv;
    /**
     * 绘制的数据
     */
    List<Integer> x = new ArrayList<>();

    Paint mPaint;

    /**
     * X轴缩小的比例
     */
    public int rateX = 352;

    /**
     * Y轴缩小的比例
     */
    public int rateY = 0;

    /**
     * Y轴基线
     */
    public int baseLine = 0;

    private final int TEXT_SIZE = 40;

    /**
     * 为了节约绘画时间，每三个像素画一个数据
     */
    int divider = 10;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_electrocardiogram);
        intiViews();
    }
    AsyncTask<Integer,Integer,Integer> asyncTask;
    protected void intiViews() {
        sfv = (SurfaceView) findViewById(R.id.electrocardiogram_surfaceView);
        sfv.setZOrderOnTop(true);
        sfv.getHolder().setFormat(PixelFormat.TRANSLUCENT);
        // 初始化画笔
        mPaint = new Paint();
        mPaint.setColor(Color.WHITE);// 画笔为主色调
        mPaint.setStrokeWidth(5);// 设置画笔粗细
        baseLine = sfv.getHeight() / 2;
        asyncTask =  new AsyncTask<Integer,Integer,Integer>(){
            @Override
            protected Integer doInBackground(Integer... integers) {
                SystemClock.sleep(300);
                Random random = new Random();
                while(!this.isCancelled()){
                    x.add(random.nextInt(120)+1);
                    publishProgress(1);
                    SystemClock.sleep(200);

                }
                return 0;
            }

            @Override
            protected void onProgressUpdate(Integer... values) {
                super.onProgressUpdate(values);
                try {
                    //如果算出的来数据,大于宽度的话,那么则减去一个数值
                    if (x.size() > sfv.getWidth() / divider) {
                        x.remove(0);
                    }
                    if (rateY == 0) {
                        rateY = 2;
                        baseLine = sfv.getHeight() / 2;
                    }
                    //获取到画布
                    Canvas canvas = sfv.getHolder().lockCanvas(
                            new Rect(0, 0, sfv.getWidth(), sfv.getHeight()));// 关键:获取画布
                    canvas.drawColor(Color.BLACK);// 清除背景
                    mPaint.setStrokeWidth(5);// 设置画笔粗细
                    //开始的位置
                    int start = 0;
                    int py = baseLine;
                    if (x.size() > 0)
                        py += x.get(0) / rateY;
                    int y;
                    //绘制数据
                    for (int i = 0; i < x.size(); i++) {
                        y = x.get(i)  + baseLine;
                        canvas.drawLine(start + (i - 1) * divider - TEXT_SIZE, py, start + i * divider - TEXT_SIZE,
                                y, mPaint);
                        py = y;

                    }
                    mPaint.setStrokeWidth(0);// 设置画笔粗细

                    mPaint.setTextSize(20); //设置字体大小，int型，如12
                    canvas.drawText(""+x.get(x.size() - 1), start + (x.size() - 1 ) * divider - TEXT_SIZE , py + TEXT_SIZE, mPaint);
                    sfv.getHolder().unlockCanvasAndPost(canvas);// 解锁画布，提交画好的图像
                }catch (Exception e){

                }
            }
        }.execute();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        asyncTask.cancel(true);
        asyncTask = null;
        SystemClock.sleep(100);
    }
}
