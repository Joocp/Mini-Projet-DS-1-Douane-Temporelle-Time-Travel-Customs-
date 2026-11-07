package com.example.douane_temporelle;                    //el package eli y3arraf el app w ynadham les fishiers

import android.os.Bundle;                                 //classe tgerie les infomations mta l'activite
import androidx.activity.EdgeToEdge;                      //testa3mal l'espace hata lil a7raf mta l'ecran
import androidx.appcompat.app.AppCompatActivity;         //hedhi classe basic beach namlou beha activite android
import androidx.core.graphics.Insets;                    //traj3lna les dimensions mta zone el system
import androidx.core.view.ViewCompat;                    //tapliqui des operations ala interface
import androidx.core.view.WindowInsetsCompat;           //tdecali el formulair beach ynajem yrah el utilisateur

import android.widget.EditText;                        //manipulation de champ de saisir
import android.widget.RadioGroup;                      //manipultion des group de motifs
import android.widget.Button;                          //manipulation de button inspecter

public class MainActivity extends AppCompatActivity {     //MainActivity:hiya el 1st ecran mta activiter
    // manipulation mta les elements el mawjoudin fel xml
      // declaration mta varriables de l'interface
    EditText edtNom, edtNaissance, edtDestination;
    RadioGroup radioMotif;
    Button btnInspecter;
    @Override
    protected void onCreate(Bundle savedInstanceState) { //intialisiw l'interface l'activiter w nhadroulha les element mte3ha
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);    //activation mta el mode EdgeToEdge
        setContentView(R.layout.activity_main);          //tafichie el fichier xml eli kdemtou

        ViewCompat.setOnApplyWindowInsetsListener(        //yamel traitement wakteli les dimesions mta el system tebda mawjouda
                findViewById(R.id.layout_formulaire_os), // tfarkes element bel id mte3ou
                (v, insets) -> {
                    Insets systemBars = insets.getInsets(
                            WindowInsetsCompat.Type.systemBars()
                    );
                    v.setPadding(                         //tjouti espace de5el ala el continue mta el application
                            systemBars.left,
                            systemBars.top,
                            systemBars.right,
                            systemBars.bottom
                    );
                    return insets;
                }
        );
    }


}
