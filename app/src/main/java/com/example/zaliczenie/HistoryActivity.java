package com.example.zaliczenie;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.BarGraphSeries;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HistoryActivity extends Fragment {
    @SuppressLint("StaticFieldLeak")
    List<Result> mResults = new ArrayList<>();
    RecyclerView recyclerView;
    Button refreshBtn, clearBtn;
    SimpleListAdapter adapter;

    HistoryActivity(){}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.hist_activity,container,false);

        recyclerView = view.findViewById(R.id.recyclerView);
        refreshBtn = view.findViewById(R.id.refreshBtn);
        clearBtn = view.findViewById(R.id.clearBtn);

        AppDatabase appDatabase = Room.databaseBuilder(view.getContext(),AppDatabase.class,"database").
                allowMainThreadQueries().build();

        ResultDao resultDao = appDatabase.resultDao();
        mResults = resultDao.getResults();
        Collections.reverse(mResults);


        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        adapter = new SimpleListAdapter(mResults);
        recyclerView.setAdapter(adapter);

        GraphView graph = view.findViewById(R.id.graph);
        BarGraphSeries<DataPoint> series = new BarGraphSeries<>(new DataPoint[] {
                new DataPoint(1, 0),
                new DataPoint(2, 0),
                new DataPoint(3, 0),
                new DataPoint(4, 0),
                new DataPoint(5, 0)
        });
        graph.removeAllSeries();
        graph.addSeries(series);

        refreshBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BarGraphSeries<DataPoint> seriesGraph;
                mResults = resultDao.getResults();
                Collections.reverse(mResults);
                adapter.updateHistory(mResults);

                if (mResults.size() >= 5) {
                    seriesGraph = new BarGraphSeries<>(new DataPoint[] {
                            new DataPoint(1, (int) Double.parseDouble(mResults.get(0).getResult())),
                            new DataPoint(2, (int) Double.parseDouble(mResults.get(1).getResult())),
                            new DataPoint(3, (int) Double.parseDouble(mResults.get(2).getResult())),
                            new DataPoint(4, (int) Double.parseDouble(mResults.get(3).getResult())),
                            new DataPoint(5, (int) Double.parseDouble(mResults.get(4).getResult()))
                    });
                } else {
                    DataPoint[] dataPoints = new DataPoint[mResults.size() + 1];
                    for (int i = 0; i < mResults.size(); i++) {
                        dataPoints[i] = new DataPoint(i+1,(int) Double.parseDouble(mResults.get(i).getResult()));
                    }
                    dataPoints[mResults.size()] = new DataPoint(mResults.size() + 1, 0);
                    seriesGraph = new BarGraphSeries<>(dataPoints);
                }


                graph.removeAllSeries();
                graph.addSeries(seriesGraph);
            }
        });

        clearBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resultDao.clearTable();
                graph.removeAllSeries();
                mResults = resultDao.getResults();
                Collections.reverse(mResults);
                adapter.updateHistory(mResults);
            }
        });


        return view;
    }
}
