package com.example.douane_temporelle;                    //El package eli y3arraf el app w ynadham les fishiers

import android.os.Bundle;                                 //Classe tgerie les infomations mta l'activite
import androidx.activity.EdgeToEdge;                      //Testa3mal l'espace hata lil a7raf mta l'ecran
import androidx.appcompat.app.AppCompatActivity;         //Tedhi classe basic beach namlou beha activite android
import androidx.core.graphics.Insets;                    //Traj3lna les dimensions mta zone el system
import androidx.core.view.ViewCompat;                    //Tapliqui des operations ala interface
import androidx.core.view.WindowInsetsCompat;           //Tdecali el formulair beach ynajem yrah el utilisateur

import android.widget.EditText;                        //Manipulation de champ de saisir
import android.widget.RadioGroup;                      //Manipultion des group de motifs
import android.widget.Button;                          //Manipulation de button inspecter

public class MainActivity extends AppCompatActivity {     //MainActivity:hiya el 1st ecran mta activiter

    // declaration mta varriables de l'interface
    EditText edtNom, edtNaissance, edtDestination;
    RadioGroup radioMotif;
    Button btnInspecter;
    @Override
    protected void onCreate(Bundle savedInstanceState) { //Intialisiw l'interface l'activiter w nhadroulha les element mte3ha
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);  //Activation mta el mode EdgeToEdge
        setContentView(R.layout.activity_main);          //Tafichie el fichier xml eli kdemtou

        ViewCompat.setOnApplyWindowInsetsListener(        //Yamel traitement wakteli les dimesions mta el system tebda mawjouda
                findViewById(R.id.layout_formulaire_os), // Tfarkes element bel id mte3ou
                (v, insets) -> {
                    Insets systemBars = insets.getInsets(
                            WindowInsetsCompat.Type.systemBars()
                    );
                    v.setPadding(                         //Tjouti espace de5el ala el continue mta el application
                            systemBars.left,
                            systemBars.top,
                            systemBars.right,
                            systemBars.bottom
                    );
                    return insets;
                }
        );
        // Manipulation mta les elements el mawjoudin fel xml

        //  Norbtou les champs de saisie m3a les éléments XML
        edtNom = findViewById(R.id.edt_nom_os);           // findViewById: Tfarkes al element fel xml
        edtNaissance = findViewById(R.id.edt_naissance_os);    // R.id.edt_nom_os: id mta el champ fel xml
        edtDestination = findViewById(R.id.edt_destination_os);

        //  Norbtou les groupe des motifs
        radioMotif = findViewById(R.id.radio_motif_os);

        //  Norbtou le bouton Inspecter
        btnInspecter = findViewById(R.id.btn_inspecter_os);
    }



}
