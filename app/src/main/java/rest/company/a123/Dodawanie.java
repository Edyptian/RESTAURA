package rest.company.a123;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class Dodawanie extends AppCompatActivity {
    private EditText mNazwa,mAdres,mGodzinyO,mTelefon,mKuchnia,mGodzinyZ;
    private Button Zapisz;
    private FirebaseDatabase db = FirebaseDatabase.getInstance();
    private DatabaseReference root = db.getReference().child("Restauracje");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dodawanie);
        mNazwa = findViewById(R.id.editTextTextPersonName2);
        mAdres = findViewById(R.id.editTextTextPostalAddress);
        mGodzinyO = findViewById(R.id.editTextTime);
        mGodzinyZ = findViewById(R.id.editTextTime2);
        mTelefon = findViewById(R.id.editTextPhone);
        mKuchnia = findViewById(R.id.editTextTextPersonName);
        Zapisz = findViewById(R.id.button5);
        Zapisz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String Nazwa = mNazwa.getText().toString();
                String Adres = mAdres.getText().toString();
                String GodzinyO = mGodzinyO.getText().toString();
                String GodzinyZ = mGodzinyZ.getText().toString();
                String Telefon = mTelefon.getText().toString();
                String Kuchnia = mKuchnia.getText().toString();

                HashMap<String,String> userMap = new HashMap<>();

                userMap.put("Nazwa",Nazwa);
                userMap.put("Adres",Adres);
                userMap.put("GodzinaO",GodzinyO);
                userMap.put("GodzinaZ",GodzinyZ);
                userMap.put("Telefon",Telefon);
                userMap.put("Kuchnia",Kuchnia);

                root.push().setValue(userMap);
                Toast.makeText(Dodawanie.this,"rejestracja się powiodła", Toast.LENGTH_LONG).show();
            }
        });
    }
}