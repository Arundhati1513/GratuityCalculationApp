package edu.uncc.gratuitycalculationapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

// ** Aakshi Soni, Arundhati Mishra, Tanvee Raje **//


public class MainActivity extends AppCompatActivity {
    EditText Total_BillEditText;
    RadioGroup TipPercentRadioGroup;
    SeekBar tipSeekBar;
    TextView TipCustomValueTextView;
    TextView Tip_AmountTextView;
    TextView TotalAmounts_TextView;
    RadioGroup SplitBy_RadioGroup;
    TextView totalPerPersonTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("GRATUITY CALCULATION APP ");

        Total_BillEditText = findViewById(R.id.Total_BillEditText);
        TipPercentRadioGroup = findViewById(R.id.TipPercentRadioGroup);
        tipSeekBar =  findViewById(R.id.seekbar);
        TipCustomValueTextView = findViewById(R.id.TipCustomValueTextView);
        Tip_AmountTextView = findViewById(R.id.Tip_AmountTextView);
        TotalAmounts_TextView = findViewById(R.id.TotalAmounts_TextView);
        SplitBy_RadioGroup = findViewById(R.id.SplitBy_RadioGroup);
        totalPerPersonTextView = findViewById(R.id.totalPerPersonTextView);

        Total_BillEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int p, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int p, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                RecalculatingTip();
            }
        });

        TipPercentRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RecalculatingTip();
            }
        });

        tipSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {
                TipCustomValueTextView.setText(progress +"%");
                TipPercentRadioGroup.check(R.id.customPercentRadioButton);
                RecalculatingTip();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        SplitBy_RadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RecalculatingTip();
            }
        });


        findViewById(R.id.clearButton).setOnClickListener(view -> {
            clearForm();
        });
    }

    private void RecalculatingTip() {
        if(Total_BillEditText.getText().toString().equals("")){
            Toast.makeText(this, R.string.please_enter_bill_total, Toast.LENGTH_SHORT).show();
            Tip_AmountTextView.setText(R.string.zero_dollar);
            TotalAmounts_TextView.setText(R.string.zero_dollar);
            totalPerPersonTextView.setText(R.string.zero_dollar);
        }else{
            double billAmount= Double.parseDouble(Total_BillEditText.getText().toString());
            double tipAmount =0;
            switch(TipPercentRadioGroup.getCheckedRadioButtonId()){
                case R.id.EightPercentRadioButton:
                    tipAmount = billAmount * .08;
                    break;
                case R.id.TenPercentradioButton:
                    tipAmount = billAmount * .10;
                    break;
                case R.id.TwelvePercentRadioButton:
                    tipAmount = billAmount * .12;
                    break;
                case R.id.customPercentRadioButton:
                    tipAmount = billAmount * tipSeekBar.getProgress()/100;
                    break;
            }
            int splitBy = 1;
            switch(SplitBy_RadioGroup.getCheckedRadioButtonId()){
                case R.id.SpliyByTwoRadioButton:
                    splitBy = 2;
                    break;
                case R.id.SpliyByThreeRadioButton:
                    splitBy = 3;
                    break;
                case R.id.SpliyByFourRadioButton:
                    splitBy = 4;
                    break;
            }
            Tip_AmountTextView.setText(String.valueOf(tipAmount));
            TotalAmounts_TextView.setText(String.valueOf(tipAmount+billAmount));
            totalPerPersonTextView.setText(String.valueOf((tipAmount+billAmount)/splitBy));
        }
    }

    private void clearForm() {
        Total_BillEditText.setText("");
        TipPercentRadioGroup.check(R.id.EightPercentRadioButton);
        tipSeekBar.setProgress(50);
        Tip_AmountTextView.setText(R.string.zero_dollar);
        TotalAmounts_TextView.setText(R.string.zero_dollar);
        SplitBy_RadioGroup.check(R.id.SpliyByOneRadioButton);
        totalPerPersonTextView.setText(R.string.zero_dollar);
    }

}