package com.example.douane_temporelle;                    // El package eli y3arraf el app w ynadham les fishiers

import android.os.Bundle;                                 // Classe tgerie les infomations mta l'activite
import androidx.activity.EdgeToEdge;                      // Testa3mal l'espace hata lil a7raf mta l'ecran
import androidx.appcompat.app.AppCompatActivity;          // Tedhi classe basic beach namlou beha activite android
import androidx.core.graphics.Insets;                     // Traj3lna les dimensions mta zone el system
import androidx.core.view.ViewCompat;                     // Tapliqui des operations ala interface
import androidx.core.view.WindowInsetsCompat;             // Tdecali el formulair beach ynajem yrah el utilisateur
import android.widget.EditText;                           // Manipulation de champ de saisir
import android.widget.RadioGroup;                         // Manipultion des group de motifs
import android.widget.Button;                             // Manipulation de button inspecter
import android.widget.Toast;                              // Ykharej message sghir temporaire ala l'ecron EXP:"Vouilliez remplir tous les champs"
import java.util.Calendar;                                // Trajalek el annee actuelle
import android.widget.RadioButton;                        // Nesthakoha ki beach nraj3ou continue mta motif
import android.content.Intent;                            // Nesthakoha ki beach nab3thhou des donnes men classe l classe o5ra

public class MainActivity extends AppCompatActivity {     // MainActivity:hiya el 1st ecran mta activiter

    // declaration mta varriables de l'interface
    EditText edtNom, edtNaissance, edtDestination;
    RadioGroup radioMotif;
    Button btnInspecter;


    @Override
    protected void onCreate(Bundle savedInstanceState) { // Intialisiw l'interface l'activiter w nhadroulha les element mte3ha
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);  // Activation mta el mode EdgeToEdge
        setContentView(R.layout.activity_main);          // Tafichie el fichier xml eli kdemtou

        ViewCompat.setOnApplyWindowInsetsListener(        // Yamel traitement wakteli les dimesions mta el system tebda mawjouda
                findViewById(R.id.layout_formulaire_os), // Tfarkes element bel id mte3ou
                (v, insets) -> {
                    Insets systemBars = insets.getInsets(
                            WindowInsetsCompat.Type.systemBars()
                    );
                    v.setPadding(                         // Tjouti espace de5el ala el continue mta el application
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

        // Bech neprogramiw el nazla mta el bouton "Inspecter" beach tamel collection lel les donners eli maktoubin fel interface mte3na w tab3athom lil activite2 (le scanner de paradoxe)


        btnInspecter.setOnClickListener(v -> {     // setOnClickListener: tdetecti el click ala el bouton

            // Trecuperie les informations el maktoubin fel les champs de saisir
            String nom = edtNom.getText().toString().trim();            //toString(): Tronsformie el maktoub fel in input en chaine de caractere
            String naissance = edtNaissance.getText().toString().trim(); //trim(): Tnadhef les espaces el mawjoudin fel les exptrmiter mta el chaine
            String destination = edtDestination.getText().toString().trim();

            // Tetaked  eli un motif est sélectionné
            int motifSelectionne = radioMotif.getCheckedRadioButtonId(); // getCheckedRadioButtonId(): traje3 le id mta el motif eli tselectionnet


            // Vérifier si les champs sont vides
            if (nom.isEmpty() ||
                    naissance.isEmpty() ||
                    destination.isEmpty() ||
                    motifSelectionne == -1) {

                Toast.makeText(                                         // makeText(): traje3 message temporaire
                        MainActivity.this,
                        "Veuillez remplir tous les champs !",
                        Toast.LENGTH_SHORT
                ).show();                                               // Tahfichi el message ala el interface

                return;
            }

            // Récupérer le continue de motif choisie
            RadioButton radioChoisi = findViewById(motifSelectionne);  // findViewById():Tlawaj el bouton radio selectionnee
            String motif = radioChoisi.getText().toString();


            // Idha ken el esm voyageur mahouch chaine de caractere bech yokhrojlek message temporaire ykolek rouhou el nom lezmou ykoun fromat chaine
            if (!nom.matches("[\\p{L} ]+")) { // Tekbel des les forma des lettres, espaces, accents, espaces et refuse les chiffres

                Toast.makeText(MainActivity.this,
                        "Le nom doit contenir uniquement des lettres !",
                        Toast.LENGTH_SHORT).show();
                return;
            }

            // annee de naissance lazmou ykoun number
            if (!naissance.matches("[0-9]+")) { // Tekbel uniquement les chiffres ala akal wehed

                Toast.makeText(MainActivity.this,
                        "L'année de naissance doit contenir uniquement des chiffres !",
                        Toast.LENGTH_SHORT).show();
                return;
            }

            // annee de destination lazmou ykoun number
            if (!destination.matches("[0-9]+")) {

                Toast.makeText(MainActivity.this,
                        "L'année de destination doit contenir uniquement des chiffres !",
                        Toast.LENGTH_SHORT).show();
                return;
            }

            // Beach nverifiw el cohearance bin annee de naissance et e de destination
            int anneeNaissance;
            int anneeDestination;
            try {

                // Beach nparssiw el les chaine de caracter mta les champs annees lil int
                 anneeNaissance = Integer.parseInt(naissance);
                 anneeDestination = Integer.parseInt(destination);

                // Récupérer l'année actuelle automatiquement
                int anneeActuelle = Calendar.getInstance().get(Calendar.YEAR);

                // Vérifier que l'année de naissance est positive
                if (anneeNaissance <= 0) {

                    Toast.makeText(MainActivity.this,
                            "L'année de naissance doit être positive !",
                            Toast.LENGTH_SHORT).show();
                    return;
                }

                // Vérifier que l'année de naissance n'est pas dans le futur
                if (anneeNaissance > anneeActuelle) {

                    Toast.makeText(MainActivity.this,
                            "L'année de naissance ne peut pas être dans le futur !",
                            Toast.LENGTH_SHORT).show();
                    return;
                }

                // Vérifier que l'année de destination est positive
                if (anneeDestination <= 0) {

                    Toast.makeText(MainActivity.this,
                            "L'année de destination doit être positive !",
                            Toast.LENGTH_SHORT).show();
                    return;
                }

                // Vérifier que les deux années sont différentes
                if (anneeActuelle == anneeDestination) {

                    Toast.makeText(MainActivity.this,
                            "Tu est deja dans cette annee !",
                            Toast.LENGTH_SHORT).show();
                    return;
                }

            } catch (NumberFormatException e) {

                // Éviter une erreur de conversion
                Toast.makeText(MainActivity.this,
                        "Année invalide !",
                        Toast.LENGTH_SHORT).show();
                return;
            }



            // Créer un Intent pour passer vers l'activité 2
            Intent intent = new Intent(MainActivity.this, Verif_Activity.class); // Intent():bech najmou nemchiw men activite l activite o5ra

            // Envoyer les informations du voyageur
            intent.putExtra("4198_NOM_VOYAGEUR", nom);  // Beach nab3thou les informations lel activite lor5ra
            intent.putExtra("4198_ANNEE_NAISSANCE", anneeNaissance);
            intent.putExtra("4198_ANNEE_DESTINATION", anneeDestination);
            intent.putExtra("4198_MOTIF", motif);

          // Ouvrir l'activité 2
            startActivity(intent);



        });
    }



}
