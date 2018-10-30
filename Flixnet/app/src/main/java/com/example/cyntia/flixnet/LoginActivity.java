package com.example.cyntia.flixnet;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.cyntia.flixnet.modelos.Respuesta;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {
    private Button btnLogin, btnRegister;
    private EditText user, password;
    private final String DUMMY_USER = "Bruce";
    private final String DUMMY_PASS = "iambatman";
    private final String API_KEY = "kdj";
    private final String usr = user.getText().toString().trim();
    private final String clv = password.getText().toString().trim();


    private RequestQueue queue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        queue = Volley.newRequestQueue(this);

        user = findViewById(R.id.loginUsuario);
        password = findViewById(R.id.loginPassword);
        btnLogin = findViewById(R.id.login_button);
        btnRegister = findViewById(R.id.register_button);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,
                        RegisterActivity.class);
                startActivity(intent);

            }
        });
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String usr = user.getText().toString().trim();
                String pass = password.getText().toString().trim();
                if (usr.isEmpty() || pass.isEmpty()) {
                    Snackbar.make(v, R.string.login_error_login, Snackbar.LENGTH_LONG);
                } else {

                    // Definimos la URL de acceso a la API. Recuerda que debes utilizar la IP de tu máquina (no vale 127.0.0.1 o localhost)
                    String ruta = "" ;

// Creamos una solicitud de acceso a la URL anterior.
// Necesitamos especificar: método (GET|POST), ruta y dos listeners.
                    StringRequest jsonreq = new StringRequest(
                            Request.Method.POST,
                            ruta,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {

                                    // Si la solicitud se ha realizado con éxito, procesamos de manera apropiada,
                                    // la información obtenida en RESPONSE.
                                    Toast.makeText(LoginActivity.this,response, Toast.LENGTH_LONG).show();
                                    // A partir del constructor anterior, instanciamos un objeto de tipo gson.
                                    //ESte será el encargado fr parsear la cadena y deserializarla. permitiendonos obtener
                                    // el objeto del tipo respuesta.
                                    GsonBuilder builder = new GsonBuilder();
                                    Gson gson = builder.create();

                                    //Deserializamos la cadena response y obtenemos un objeto
                                    // de tipo respuesta.
                                    Respuesta respuesta = gson.fromJson(response,Respuesta.class);

                                }
                            },

                            new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {

                                    // Se la solicitud no se ha completado con éxito, procesamos el error de la
                                    // manera que estimemos oportuna, según la información obtenida a través del
                                    // objeto ERROR de tipo VOLLEYERROR.
                                    Log.e("FLIXNET_LOGIN", error.getMessage()) ;
                                }
                            }
                    )
                    {

                        // El método getParams de la clase Request<T>, nos permite definir aquellos parámetros
                        // que necesitemos enviar a través de una petición POST.
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {

                            Map<String, String> param = new HashMap<String,String>() ;

                            param.put("token",API_KEY) ;
                            param.put("codigo","1") ;
                            param.put("usr",usr) ;
                            param.put("pwd",clv) ;

                            return param ;
                        }

                        // El método getHeaders de la clase Request<T>, nos permite modificar la cabecera del
                        // mensaje HTTP que enviamos al servidor. En este caso, nos bastará con establecer el
                        // valor del parámetro Content-Type, indicando que los parámetros enviados deberán
                        // ser codificados en tuplas clave, valor.
                        @Override
                        public Map<String, String> getHeaders() throws AuthFailureError {

                            Map<String, String> param = new HashMap<String,String>() ;
                            param.put("Content-Type","application/x-www-form-urlencoded") ;

                            return param ;
                        }
                    } ;

// Finalmente, añadimos la petición a la cola de Volley.
                    queue.add(jsonreq) ;

                /*if(usr == DUMMY_USER && pass == DUMMY_PASS){
                    Intent intent = new Intent(LoginActivity.this,
                            ListActivity.class);
                    startActivity(intent);

                } else {
                    Snackbar.make(v, R.string.error_login, Snackbar.LENGTH_LONG);
                }*/

                }
            }
        });



    }

}
