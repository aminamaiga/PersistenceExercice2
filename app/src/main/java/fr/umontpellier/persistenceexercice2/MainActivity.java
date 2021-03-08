package fr.umontpellier.persistenceexercice2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    String fileName;
    FileOutputStream fos;
    int Id_gen;
    Button buttonValider, soumettre;

    private EditText editTexNom, editTextPrenom, editTextAge, editTextPhone;
    private final static String NOM = "NOM", PRENOM = "PRENOM", AGE = "AGE", PHONE = "PHONE", ID_USER = "ID_USER" ;
    private static String FILE_NAME = "FILE_NAME";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTexNom = (EditText) findViewById(R.id.editext_nom);
        editTextPrenom = (EditText) findViewById(R.id.editext_prenom);
        editTextAge = (EditText) findViewById(R.id.editext_age);
        editTextPhone = (EditText) findViewById(R.id.editext_phone);
        buttonValider = (Button)findViewById(R.id.valider);
        soumettre = (Button)findViewById(R.id.soumettre);

        Id_gen = getRandomNumber();

        buttonValider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sauvegarder();
            }
        });

        soumettre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sauvegarder();

                Intent i = new Intent(getApplicationContext(), MainActivity2.class);
                i.putExtra(FILE_NAME, fileName);
                startActivity(i);
            }
        });

    }

    public void sauvegarder(){
        fileName = Id_gen + editTextPrenom.getText().toString();
        String data = editTextPrenom.getText() + "\n"
                    + editTextPrenom.getText() + "\n"
                     + editTextAge.getText() + "\n"
                        + editTextPhone.getText() + "\n";
        try {
           fos = openFileOutput(fileName, Context.MODE_APPEND);
            try {
                fos.write(data.getBytes());
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(NOM, editTexNom.getText().toString());
        outState.putString(PRENOM, editTextPrenom.getText().toString());
        outState.putString(AGE, editTextAge.getText().toString());
        outState.putString(PHONE, editTextPhone.getText().toString());
           }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Toast.makeText(getApplicationContext(), "L'etat de l'activité est detruit", Toast.LENGTH_LONG).show();
    }

    public static int getRandomNumber(){
        int x = (int) Math.random();
        return x;
    }
}