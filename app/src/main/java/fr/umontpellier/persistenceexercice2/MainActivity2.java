package fr.umontpellier.persistenceexercice2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MainActivity2 extends AppCompatActivity {
    private static String FILE_NAME = "FILE_NAME";
    private EditText editTexNom, editTextPrenom, editTextAge, editTextPhone;

    Intent intent;
    String file_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        intent = getIntent();
        file_name = intent.getStringExtra(FILE_NAME);

        setContentView(R.layout.activity_main2);

        editTexNom = (EditText) findViewById(R.id.editext_nom);
        editTextPrenom = (EditText) findViewById(R.id.editext_prenom);
        editTextAge = (EditText) findViewById(R.id.editext_age);
        editTextPhone = (EditText) findViewById(R.id.editext_phone);

        String[] text = new String[100];
        try {
           text = readTextFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        editTexNom.setText(text[0]);
        editTextPrenom.setText(text[1]);
        editTextAge.setText(text[2]);
        editTextPhone.setText(text[3]);
    }

    public String[] readTextFile() throws IOException {
        String string = "";
        int i = 0;
        String[] tabSting = new String[100];
        InputStream is = openFileInput(file_name);
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        while (true) {
            try {
                if ((string = reader.readLine()) == null) break;
            }
            catch (IOException e) {
                e.printStackTrace();
            }
            tabSting[i++] = string;
        }
        is.close();
       return tabSting;
    }

}