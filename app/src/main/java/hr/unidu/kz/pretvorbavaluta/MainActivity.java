package hr.unidu.kz.pretvorbavaluta;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class MainActivity extends AppCompatActivity {
    private EditText kn, eu, tecaj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        kn = (EditText)findViewById(R.id.iznosKn);
        eu = (EditText)findViewById(R.id.iznosEu);
        tecaj = (EditText)findViewById(R.id.tecaj);
    }

    public void pretvorbaKnEu(View view) {
        // mora se popuniti polje tečaj i kn
        String st = tecaj.getText().toString();
        String skn = kn.getText().toString();
        if (st == null || st.length()<=0){
            Toast.makeText(this,"Morate popuniti polje tečaja!",Toast.LENGTH_LONG).show();
            return;
        }
        if (skn == null || skn.length()<=0){
            Toast.makeText(this,"Morate popuniti polje iznosa u Kn!",Toast.LENGTH_LONG).show();
            return;
        }
        BigDecimal bdt = new BigDecimal(st);
        BigDecimal bdkn = new BigDecimal(skn);
        BigDecimal bdeu = bdkn.divide(bdt, 2, RoundingMode.HALF_UP);
        String seu = bdeu.setScale(2, RoundingMode.HALF_UP).toString();
        eu.setText(seu);
    }

    public void pretvorbaEuKn(View view) {
        // mora se popuniti polje tečaj i eu
        String st = tecaj.getText().toString();
        String seu = eu.getText().toString();
        if (st == null || st.length()<=0){
            Toast.makeText(this,"Morate popuniti polje tečaja!",Toast.LENGTH_LONG).show();
            return;
        }
        if (seu == null || seu.length()<=0){
            Toast.makeText(this,"Morate popuniti polje iznosa u Eurima!",Toast.LENGTH_LONG).show();
            return;
        }
        BigDecimal bdt = new BigDecimal(st);
        BigDecimal bdeu = new BigDecimal(seu);
        BigDecimal bdkn = bdeu.multiply(bdt);
        String skn = bdkn.setScale(2, RoundingMode.HALF_UP).toString();
         kn.setText(skn);
    }
}
