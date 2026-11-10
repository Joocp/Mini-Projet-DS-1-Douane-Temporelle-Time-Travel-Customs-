package com.example.douane_temporelle;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ResultActivity extends AppCompatActivity {

    //variable fiha el matricule mta3 el groupe
    private final String matricule = "4198";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // calls parent method
        super.onCreate(savedInstanceState);

        //norbto el activity bel fichier XML elli fih el interface(vue) mta3 el résultat
        setContentView(R.layout.activity_result_layout);

        //njibou les composants graphiques elli mawjouda fel fichier XML
        LinearLayout layout = findViewById(R.id.activity_result_layout);
        Button btnNouveau = findViewById(R.id.btn_nouveau_1);
        TextView txtVerdict = findViewById(R.id.txt_verdict_1);
        TextView txtMessage = findViewById(R.id.txt_message_1);

        //on recoit esm el voyageur elli jet men MainActivity
        String nom = getIntent().getStringExtra(
                ExtraKeys.construireCle(matricule, "NOM_VOYAGEUR")
        );

        //on recoit el verdict -> true ken el voyage jawo ta7foon, false ken ma9boulch
        boolean verdict = getIntent().getBooleanExtra(
                ExtraKeys.construireCle(matricule, "VERDICT"), false
        );

        //nethabtou ken el esm mawjoud w moch fera8 bech ma ysirish crash lel application
        if (nom == null || nom.trim().isEmpty()) {
            nom = "Voyageur";
        }

        //naffichiw el resultat 7asb el verdict elli wsil

        if (verdict) {
            layout.setBackgroundColor(Color.GREEN);
            txtVerdict.setText("Voyage approuvé");
            txtMessage.setText("Bon voyage " + nom + " !");

        } else {

            layout.setBackgroundColor(Color.RED);
            txtVerdict.setText("Voyage refusé");
            txtMessage.setText(nom + " est en état d'arrestation temporelle !");
        }

        //n9aroro chnowa ysir ki el utilisateur yenzel 3al bouton "Nouveau voyage" " (lambda concept bch na5tasro anonymous class")
        btnNouveau.setOnClickListener(v -> {

            //na3mlou Intent bech narj3ou lel MainActivity
            Intent retour = new Intent(ResultActivity.this, MainActivity.class);

            //nfas5ou les Activities elli fou9 MainActivity w ken mawjoud deja nesta3mlou nafs el instance
            retour.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            // Lance MainActivity
            startActivity(retour);
            // Ferme l'Activity actuelle (ResultActivity)
            finish();
        });

    }
}
