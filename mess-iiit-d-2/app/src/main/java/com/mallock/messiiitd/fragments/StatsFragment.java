package com.mallock.messiiitd.fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.Chart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.mallock.messiiitd.R;

import java.util.ArrayList;

/**
 * Created by Mallock on 06-10-2016.
 */

public class StatsFragment extends Fragment {

    private static final String BEST_FOODS = "best items";
    private static final String WORST_FOODS = "worst items";
    private static final int CHART_TYPE_LINE = 0;
    private static final int CHART_TYPE_PIE = 1;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.from(getContext())
                .inflate(R.layout.stats_layout, container, false);
        //entries

        //labels
        ArrayList<String> labelsFood = new ArrayList<>();
        labelsFood.add("Brownies");
        labelsFood.add("Ice cream");
        labelsFood.add("Rajma");
        labelsFood.add("Chole");
        labelsFood.add("Raita");

        String[] mMonths = new String[]{
                "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Okt"
        };
        ArrayList<String> labelsMonths = new ArrayList<String>();
        for (String month : mMonths) {
            labelsMonths.add(month);
        }

        LineChart lineLikes = (LineChart) view.findViewById(R.id.line_chart_likes);
        setLineChart(lineLikes, getRandomData(10), "Line chart: likes", labelsMonths, CHART_TYPE_LINE);

        LineChart lineDislikes = (LineChart) view.findViewById(R.id.line_chart_dislikes);
        setLineChart(lineDislikes, getRandomData(10), "Line chart: dislikes", labelsMonths, CHART_TYPE_LINE);



        PieChart pieLikes = (PieChart) view.findViewById(R.id.pie_chart_likes);
        setLineChart(pieLikes, getRandomData(5), "Pie chart: likes", labelsFood, CHART_TYPE_PIE);


        PieChart pieDislikes = (PieChart) view.findViewById(R.id.pie_chart_dislikes);
        setLineChart(pieDislikes, getRandomData(5), "Pie chart: Dislikes", labelsFood, CHART_TYPE_PIE);


        return view;
    }

    private Chart setLineChart(Chart chart, ArrayList<Entry> data, String description, ArrayList<String> labels, int chartType) {
        String string;
        if (description.equals(BEST_FOODS)) {
            string = "# of likes";
        } else {
            string = "# of dislikes";
        }
        ArrayList<Integer> colors = new ArrayList<>();
        for (int c : ColorTemplate.PASTEL_COLORS) {
            colors.add(c);
        }
        for (int c : ColorTemplate.JOYFUL_COLORS) {
            colors.add(c);
        }
        switch (chartType) {
            case CHART_TYPE_PIE:
                PieChart pieChart = (PieChart) chart;
                PieDataSet dataSet = new PieDataSet(data, string);
                dataSet.setColors(colors);
                pieChart.setData(new PieData(labels, dataSet));
                pieChart.setHoleRadius(50);
                pieChart.setDescription(description);
                pieChart.setDescriptionTextSize(22.0f);
                pieChart.setDescriptionPosition(View.TEXT_ALIGNMENT_CENTER, View.TEXT_ALIGNMENT_VIEW_END);
                pieChart.setDrawSlicesUnderHole(false);
                pieChart.setCenterTextColor(R.color.red);
                pieChart.animateX(2000);
                pieChart.animateY(2000);
                return pieChart;
            case CHART_TYPE_LINE:
                LineChart lineChart = (LineChart) chart;
                LineDataSet dataSet2 = new LineDataSet(data, string);
                dataSet2.setColor(Color.BLUE);
//                dataSet2.setDrawCubic(true);
//                dataSet2.setHighLightColor(Color.BLUE);
//                dataSet2.setHighlightEnabled(false);
//                dataSet2.setCubicIntensity(0.1f);
                dataSet2.setDrawValues(false);
                dataSet2.setFillColor(Color.RED);
                dataSet2.setDrawFilled(true);
                lineChart.setData(new LineData(labels, dataSet2));
                lineChart.setDescription(description);
                lineChart.setDrawGridBackground(false);
                lineChart.setGridBackgroundColor(Color.WHITE);
                lineChart.setDescriptionTextSize(22.0f);
                lineChart.setDescriptionPosition(View.TEXT_ALIGNMENT_CENTER, View.TEXT_ALIGNMENT_VIEW_END);
                lineChart.animateY(2000);
                return lineChart;

            default:
                return null;
        }
    }

    private ArrayList<Entry> getRandomData(int size) {
        ArrayList<Entry> array = new ArrayList<>();
        int minimum = 1;
        int maximum = 15;
        for (int i = 0; i != size; i++) {
            array.add(new Entry(minimum + (int) (Math.random() * maximum), i));
        }
        return array;
    }

}
