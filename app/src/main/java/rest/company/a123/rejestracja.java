package rest.company.a123;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class rejestracja extends AppCompatActivity implements View.OnClickListener {
    private FirebaseAuth mAuth;
    private TextView zarejestruj;
    private EditText editTextTextEmailAddress2,editTextTextPassword2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rejestracja);
        mAuth = FirebaseAuth.getInstance();
        zarejestruj = (Button) findViewById(R.id.zarejestruj);
        zarejestruj.setOnClickListener(this);
        editTextTextEmailAddress2 = (EditText) findViewById(R.id.editTextTextEmailAddress2);
        editTextTextPassword2 = (EditText) findViewById(R.id.editTextTextPassword2);
    }

    @Override
    public void onClick(View view) {
        registerUser();
    }

    private void registerUser() {
        String email = editTextTextEmailAddress2.getText().toString().trim();
        String password = editTextTextPassword2.getText().toString().trim();

        if (email.isEmpty()) {
            editTextTextEmailAddress2.setError("wpisz Email");
            editTextTextEmailAddress2.requestFocus();
            return;
        }
        if (password.isEmpty()) {
            editTextTextPassword2.setError("wpisz hasło");
            editTextTextPassword2.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            editTextTextEmailAddress2.setError("wpisz poprawny email");
            editTextTextEmailAddress2.requestFocus();
            return;
        }
        if (password.length() < 6) {
            editTextTextPassword2.setError("hasło musi zawierać 6 znaków");
            editTextTextPassword2.requestFocus();
        }
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            User user = new User(email);
                            FirebaseDatabase.getInstance().getReference("Users")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(rejestracja.this, "rejestracja się powiodła", Toast.LENGTH_LONG).show();

                                    } else {
                                        Toast.makeText(rejestracja.this, "rejestracja się nie powiodła", Toast.LENGTH_LONG).show();
                                    }
                                }
                            });

                        } else {
                            Toast.makeText(rejestracja.this, "rejestracja się nie powiodła", Toast.LENGTH_LONG).show();

                        }
                    }
                });
    }}