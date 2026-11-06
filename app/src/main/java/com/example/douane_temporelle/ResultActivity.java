package com.example.douane_temporelle;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ResultActivity extends AppCompatActivity {

    // variable temporaire
    private final String matricule = "1234";    // Omar wala youssef a3tiwna lmatricule bch ni5dim beha

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // calls parent method
        super.onCreate(savedInstanceState);

        // Associe cette Activity au fichier XML qui contient son interface
        setContentView(R.layout.activity_result_layout);

        //  récupère tout les composants graphiques définis dans le fichier xml
        LinearLayout layout = findViewById(R.id.activity_result_layout);
        Button btnNouveau = findViewById(R.id.btn_nouveau_1);
        TextView txtVerdict = findViewById(R.id.txt_verdict_1);
        TextView txtMessage = findViewById(R.id.txt_message_1);

        String nom = getIntent().getStringExtra(
                ExtraKeys.construireCle(matricule, "NOM_VOYAGEUR")
        );

        boolean verdict = getIntent().getBooleanExtra(
                ExtraKeys.construireCle(matricule, "VERDICT"), false
        );

        //  null check first to not crash after using trim() --> "nullPointerException"
        if (nom == null || nom.trim().isEmpty()) {
            nom = "Voyageur";
        }

        // Affiche le résultat selon le verdict reçu

        if (verdict) {
            layout.setBackgroundColor(Color.GREEN);
            txtVerdict.setText("Voyage approuvé");
            txtMessage.setText("Bon voyage " + nom + " !");

        } else {

            layout.setBackgroundColor(Color.RED);
            txtVerdict.setText("Voyage refusé");
            txtMessage.setText(nom + " est en état d'arrestation temporelle !");
        }

        // Définit l'action à effectuer lorsque l'utilisateur clique sur le bouton "lambda concept to make it shorter"
        btnNouveau.setOnClickListener(v -> {

            // Crée un Intent explicite pour retourner à MainActivity
            Intent retour = new Intent(ResultActivity.this, MainActivity.class);

            // Supprime les Activities situées au-dessus de MainActivity et permet de réutiliser son instance si elle est déjà présente
            retour.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            // Lance MainActivity
            startActivity(retour);
            // Ferme l'Activity actuelle (ResultActivity)
            finish();
        });

    }
}