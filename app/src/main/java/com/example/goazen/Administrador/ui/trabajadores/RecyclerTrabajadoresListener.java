package com.example.goazen.Administrador.ui.trabajadores;

import android.content.Context;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerTrabajadoresListener implements RecyclerView.OnItemTouchListener  {

    private OnItemClickListener mListener;


    //Creacion interface para el click listener
    public interface OnItemClickListener{
        public void onItemClick (View view, int position);
        public void onLongItemClick(View view,int position);
    }
    GestureDetector mGestureDetector;

    //The listener with its options
    public RecyclerTrabajadoresListener(Context context, final RecyclerView recyclerView, OnItemClickListener listener){
        mListener = (OnItemClickListener) listener;
        mGestureDetector = new GestureDetector(context,new GestureDetector.SimpleOnGestureListener(){
            @Override
            public boolean onSingleTapUp(MotionEvent event){
                return true;
            }
            @Override
            public void onLongPress (MotionEvent event){
                View child = recyclerView.findChildViewUnder(event.getX(), event.getY());
                if (child != null && mListener != null) {
                    mListener.onLongItemClick(child, recyclerView.getChildAdapterPosition(child));
                }
            }
        });
        return;
    }
    @Override
    public boolean onInterceptTouchEvent(@NonNull RecyclerView view, @NonNull MotionEvent event) {
        View childView = view.findChildViewUnder(event.getX(), event.getY());
        if (childView != null && mListener != null && mGestureDetector.onTouchEvent(event)){
            mListener.onItemClick(childView, view.getChildAdapterPosition(childView));
            return true;
        }
        return false;
    }

    @Override
    public void onTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {

    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

    }
}

