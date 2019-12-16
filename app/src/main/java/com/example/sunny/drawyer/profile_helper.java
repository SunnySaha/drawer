package com.example.sunny.drawyer;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

public abstract class profile_helper extends ItemTouchHelper.SimpleCallback {

    int buttonwidth;
    RecyclerView recyclerView;
    List<MyButton> buttons;
    GestureDetector gestureDetector;
    int swipeposition = -1;
    float swipethresold = 0.5f;
    Map<Integer, List<MyButton>> buttonBuffer;
    Queue<Integer> removerqueue;

    GestureDetector.SimpleOnGestureListener gestureListener = new GestureDetector.SimpleOnGestureListener(){

        @Override
        public boolean onSingleTapUp(MotionEvent e) {
            for(MyButton button:buttons){

                if(button.onClick(e.getX(), e.getY())){
                    break;
                }
            }
            return true;
        }
    };

    private View.OnTouchListener onTouchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            if(swipeposition<0){
                return false;
            }
            Point point = new Point((int) motionEvent.getRawX(), (int)motionEvent.getRawY());
            RecyclerView.ViewHolder swipeviewHolder = recyclerView.findViewHolderForAdapterPosition(swipeposition);
            View swipeItem = swipeviewHolder.itemView;
            Rect rect = new Rect();
            swipeItem.getGlobalVisibleRect(rect);

            if(motionEvent.getAction()== MotionEvent.ACTION_DOWN || motionEvent.getAction() == MotionEvent.ACTION_UP
                    || motionEvent.getAction() == MotionEvent.ACTION_MOVE){

                if(rect.top< point.y && rect.bottom > point.y){
                    gestureDetector.onTouchEvent(motionEvent);
                }else{

                    removerqueue.add(swipeposition);
                    swipeposition = -1;
                    recoverswipeItem();
                }
            }
            return false;


        }
    };

    private synchronized void recoverswipeItem() {
        while(!removerqueue.isEmpty()){
            int pos = removerqueue.poll();
            if(pos>-1){
                recyclerView.getAdapter().notifyItemChanged(pos);
            }
        }
    }


    public profile_helper(Context context, RecyclerView recyclerView, int buttonwidth) {
        super(0, ItemTouchHelper.LEFT);
        this.recyclerView = recyclerView;
        this.buttons = new ArrayList<>();
        this.gestureDetector = new GestureDetector(context, gestureListener);
        this.recyclerView.setOnTouchListener(onTouchListener);
        this.buttonBuffer = new HashMap<>();
        this.buttonwidth = buttonwidth;

        removerqueue = new LinkedList<Integer>(){
            @Override
            public boolean add(Integer integer) {
                if(contains(integer)){
                    return false;
                }
                return super.add(integer);
            }
        };
        attachSwip();
    }

    private void attachSwip() {
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(this);
        itemTouchHelper.attachToRecyclerView(recyclerView);

    }

    public class MyButton {

        String text;
        int textsize, color, pos;
        int imageresId;
        RectF clickregion;
        MyButtonClickListener listener;
        Context context;
        Resources resources;

        public MyButton(Context context, String text, int textsize, int color, int imageresId,  MyButtonClickListener listener) {
            this.text = text;
            this.textsize = textsize;
            this.color = color;
            this.imageresId = imageresId;
            this.listener = listener;
            this.context = context;
            resources = context.getResources();
        }

        public boolean onClick(float x, float y){
            if(clickregion!=null && clickregion.contains(x, y)){
                listener.onClick(pos);
                return true;
            }
            return false;
        }
        public void onDraw(Canvas canvas, RectF rectF, int pos){

            Paint p = new Paint();
            p.setColor(color);
            canvas.drawRect(rectF, p);

            //text attributes

            p.setColor(Color.WHITE);
            p.setTextSize(textsize);

            Rect r = new Rect();
            float cHeight = rectF.height();
            float cWidth = rectF.width();
            p.setTextAlign(Paint.Align.LEFT);
            p.getTextBounds(text, 0, text.length(), r);
            float x =0, y=0;
            if(imageresId==0){
                //if thers is no icon in the button
                x = cWidth/2f - r.width()/2f - r.left;
                y = cHeight/2f + r.height()/2f - r.bottom;
                canvas.drawText(text, rectF.left+x, rectF.top+y, p);
            }else{
                Drawable d = ContextCompat.getDrawable(context, imageresId);
                Bitmap bitmap = DrawableToBitmap(d);
                canvas.drawBitmap(bitmap, (rectF.left+rectF.right)/2, (rectF.top+rectF.bottom)/2, p );
            }


            clickregion = rectF;
            this.pos = pos;


        }

        private Bitmap DrawableToBitmap(Drawable d) {

            if(d instanceof BitmapDrawable) {
                return ((BitmapDrawable) d).getBitmap();
            }
                Bitmap bitmap = Bitmap.createBitmap(d.getIntrinsicWidth(), d.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);

                Canvas canvas = new Canvas(bitmap);
                d.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
                d.draw(canvas);
                return bitmap;


        }
    }

    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
        return false;
    }

    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
        int pos = viewHolder.getAdapterPosition();
        if(swipeposition!=pos){
            removerqueue.add(swipeposition);
        }
        swipeposition = pos;
        if(buttonBuffer.containsKey(swipeposition)){
            buttons = buttonBuffer.get(swipeposition);
        }else{
            buttons.clear();
        }
        buttonBuffer.clear();
        swipethresold = 0.5f * buttons.size() * buttonwidth;
        recoverswipeItem();

    }

    public float getSwipethresold(RecyclerView.ViewHolder viewHolder) {
        return swipethresold;
    }

    @Override
    public float getSwipeEscapeVelocity(float defaultValue) {
        return 0.1f*defaultValue;
    }

    @Override
    public float getSwipeVelocityThreshold(float defaultValue) {
        return 5.0f*defaultValue;
    }

    @Override
    public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
        int pos = viewHolder.getAdapterPosition();
        float transitionX = dX;
        View itemView = viewHolder.itemView;

        if(pos<0){
            swipeposition = pos;
            return;
        }
        if(actionState == ItemTouchHelper.ACTION_STATE_SWIPE){
            if(dX<0){
                List<MyButton> buffer = new ArrayList<>();
                if(!buttonBuffer.containsKey(pos)){
                    intantiatemybutton(viewHolder, buffer);
                    buttonBuffer.put(pos, buffer);
                }else{
                    buffer = buttonBuffer.get(pos);

                }

                transitionX = dX* buffer.size()*buttonwidth/ itemView.getWidth();
                drawButton(c, itemView, buffer, pos, transitionX);
            }
        }
        super.onChildDraw(c, recyclerView, viewHolder, transitionX, dY, actionState, isCurrentlyActive);
    }

    private void drawButton(Canvas c, View itemView, List<MyButton> buffer, int pos, float transitionX) {

        float right = itemView.getRight();
        float dbuttonWidth = -1 * transitionX / buffer.size();
        for(MyButton button: buffer){

            float left = right-dbuttonWidth;
            button.onDraw(c, new RectF(left, itemView.getTop(), right, itemView.getBottom()), pos);
            right = left;
        }
    }

    public abstract void intantiatemybutton(RecyclerView.ViewHolder viewHolder, List<MyButton> buffer);


}
