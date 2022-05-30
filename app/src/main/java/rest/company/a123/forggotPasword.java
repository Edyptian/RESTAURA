package rest.company.a123;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class forggotPasword extends AppCompatActivity {

    private EditText editTextTextEmailAddress3;
    private Button przypomnij;
    FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forggot_pasword);
        editTextTextEmailAddress3 = (EditText) findViewById(R.id.editTextTextEmailAddress3);
        przypomnij = (Button) findViewById(R.id.przypomnij);
        auth = FirebaseAuth.getInstance();

        przypomnij.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetPassword();
            }

            private void resetPassword() {
                String email = editTextTextEmailAddress3.getText().toString().trim();
                if (email.isEmpty()) {
                    editTextTextEmailAddress3.setError("wpisz Email");
                    editTextTextEmailAddress3.requestFocus();
                    return;
                }
                if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    editTextTextEmailAddress3.setError("wpisz poprawny email");
                    editTextTextEmailAddress3.requestFocus();
                    return;
                }
                auth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(forggotPasword.this, "udało się przypomniec haslo", Toast.LENGTH_LONG).show();

                        } else {
                            Toast.makeText(forggotPasword.this, "sprobuj ponownie", Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });
    }}