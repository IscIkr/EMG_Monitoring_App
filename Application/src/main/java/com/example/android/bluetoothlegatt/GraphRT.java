package com.example.android.bluetoothlegatt;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.Viewport;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.Random;


public class GraphRT extends Fragment {
    private static final Random RANDOM = new Random();
    private LineGraphSeries<DataPoint> series;
    private int lastX = 0;
    private final Handler mHandler = new Handler();
    private Runnable mTimer1;
    private View mMyView;

    public GraphRT() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        if (mMyView == null){
            mMyView = inflater.inflate(R.layout.rt_graphview, container, false);

        } else {
            container.removeView(mMyView);
        }
        //set up of the DAC functions
        EditText dac_value = (EditText) mMyView.findViewById(R.id.editText);
        Button send_button = (Button) mMyView.findViewById(R.id.button);
        // we get graph view instance
        GraphView graph = (GraphView) mMyView.findViewById(R.id.graph);
        // data
        series = new LineGraphSeries<DataPoint>();
        graph.addSeries(series);
        // customize a little bit viewport
        Viewport viewport = graph.getViewport();
        viewport.setYAxisBoundsManual(true);
        viewport.setMinY(70);
        viewport.setMaxY(120);
        viewport.setScrollable(true);
        return mMyView;
    }


    @Override
    public void onResume() {
        super.onResume();
        // we're going to simulate real time with thread that append data to the graph
        /*
        new Thread(new Runnable() {

            @Override
            public void run() {
                // we add 100 new entries
                for (int i = 0; i < 100; i++) {
                    getActivity().runOnUiThread(new Runnable() {

                        @Override
                        public void run() {
                            addEntry();
                        }
                    });

                    // sleep to slow down the add of entries
                    try {
                        Thread.sleep(600);
                    } catch (InterruptedException e) {
                        // manage error ...
                    }
                }
            }
        }).start();*/
    }

    // add random data to graph
    protected void addEntry(double dataPoint) {
        // here, we choose to display max 10 points on the viewport and we scroll to end
        //RANDOM.nextDouble() * 10d
        series.appendData(new DataPoint(lastX++, dataPoint), true, 100);
    }
}
