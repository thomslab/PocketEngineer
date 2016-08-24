package com.thomslab.pocketengineer;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by mitohida on 8/21/2016.
 */

public class CurrentCalculation extends AppCompatActivity{

    //deklarasi variabel widget layout
    private Spinner sp_current_option,sp_unit_power_option;
    private RadioGroup rd_use;
    private TextView type_current_tv,use_tv,voltage_tv,voltage_unit_tv,power_tv,resistance_tv,
            ohm_unit_tv,power_factor_tv,current_result_tv;
    private EditText voltage_et,PR_et,power_factor_et;
    private Button button_calculate;

    //variabel integer untuk menyimpan posisi radio button dan spinner
    private int rd_checked, sp1_selected, sp2_selected;
    private float voltage,P_R,pf,sum_current;

    //oncreate activity method callback
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //set xml layout untuk activity
        setContentView(R.layout.currentcalculation);

        //referensi untuk masing masing variabel widget
        sp_current_option = (Spinner)findViewById(R.id.spinner_current_type_0);
        sp_unit_power_option = (Spinner)findViewById(R.id.spinner_power_unit_option_0);
        rd_use = (RadioGroup)findViewById(R.id.radio_grp_0);
        type_current_tv = (TextView)findViewById(R.id.tv_type_current);
        use_tv = (TextView)findViewById(R.id.tv_use);
        voltage_tv = (TextView)findViewById(R.id.tv_voltage);
        voltage_unit_tv =(TextView)findViewById(R.id.tv_voltage_unit);
        power_tv = (TextView)findViewById(R.id.textview_power_0);
        resistance_tv = (TextView)findViewById(R.id.textview_resistance_0);
        ohm_unit_tv = (TextView)findViewById(R.id.tv_ohm_unit);
        power_factor_tv = (TextView)findViewById(R.id.tv_power_factor);
        current_result_tv = (TextView)findViewById(R.id.textview_result_0);
        voltage_et = (EditText)findViewById(R.id.edittext_voltage_0);
        PR_et = (EditText)findViewById(R.id.edittext_PR_0);
        power_factor_et = (EditText)findViewById(R.id.edittext_powerfactor_0);
        button_calculate = (Button)findViewById(R.id.calculate_current_button);

        //set default value untuk widget spinner, edittext dan radio button
        sp_current_option.setSelection(1);
        rd_use.check(R.id.radiobutton_power_0);
        sp_unit_power_option.setSelection(0);
        power_factor_et.setText("0.9");

        //memunculkan power_factor_et saat sp_current_option posisi 1 atau 2 dan hilang saat 0
        sp_current_option.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 1){
                    power_factor_et.setEnabled(true);
                  // sp1_selected = 1;
                }else if (position == 2){
                    power_factor_et.setEnabled(true);
                    //sp1_selected = 2;
                }else {
                    power_factor_et.setEnabled(false);
                   // sp1_selected = 0;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        /* menghilangkan power_tv, power_et, dan sp_unit_power_option dan memunculkan resistance_tv,
        resistance_et dan ohm_unit_tv saat radio checked posisi 1 */
        rd_use.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.radiobutton_resistance_0){
                   // rd_checked = 0;
                    power_tv.setVisibility(View.INVISIBLE);
                    sp_unit_power_option.setVisibility(View.INVISIBLE);
                    resistance_tv.setVisibility(View.VISIBLE);
                    ohm_unit_tv.setVisibility(View.VISIBLE);
                }else {
                    //rd_checked = 1;
                    power_tv.setVisibility(View.VISIBLE);
                    sp_unit_power_option.setVisibility(View.VISIBLE);
                    resistance_tv.setVisibility(View.INVISIBLE);
                    ohm_unit_tv.setVisibility(View.INVISIBLE);

                }
            }
        });

        //membuat status integer untuk posisi sp_unit_power_option
       /*sp_unit_power_option.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0: sp2_selected = 0;
                        break;
                    case 1: sp2_selected = 1;
                        break;
                    case 2: sp2_selected = 2;
                        break;
                    case 3: sp2_selected = 3;
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });*/

        // kode yang di eksekusi saat button_calculate di klik
        button_calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (sp_current_option.getSelectedItemPosition()){
                    case 0: sp1_selected = 0; break;
                    case 1: sp1_selected = 1; break;
                    case 2: sp1_selected = 2; break;
                }
               // sp1_selected = sp_current_option.getSelectedItemPosition();
                //rd_checked = rd_use.getCheckedRadioButtonId();
                //sp2_selected = sp_unit_power_option.getSelectedItemPosition();
                Toast.makeText(CurrentCalculation.this,sp1_selected,Toast.LENGTH_LONG).show();
                if (voltage_et.getText().toString().isEmpty()||PR_et.getText().toString().isEmpty()||
                        power_factor_et.getText().toString().isEmpty()){
                    edittext_empty();
                }else {
                    DC_VPR();
                    /*if (sp1_selected == 0&&rd_checked==0&&sp2_selected==0){
                        DC_VPR();
                    }*/
                }


            }
        });


    }

    //method untuk dipanggil apabila edittext salah satu atau semua kosong
    public void edittext_empty(){
        AlertDialog.Builder builder = new AlertDialog.Builder(CurrentCalculation.this);
        builder.setTitle("ATTENTION !");
        builder.setMessage("Please enter all required field !");
        builder.setPositiveButton("Got it", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        AlertDialog alert = builder.create();
        alert.show();

    }
    public void DC_VPR(){
        voltage = Float.parseFloat(voltage_et.getText().toString());
        P_R = Float.parseFloat(PR_et.getText().toString());
        sum_current = P_R/voltage;
        current_result_tv.setText(String.format("%.2f", sum_current)+" A");

    }
    public void AC_VPRF (){
        voltage = Float.parseFloat(voltage_et.getText().toString());
        P_R = Float.parseFloat(PR_et.getText().toString());
        pf = Float.parseFloat(power_factor_et.getText().toString());

    }
    public void Current_Result(){
        current_result_tv.setText(String.format("%.2f", sum_current)+" A");

    }
}
