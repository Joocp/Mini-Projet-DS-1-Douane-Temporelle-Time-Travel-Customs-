package com.example.douane_temporelle;

import android.content.Intent;                    //  Bech neb3thou l verdict l Activité 3
import android.os.Bundle;                         //  trigel el info t3 activité
import android.widget.Button;                     // el buttons
import android.widget.ImageView;                  // Tsawira 3al ysar
import android.widget.TextView;                   // ktiba 3al ymin

import androidx.appcompat.app.AppCompatActivity;  // trigel compatabilité m3a version android 9dom

public class Verif_Activity extends AppCompatActivity {

    //  Matricule (4198) — nafsha f Main, Verif, w Result
    private static final String MATRICULE = "4198";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verif_layout);            //  Narbtou el layout

        // Nrbtou kol object bel ID mte3ou
        ImageView imgBillet    = findViewById(R.id.img_billet_yg);
        TextView  txtNom       = findViewById(R.id.txt_nom_yg);
        TextView  txtAge       = findViewById(R.id.txt_age_yg);
        TextView  txtMotif     = findViewById(R.id.txt_motif_yg);
        Button    btnApprouver = findViewById(R.id.btn_approuver_yg);
        Button    btnRefuser   = findViewById(R.id.btn_refuser_yg);

        // ne5dhou donnée mel MainActivity t3 omar b les clés dynamiques
        String nomRecu = getIntent().getStringExtra(
                ExtraKeys.construireCle(MATRICULE, "NOM_VOYAGEUR"));
        int anneeNaissance = getIntent().getIntExtra(
                ExtraKeys.construireCle(MATRICULE, "ANNEE_NAISSANCE"), 0);
        int anneeDestination = getIntent().getIntExtra(
                ExtraKeys.construireCle(MATRICULE, "ANNEE_DESTINATION"), 0);
        String motifRecu = getIntent().getStringExtra(
                ExtraKeys.construireCle(MATRICULE, "MOTIF"));

        // 7maya men null — na3mlou des variables FINAL bech nesta3mlouhom f les lambdas
        final String nom   = (nomRecu == null || nomRecu.trim().isEmpty()) ? "Voyageur" : nomRecu;
        final String motif = (motifRecu == null) ? "" : motifRecu;

        // 7seb el age temporel = destination - naissance
        int ageTemporel = anneeDestination - anneeNaissance;

        // hedha lpassport
        txtNom.setText("Nom : " + nom);
        txtAge.setText("Âge temporel : " + ageTemporel + " ans");
        txtMotif.setText("Motif : " + motif);

        // ken el motif modification de passé w age temporel noumrou premier, nsakrou lbouton approuver
        boolean motifModif = motif.trim().equalsIgnoreCase("Modification du passé");
        boolean agePremier = estPremier(ageTemporel);

        if (motifModif && agePremier) {
            btnApprouver.setEnabled(false);
            btnApprouver.setAlpha(0.5f);
        }

        btnApprouver.setOnClickListener(v -> lancerVerdict(nom, true));
        btnRefuser.setOnClickListener(v -> lancerVerdict(nom, false));
    }

    // naba3thou nom wel verdict ResultActivity
    private void lancerVerdict(String nom, boolean verdict) {
        Intent intent = new Intent(Verif_Activity.this, ResultActivity.class);
        intent.putExtra(ExtraKeys.construireCle(MATRICULE, "NOM_VOYAGEUR"), nom);
        intent.putExtra(ExtraKeys.construireCle(MATRICULE, "VERDICT"), verdict);
        startActivity(intent);
    }

    // noumrou premier?
    private boolean estPremier(int n) {
        n=Math.abs(n);
        if (n < 2) return false;
        for (int i = 2; i * i <= n; i++) {
            if (n % i == 0) return false;
        }
        return true;
    }
}
