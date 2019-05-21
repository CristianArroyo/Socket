package com.example.cncarroyo.comunicacionsocket;

import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class MainActivity extends AppCompatActivity {

    private Socket cliente;
    private PrintWriter printWriter;
    private EditText Ip, Puerto , Msg;
    private Button enviar;
    private String message;
    int port=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Ip = (EditText) findViewById(R.id.edIP);
        Puerto =(EditText) findViewById(R.id.edPuerto);
        Msg = (EditText) findViewById(R.id.edMensaje);
        enviar =(Button) findViewById(R.id.btnEnviart);

        enviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

              message = Msg.getText().toString();
              port= Integer.parseInt(Puerto.getText().toString());

              new Thread(new Runnable() {
                  @Override
                  public void run() {

                      try{

                          cliente = new Socket(Ip.getText().toString(),port);
                          printWriter= new PrintWriter(cliente.getOutputStream());
                          printWriter.write(message);
                          printWriter.flush();
                          printWriter.close();
                          cliente.close();


                      }catch (IOException e){

                          e.printStackTrace();

                      }



                  }
              }).start();


            }
        });

    }
}
