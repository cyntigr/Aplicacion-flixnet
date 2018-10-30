package com.example.cyntia.flixnet;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.Spanned;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {
    private Button registerBtn;
    private EditText nom,ape,ema;
    private EditText tel,ali,pwd1,pwd2;

    private FirebaseAuth mAuth;
    private FirebaseDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        nom = findViewById(R.id.register_firstName);
        ape = findViewById(R.id.register_lastName);
        ema = findViewById(R.id.register_email);
        tel = findViewById(R.id.register_phoneNumber);
        ali = findViewById(R.id.register_alias);
        pwd1 = findViewById(R.id.register_password);
        pwd2 = findViewById(R.id.register_confirm_password);

        registerBtn = findViewById(R.id.register_btn_send);

        InputFilter alfabeticos = new InputFilter() {
            @Override
            public CharSequence filter(CharSequence source,
                                       int start,
                                       int end, Spanned dest, int dstart, int dend) {
                for(int i=start; i<end; i++){
                    if(Character.isLetter(source.charAt(i))){
                        Toast.makeText(RegisterActivity.this,R.string.register_error_alphabetic,Toast.LENGTH_SHORT).show();
                    }
                }


                return "";
            }
        };
        InputFilter alfaNumerico =new InputFilter() {
            @Override
            public CharSequence filter(CharSequence source,
                                       int start,
                                       int end, Spanned dest, int dstart, int dend) {


                for(int i=start; i<end; i++){
            if(!Character.isLetterOrDigit(source.charAt(i))){
                Toast.makeText(RegisterActivity.this,R.string.register_error_alphaNum,Toast.LENGTH_SHORT).show();
                return "";
            }
                }
                return null;
            }
        };

        nom.setFilters(new InputFilter[] {alfabeticos});
        // usu.setFilters(new InputFilter[] {alfabeticos});
        ape.setFilters(new InputFilter[] {alfabeticos});
        tel.setFilters(new InputFilter[] { new InputFilter.LengthFilter(9) });
        pwd1.setFilters(new InputFilter[] { new InputFilter.LengthFilter(15),alfaNumerico });
        pwd2.setFilters(new InputFilter[] { new InputFilter.LengthFilter(15),alfaNumerico });


        String usuario = ema.getText().toString().trim();
        String clave = pwd1.getText().toString().trim();

        mAuth = FirebaseAuth.getInstance(),
                mAuth.createUserWithEmailAndPassword(usuario,clave)
                        .addOnCompleteListener()
    }
}
