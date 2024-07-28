package com.example.carservice;


import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText etPrincipal, etDownPayment, etInterestRate, etLoanTerm;
    private TextView tvEMIResult;
    private Button btnCalculate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etPrincipal = findViewById(R.id.etPrincipal);
        etDownPayment = findViewById(R.id.etDownPayment);
        etInterestRate = findViewById(R.id.etInterestRate);
        etLoanTerm = findViewById(R.id.etLoanTerm);
        tvEMIResult = findViewById(R.id.tvEMIResult);
        btnCalculate = findViewById(R.id.btnCalculate);

        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateEMI();
            }
        });
    }

    private void calculateEMI() {
        double principal = Double.parseDouble(etPrincipal.getText().toString()) - Double.parseDouble(etDownPayment.getText().toString());
        double annualInterestRate = Double.parseDouble(etInterestRate.getText().toString());
        int loanTermMonths = Integer.parseInt(etLoanTerm.getText().toString());

        double monthlyInterestRate = annualInterestRate / (12 * 100);
        double onePlusRPowerN = Math.pow(1 + monthlyInterestRate, loanTermMonths);
        double emi = (principal * monthlyInterestRate * onePlusRPowerN) / (onePlusRPowerN - 1);

        tvEMIResult.setText(String.format("Monthly EMI in Rs is :%.2f", emi));
    }
}
