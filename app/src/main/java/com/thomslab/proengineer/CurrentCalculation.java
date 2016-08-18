package com.thomslab.proengineer;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import com.thomslab.proengineer.R;

/**
 * Created by mitohida on 8/16/2016.
 */

public class CurrentCalculation extends AppCompatActivity{

    // variable untuk kalkulasi arus
    private EditText voltage,power,resistance,power_factor;
    private Button button_calculate;
    private TextView tv_power,tv_resistance,textview_result,tv_ohm_unit;
    private RadioGroup radio_use;
    private Spinner spinner_current_option, spinner_unit_option;

    private Double num_voltage,num_power,num_resistance,num_pf,sum_current = 5.0;

    //untuk id pada radio button dan spinner selected
    private int rd_selected;
    private int sp_selected;
    private int sp_unit_selected;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.currentcalculation);

        // inisialisasi objek
        resistance = (EditText) findViewById(R.id.edittext_resistance_0);
        voltage = (EditText) findViewById(R.id.edittext_voltage_0);
        power = (EditText) findViewById(R.id.edittext_power_0);
        power_factor = (EditText) findViewById(R.id.edittext_powerfactor_0);
        button_calculate = (Button) findViewById(R.id.calculate_current_button);
        textview_result = (TextView) findViewById(R.id.textview_result_0);
        tv_power = (TextView)findViewById(R.id.textview_power_0);
        tv_resistance = (TextView) findViewById(R.id.textview_resistance_0);
        tv_ohm_unit = (TextView) findViewById(R.id.tv_ohm_unit);
        radio_use = (RadioGroup) findViewById(R.id.radio_grp_0);
        spinner_current_option = (Spinner) findViewById(R.id.spinner_current_type_0);
        spinner_unit_option = (Spinner)findViewById(R.id.spinner_power_unit_option_0);

//saat load aplikasi secara default focus pada objek dibawah
       voltage.requestFocus();
        radio_use.check(R.id.radiobutton_power_0);
        power_factor.setText("0.9");

        //deteksi perubahan radio checked
       radio_use.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.radiobutton_power_0){
                    rd_selected = 0;
                    spinner_unit_option.setVisibility(View.VISIBLE);
                    power.setVisibility(View.VISIBLE);
                    tv_power.setVisibility(View.VISIBLE);
                    tv_resistance.setVisibility(View.GONE);
                    resistance.setVisibility(View.GONE);
                    tv_ohm_unit.setVisibility(View.GONE);


                }if  (checkedId == R.id.radiobutton_resistance_0){
                    rd_selected = 1;
                    spinner_unit_option.setVisibility(View.GONE);
                    power.setVisibility(View.GONE);
                    tv_power.setVisibility(View.GONE);
                    tv_resistance.setVisibility(View.VISIBLE);
                    resistance.setVisibility(View.VISIBLE);
                    tv_ohm_unit.setVisibility(View.VISIBLE);
                }
            }
        });
        // deteksi item selected pada spinner current type
        spinner_current_option.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0){
                    sp_selected = 0;
                    power_factor.setEnabled(false);

                }if (position == 1){
                    sp_selected = 1;
                    power_factor.setEnabled(true);
                }if (position == 2){
                    sp_selected = 2;
                    power_factor.setEnabled(true);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                power_factor.setEnabled(true);

            }
        });
        //deteksi item selected pada unit option spinner
        spinner_unit_option.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0){
                    sp_unit_selected = 0;
                }if (position == 1){
                    sp_unit_selected = 1;
                }if (position == 2){
                    sp_unit_selected = 2;
                }if (position == 3){
                    sp_unit_selected = 3;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        //button calculate start on event click
        button_calculate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (rd_selected == 0 && sp_selected == 0 && sp_unit_selected == 0){
                    num_voltage = Double.parseDouble(voltage.getText().toString());
                    num_power = Double.parseDouble(power.getText().toString());

                    sum_current = num_power / num_voltage;
                    textview_result.setText(String.format("%.2f", sum_current)+" A");


                }else if (rd_selected == 0 && sp_selected == 0 && sp_unit_selected == 1){
                    textview_result.setText("coba");
                }

                //num_resistance = Double.parseDouble(resistance.getText().toString());
               // num_pf = Double.parseDouble(power_factor.getText().toString());




            }
        });




    }

}
