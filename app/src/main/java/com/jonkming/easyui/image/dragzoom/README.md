#拖动缩放ImageView控件#
##目录结构##
│  ImageUi_DragZoomActivity.java 展示的Activity
│  README.md
│
└─ui
        BaseDragZoomImageView.java 自定义的ImageView

##详解##
因为要使用缩放和拖动,所以这里自定义了一个ImageView.具体实现则实现了View.OnTouchListener 接口。
```
public class BaseDragZoomImageView extends ImageView implements View.OnTouchListener
```

既然添加了接口,那么我们就需要实现它
在View.OnTouchListener 中触发,有这么几种情况
- MotionEvent.ACTION_DOWN 手指按下屏幕
- MotionEvent.ACTION_MOVE 手指在屏幕上移动
- MotionEvent.ACTION_UP 手指离开屏幕
- MotionEvent.ACTION_POINTER_UP 当触点离开屏幕，但是屏幕上还有触点(手指)
- MotionEvent.ACTION_POINTER_DOWN   当屏幕上已经有触点(手指)，再有一个触点压下屏幕

具体就这么多

因为要实现拖动和缩放,那么这些处理必须要实现:
```
    public boolean onTouch(View v, MotionEvent event) {
        /** 通过与运算保留最后八位 MotionEvent.ACTION_MASK = 255 */
        switch (event.getAction() & MotionEvent.ACTION_MASK) {
            // 手指压下屏幕
            case MotionEvent.ACTION_DOWN:
                mode = MODE_DRAG;
                // 记录ImageView当前的移动位置
                currentMatrix.set(getImageMatrix());
                startPoint.set(event.getX(), event.getY());
                break;
            // 手指在屏幕上移动，改事件会被不断触发
            case MotionEvent.ACTION_MOVE:
                // 拖拉图片
                if (mode == MODE_DRAG) {
                    float dx = event.getX() - startPoint.x; // 得到x轴的移动距离
                    float dy = event.getY() - startPoint.y; // 得到x轴的移动距离
                    // 在没有移动之前的位置上进行移动
                    matrix.set(currentMatrix);
                    matrix.postTranslate(dx, dy);
                }
                // 放大缩小图片
                else if (mode == MODE_ZOOM) {
                    float endDis = distance(event);// 结束距离
                    if (endDis > 10f) { // 两个手指并拢在一起的时候像素大于10
                        float scale = endDis / startDis;// 得到缩放倍数
                        matrix.set(currentMatrix);
                        matrix.postScale(scale, scale,midPoint.x,midPoint.y);
                    }
                }
                break;
            // 手指离开屏幕
            case MotionEvent.ACTION_UP:
                // 当触点离开屏幕，但是屏幕上还有触点(手指)
            case MotionEvent.ACTION_POINTER_UP:
                mode = 0;
                break;
            // 当屏幕上已经有触点(手指)，再有一个触点压下屏幕
            case MotionEvent.ACTION_POINTER_DOWN:
                mode = MODE_ZOOM;
                /** 计算两个手指间的距离 */
                startDis = distance(event);
                /** 计算两个手指间的中间点 */
                if (startDis > 10f) { // 两个手指并拢在一起的时候像素大于10
                    midPoint = mid(event);
                    //记录当前ImageView的缩放倍数
                    currentMatrix.set(getImageMatrix());
                }
                break;
        }
        setImageMatrix(matrix);
        return true;
    }
```
OK,完成收工。

这里不说太多,详细可以看<a href ="http://blog.csdn.net/u013151336/article/details/53086352">博客</a>
当然你也可以加入这个项目....