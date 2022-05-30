package rest.company.a123;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView register,forgotPassword;
    private EditText editTextTextEmailAddress,editTextTextPassword;
    private Button button3;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        register = (TextView) findViewById(R.id.register);
        register.setOnClickListener(this);
        button3 = (Button) findViewById(R.id.button3);
        button3.setOnClickListener(this);
        editTextTextEmailAddress = (EditText) findViewById(R.id.editTextTextEmailAddress);
        editTextTextPassword = (EditText) findViewById(R.id.editTextTextPassword);
        mAuth = FirebaseAuth.getInstance();
        forgotPassword = (TextView) findViewById(R.id.forgotPassword);
        forgotPassword.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.register:
                startActivity(new Intent(this,rejestracja.class ));
                break;

            case R.id.button3:
                userLogin();
                break;

            case R.id.forgotPassword:
                startActivity(new Intent(this,forggotPasword.class));


        }
    }

    private void userLogin() {
        String email= editTextTextEmailAddress.getText().toString().trim();
        String password= editTextTextPassword.getText().toString().trim();

        if(email.isEmpty()){
            editTextTextEmailAddress.setError("wpisz Email");
            editTextTextEmailAddress.requestFocus();
            return;
        }
        if (password.isEmpty())
        {
            editTextTextPassword.setError("wpisz hasło");
            editTextTextPassword.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            editTextTextEmailAddress.setError("wpisz poprawny email");
            editTextTextEmailAddress.requestFocus();
            return;
        }
        if(password.length()<6){
            editTextTextPassword.setError("hasło musi zawierać 6 znaków");
            editTextTextPassword.requestFocus();
        }

        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                    if(user.isEmailVerified()){
                        startActivity(new Intent(MainActivity.this,PanelGlowny.class));
                    }else{

                        user.sendEmailVerification();
                        Toast.makeText(MainActivity.this,"zwerifikuj email",Toast.LENGTH_LONG).show();
                    }
                }else{
                    Toast.makeText(MainActivity.this,"logowanie się nie powiodło",Toast.LENGTH_LONG).show();
                }}
        });
    }
}