package com.mani.session2ass3;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity implements View.OnClickListener {
    EditText etcard, etrate, etpay;
    TextView etfbal, etmonth, etIn;
    Button btcomp, btclear;
    private static final String TAG="MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etcard = (EditText) findViewById(R.id.etCard);
        etrate = (EditText) findViewById(R.id.etRate);
        etpay = (EditText) findViewById(R.id.etPay);
        etfbal = (TextView) findViewById(R.id.etFbal);
        etmonth = (TextView) findViewById(R.id.etMonth);
        etIn = (TextView) findViewById(R.id.etIn);
        btcomp = (Button) findViewById(R.id.btcomp);
        btclear = (Button) findViewById(R.id.btclear);
        btcomp.setOnClickListener(this);
        btclear.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String principal = etcard.getText().toString();
        float princ = Float.parseFloat(principal);
        String rate = etrate.getText().toString();
        float rates = Float.parseFloat(rate);
        int count = 0;
        boolean setLast = false;

        String pay = etpay.getText().toString();
        int pays = Integer.parseInt(pay);
        switch (v.getId()) {
            case R.id.btcomp:
                int monthlyinterestpaid = Math.round((princ * (rates / (100 * 12))));

                etIn.setText("" + monthlyinterestpaid);
                float monthlyprincipal = pays - monthlyinterestpaid;
                float monthlyBalance = princ - monthlyprincipal;
                etfbal.setText("" + monthlyBalance);
                for (int i = 1; i < 1000; i++) {
                    count++;
                    if (setLast == true) {

                        monthlyinterestpaid = Math.round((princ * rates) / (100 * 12));
                        monthlyprincipal = monthlyBalance + monthlyinterestpaid;
                        monthlyBalance = 0;
                        break;


                    }
                    if (!(princ <= 0) && setLast == false) {
                        monthlyinterestpaid = Math.round((princ * rates) / (100 * 12));
                        monthlyprincipal = pays - monthlyinterestpaid;

                        monthlyBalance = princ - monthlyprincipal;
                        if (monthlyBalance < pays) {
                            setLast = true;
                        }
                        princ = (int) monthlyBalance;


                    }
                    else{
                        break;
                    }


                }
                etmonth.setText(""+count);


        }

    }
}