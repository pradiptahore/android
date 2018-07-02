package com.example.spinner1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import static java.lang.System.exit;

public class MainComboBox extends AppCompatActivity implements OnChartValueSelectedListener {
    ArrayList<String> xvalues1 = new ArrayList<String>();
    ArrayList<BarEntry> yvalues1 = new ArrayList<BarEntry>();
    ArrayList<BarEntry> yvalues11 = new ArrayList<BarEntry>();

    ArrayList<String> xvalues2 = new ArrayList<String>();
    ArrayList<BarEntry> yvalues2 = new ArrayList<BarEntry>();

    ArrayList<String> xvalues3 = new ArrayList<String>();
    ArrayList<BarEntry> yvalues31 = new ArrayList<BarEntry>();
    ArrayList<BarEntry> yvalues32 = new ArrayList<BarEntry>();
    ArrayList<BarEntry> yvalues33 = new ArrayList<BarEntry>();

    ArrayList<String> xvalues4 = new ArrayList<String>();
    ArrayList<BarEntry> yvalues4 = new ArrayList<BarEntry>();

    ArrayList<String> xval = new ArrayList<String>();
    ArrayList<BarEntry> yval1 = new ArrayList<BarEntry>();
    ArrayList<BarEntry> yval2 = new ArrayList<BarEntry>();
    ArrayList<BarEntry> yval3 = new ArrayList<BarEntry>();
    ArrayList<BarEntry> yval4 = new ArrayList<BarEntry>();
    ArrayList<BarEntry> yval5 = new ArrayList<BarEntry>();
    ArrayList<BarEntry> yval6 = new ArrayList<BarEntry>();
    ArrayList<BarEntry> yval7 = new ArrayList<BarEntry>();

    BarChart barChart1, barChart2, barChart3, barChart4;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_combo_box);
        barChart1 = (BarChart) findViewById(R.id.barchart1);
        barChart2 = (BarChart) findViewById(R.id.barchart2);
        barChart3 = (BarChart) findViewById(R.id.barchart3);
        barChart4 = (BarChart) findViewById(R.id.barchart4);

        try {
            forFirstBarChart();
            forSecondBarChart();
            forThirdBarChart();
            forForthBarChart();
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }

        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        Spinner spinner1 = (Spinner) findViewById(R.id.spinner1);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this, R.array.Block_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(
                this, R.array.hi_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);



        spinner.setAdapter(adapter);
        spinner1.setAdapter(adapter1);
        spinner.setOnItemSelectedListener(new MyOnItemSelectedListener());

        Button myButton =(Button)findViewById(R.id.button1);
        myButton.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {

                //Intent intent = getIntent();
                //finish();
                //startActivity(intent);

                Spinner sp =	(Spinner)findViewById(R.id.spinner);
                String spinnerString = null;
                spinnerString = sp.getSelectedItem().toString();
                int nPos = sp.getSelectedItemPosition();


                Toast.makeText(getApplicationContext(), "Selected District=" + spinnerString,
                        Toast.LENGTH_LONG).show();
                /*Toast.makeText(getApplicationContext(), "getSelectedItemPosition=" + nPos,
                        Toast.LENGTH_LONG).show();*/


                Spinner sp1 =	(Spinner)findViewById(R.id.spinner1);
                String spinner1String = null;
                spinner1String = sp1.getSelectedItem().toString();
                int nPos1 = sp1.getSelectedItemPosition();


                //Toast.makeText(getApplicationContext(), "Selected Block=" + spinner1String,
                        //Toast.LENGTH_LONG).show();

                try {

                    forInnerBarChart1();

                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                }
                /*Toast.makeText(getApplicationContext(), "getSelectedItemPosition=" + nPos1,
                        Toast.LENGTH_LONG).show();*/
            }
        });

        Button myButton1 =(Button)findViewById(R.id.button);
        myButton1.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {

                Intent intent = getIntent();
                finish();
                startActivity(intent);
            }
        });
    }

    //private void refresh() {
     //   Intent intent = getIntent();
      //  finish();
        //startActivity(intent);
   //}


    @Override
    public void onValueSelected(Entry e, int dataSetIndex, Highlight h) {

        if (e != null)

            return;
        Log.i("VAL SELECTED", "Value: " + e.getVal() + ", xIndex: " + e.getXIndex()
                + ", DataSet index: " + dataSetIndex);

    }

    @Override
    public void onNothingSelected() {
        Log.i("PieChart", "nothing selected");
    }

    private String loadJSONFromAsset1() {
        String json = null;
        try {
            InputStream is = getAssets().open("BANKURA.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            Toast.makeText(getApplicationContext(), ex.getMessage(), Toast.LENGTH_LONG).show();
            return null;
        }
        return json;
    }
    private void forFirstBarChart() {
        barChart1.invalidate();
        barChart1.clear();
        xvalues1.clear();
        yvalues1.clear();
        yvalues11.clear();
        //barChart1.setUsePercentValues(true);
        try {
            JSONObject obj = new JSONObject(loadJSONFromAsset1());
            JSONArray literacyArray = obj.getJSONArray("bankura");
            for (int i = 0; i < literacyArray.length(); i++) {
                JSONObject literacyDetail = literacyArray.getJSONObject(i);
                yvalues1.add(new BarEntry(Float.parseFloat(literacyDetail.getString("phealth")), i));
                yvalues11.add(new BarEntry(Float.parseFloat(literacyDetail.getString("subcen")), i));
                xvalues1.add(literacyDetail.getString("place"));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
        BarDataSet data = new BarDataSet(yvalues1, "Primary Health Center");
        BarDataSet data1 = new BarDataSet(yvalues11, "Sub Center");
        ArrayList<IBarDataSet> dataSets = null;
        dataSets = new ArrayList<>();
        dataSets.add(data);
        dataSets.add(data1);
        BarData barData2 = new BarData(xvalues1, dataSets);
        //barData2.setValueFormatter(new PercentFormatter());
        barChart1.setData(barData2);
        barChart1.notifyDataSetChanged();
        barChart1.invalidate();
        data.setColors(ColorTemplate.COLORFUL_COLORS);
        //data1.setColors(ColorTemplate.COLORFUL_COLORS);
        barChart1.setDescription("PRIMARY HEALTH CENTER");
    }
    private String loadJSONFromAsset2() {
        String json = null;
        try {
            InputStream is = getAssets().open("BANKURA.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            Toast.makeText(getApplicationContext(), ex.getMessage(), Toast.LENGTH_LONG).show();
            return null;
        }
        return json;
    }
    private void forSecondBarChart() {
        try {
            JSONObject obj = new JSONObject(loadJSONFromAsset2());
            JSONArray literacyArray = obj.getJSONArray("bankura");
            for (int i = 0; i < literacyArray.length(); i++) {
                JSONObject literacyDetail = literacyArray.getJSONObject(i);
                yvalues2.add(new BarEntry(Float.parseFloat(literacyDetail.getString("health")), i));
                xvalues2.add(literacyDetail.getString("place"));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
        BarDataSet data = new BarDataSet(yvalues2, "Health");
        BarData barData2 = new BarData(xvalues2, data);
        barData2.setValueFormatter(new PercentFormatter());
        data.setBarSpacePercent(78f);
        barChart2.setData(barData2);
        barChart2.notifyDataSetChanged();
        barChart2.invalidate();
        data.setColors(ColorTemplate.COLORFUL_COLORS);
        barChart2.setDescription("HEALTH");
    }
    private String loadJSONFromAsset3() {
        String json = null;
        try {
            InputStream is = getAssets().open("BANKURA.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            Toast.makeText(getApplicationContext(), ex.getMessage(), Toast.LENGTH_LONG).show();
            return null;
        }
        return json;
    }
    private void forThirdBarChart() {
        try {
            JSONObject obj = new JSONObject(loadJSONFromAsset3());
            JSONArray literacyArray = obj.getJSONArray("bankura");
            for (int i = 0; i < literacyArray.length(); i++) {
                JSONObject literacyDetail = literacyArray.getJSONObject(i);
                yvalues31.add(new BarEntry(Float.parseFloat(literacyDetail.getString("primary")), i));
                yvalues32.add(new BarEntry(Float.parseFloat(literacyDetail.getString("secondary")), i));
                yvalues33.add(new BarEntry(Float.parseFloat(literacyDetail.getString("high")), i));
                xvalues3.add(literacyDetail.getString("place"));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
        BarDataSet data1 = new BarDataSet(yvalues31, "Primary");
        BarDataSet data2 = new BarDataSet(yvalues32, "Secondary");
        BarDataSet data3 = new BarDataSet(yvalues33, "High");
        ArrayList<IBarDataSet> dataSets = null;
        dataSets = new ArrayList<>();
        dataSets.add(data1);
        dataSets.add(data2);
        dataSets.add(data3);
        BarData barData2 = new BarData(xvalues3, dataSets);
        //barData2.setValueFormatter(new PercentFormatter());
        barChart3.setData(barData2);
        barChart3.notifyDataSetChanged();
        barChart3.invalidate();
        data1.setColors(ColorTemplate.COLORFUL_COLORS);
        //data2.setColors(ColorTemplate.COLORFUL_COLORS);
        data3.setColors(ColorTemplate.PASTEL_COLORS);
        barChart3.setDescription("LITERACY");
    }
    private String loadJSONFromAsset4() {
        String json = null;
        try {
            InputStream is = getAssets().open("BANKURA.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            Toast.makeText(getApplicationContext(), ex.getMessage(), Toast.LENGTH_LONG).show();
            return null;
        }
        return json;
    }
    private void forForthBarChart() {
        try {
            JSONObject obj = new JSONObject(loadJSONFromAsset4());
            JSONArray literacyArray = obj.getJSONArray("bankura");
            for (int i = 0; i < literacyArray.length(); i++) {
                JSONObject literacyDetail = literacyArray.getJSONObject(i);
                yvalues4.add(new BarEntry(Float.parseFloat(literacyDetail.getString("proad")), i));
                xvalues4.add(literacyDetail.getString("place"));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
        BarDataSet data = new BarDataSet(yvalues4, "Pucca Road");
        BarData barData2 = new BarData(xvalues4, data);
        barData2.setValueFormatter(new PercentFormatter());
        data.setBarSpacePercent(78f);
        barChart4.setData(barData2);
        barChart4.notifyDataSetChanged();
        barChart4.invalidate();
        data.setColors(ColorTemplate.COLORFUL_COLORS);
        barChart4.setDescription("PUCCA ROAD");
    }

    private String loadJSONFromAsset5() {
        String json = null;
        try {
            InputStream is = getAssets().open("BANKURA.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            Toast.makeText(getApplicationContext(), ex.getMessage(), Toast.LENGTH_LONG).show();
            return null;
        }
        return json;
    }
    private void forInnerBarChart1() {

        try {
            JSONObject obj = new JSONObject(loadJSONFromAsset5());
            Spinner sp1 =	(Spinner)findViewById(R.id.spinner1);
            JSONArray literacyArray = obj.getJSONArray(sp1.getSelectedItem().toString());
            for (int i = 0; i < literacyArray.length(); i++) {
                JSONObject literacyDetail = literacyArray.getJSONObject(i);
                yval1.add(new BarEntry(Float.parseFloat(literacyDetail.getString("phealth")), i));
                yval2.add(new BarEntry(Float.parseFloat(literacyDetail.getString("subcen")), i));
                yval3.add(new BarEntry(Float.parseFloat(literacyDetail.getString("health")), i));
                yval4.add(new BarEntry(Float.parseFloat(literacyDetail.getString("primary")), i));
                yval5.add(new BarEntry(Float.parseFloat(literacyDetail.getString("secondary")), i));
                yval6.add(new BarEntry(Float.parseFloat(literacyDetail.getString("high")), i));
                yval7.add(new BarEntry(Float.parseFloat(literacyDetail.getString("proad")), i));
                xval.add(literacyDetail.getString("place"));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
        BarDataSet data1 = new BarDataSet(yval1, "Primary Health");
        BarDataSet data2 = new BarDataSet(yval2, "Sub Center");
        BarDataSet data3 = new BarDataSet(yval3, "Health");
        BarDataSet data4 = new BarDataSet(yval4, "Primary Scl");
        BarDataSet data5 = new BarDataSet(yval5, "Secondary Scl");
        BarDataSet data6 = new BarDataSet(yval6, "High Scl");
        BarDataSet data7 = new BarDataSet(yval7, "Pucca Road");
        ArrayList<IBarDataSet> dataSets = null;
        dataSets = new ArrayList<>();
        dataSets.add(data1);
        dataSets.add(data2);
        data1.setColors(ColorTemplate.COLORFUL_COLORS);
        data2.setColors(ColorTemplate.PASTEL_COLORS);
        BarData barData2 = new BarData(xval, dataSets);
        //barData2.setValueFormatter(new PercentFormatter());
        barChart1.setData(barData2);
        barChart1.notifyDataSetChanged();
        barChart1.invalidate();
        barChart1.setDescription("PRIMARY HEALTH CENTERS");

        data3.setColors(ColorTemplate.COLORFUL_COLORS);
        BarData barData3 = new BarData(xval, data3);
        barData3.setValueFormatter(new PercentFormatter());
        data3.setBarSpacePercent(78f);
        barChart2.setData(barData3);
        barChart2.notifyDataSetChanged();
        barChart2.invalidate();
        barChart2.setDescription("HEALTH");

        ArrayList<IBarDataSet> dataSets1 = null;
        dataSets1 = new ArrayList<>();
        dataSets1.add(data4);
        dataSets1.add(data5);
        dataSets1.add(data6);
        data4.setColors(ColorTemplate.JOYFUL_COLORS);
        //data5.setColors(ColorTemplate.COLORFUL_COLORS);
        data6.setColors(ColorTemplate.PASTEL_COLORS);
        BarData barData4 = new BarData(xval, dataSets1);
        //barData4.setValueFormatter(new PercentFormatter());
        barChart3.setData(barData4);
        barChart3.notifyDataSetChanged();
        barChart3.invalidate();
        barChart3.setDescription("LITERACY");

        data7.setColors(ColorTemplate.COLORFUL_COLORS);
        BarData barData5 = new BarData(xval, data7);
        barData5.setValueFormatter(new PercentFormatter());
        data7.setBarSpacePercent(78f);
        barChart4.setData(barData5);
        barChart4.notifyDataSetChanged();
        barChart4.invalidate();
        barChart4.setDescription("PUCCA ROAD");
    }
}