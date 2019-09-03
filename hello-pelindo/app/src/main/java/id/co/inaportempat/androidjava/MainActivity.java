package id.co.inaportempat.androidjava;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // casting views
        final EditText editTextEmail = findViewById(R.id.editTextEmail);
        final EditText editTextName = findViewById(R.id.editTextName);
        Button buttonRegister = findViewById(R.id.buttonRegister);

        // set action to button register
        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = editTextEmail.getText().toString();
                String name = editTextName.getText().toString();

                String welcome = "Welcome, " + name + ". Anda sudah terdaftar dengan email "
                        +email;

                Toast.makeText(MainActivity.this, welcome, Toast.LENGTH_LONG).show();
            }
        });
    }
}
