package com.example.abccal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import java.lang.Math;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button hitung_button = findViewById(R.id.button);
        hitung_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView result_txt = findViewById(R.id.abcresult);
                EditText a_txt = findViewById(R.id.abc_a);
                EditText b_txt = findViewById(R.id.abc_b);
                EditText c_txt = findViewById(R.id.abc_c);

                String as = a_txt.getText().toString();
                String bs = b_txt.getText().toString();
                String cs = c_txt.getText().toString();

                boolean note = false;
                boolean d_zero = false;
                if(as.equals("")) {
                    as = "0.0";
                    note = true;
                    d_zero = true;
                }
                if(bs.equals("")) {
                    bs = "0.0";
                    note = true;
                }
                if(cs.equals("")) {
                    cs = "0.0";
                    note = true;
                }

                double a_double = Double.parseDouble(as);
                double b_double = Double.parseDouble(bs);
                double c_double = Double.parseDouble(cs);

                String hasil;

                if (discriminant(a_double, b_double, c_double) < 0) {
                    hasil = new String(
                            "x₁ = " + String.valueOf(simplify(a_double, b_double, c_double)) + " + √(" + (discriminant(a_double, b_double, c_double)/(4*a_double*a_double)) + ")" + "\n" +
                                   "x₂ = " + String.valueOf(simplify(a_double, b_double, c_double)) + " - √(" + (discriminant(a_double, b_double, c_double)/(4*a_double*a_double)) + ")");
                } else {
                    hasil = new String(
                            "x₁ = " + String.valueOf(calABCP(a_double, b_double, c_double)) + "\n" +
                                   "x₂ = " + String.valueOf(calABCN(a_double, b_double, c_double)));
                }
                if (note) hasil = hasil + "\nnote: empty fields will be treated as 0";
                if (d_zero) hasil += "\nCannot Define a Number that Divided by Zero";
                result_txt.setText(hasil);
            }
        });
    }

    private double simplify(double a,
                            double b,
                            double c){
        if (b / (2 * a) == 0.0) return 0.0;
        return -(b / (2 * a));
    }

    private double calABCP(double a,
                          double b,
                          double c) {
        final double cal = (b + Math.sqrt(b * b - (4 * a * c))) / (2 * a);
        if (cal == 0.0) return 0.0;
        return -cal;
    }

    private double calABCN(double a,
                           double b,
                           double c) {
        final double cal = ((b - Math.sqrt(b * b - (4 * a * c))) / (2 * a));
        if (cal == 0.0) return 0.0;
        return -cal;
    }

    private double discriminant(double a,
                         double b,
                         double c) {
        return (b * b - (4 * a * c));
    }
}