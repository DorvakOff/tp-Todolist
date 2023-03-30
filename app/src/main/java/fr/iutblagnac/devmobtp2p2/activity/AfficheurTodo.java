package fr.iutblagnac.devmobtp2p2.activity;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import fr.iutblagnac.devmobtp2p2.R;
import fr.iutblagnac.devmobtp2p2.utils.TodoConstants;

public class AfficheurTodo extends Activity
        implements OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_afficheur_todo);


        // Récupérer l'intent requête
        Intent i;
        i = this.getIntent();

        // Récupérer les datas sur lesquelles travailler
        // l'Id de Todo
        int taskid = i.getIntExtra(TodoConstants.TODO_ID, -1);
        if (taskid == -1) {
            // Si pas de Todo : Réponse de fin cancel
            Toast.makeText(getApplicationContext(), "Pas de tache", Toast.LENGTH_SHORT)
                    .show();
            Intent result = new Intent();
            setResult(Activity.RESULT_CANCELED, result);
            this.finish();
        }

        // Autres infos
        String comm = i.getStringExtra(TodoConstants.TODO_COMMENT);
        String todo = i.getStringExtra(TodoConstants.TODO_TODO);
        String title = i.getStringExtra(TodoConstants.TODO_TITLE);
        String ww = i.getStringExtra(TodoConstants.TODO_WHITHWHO);
        int day, month, year;
        day = i.getIntExtra(TodoConstants.TODO_DUEDAY, 1);
        month = i.getIntExtra(TodoConstants.TODO_DUEMONTH, 1);
        year = i.getIntExtra(TodoConstants.TODO_DUEYEAR, 1999);

        // Positionnement dans les composants du layout
        TextView tv ;

        tv = (TextView) findViewById(R.id.aff_tv_note_id);
        tv.setText(todo);

        tv = (TextView) findViewById(R.id.aff_tv_correspondant_id);
        tv.setText(ww);

        tv = (TextView) findViewById(R.id.aff_tv_commentaire_id);
        tv.setText(comm);


        DatePicker dp = (DatePicker)findViewById(R.id.aff_dp_pour_id);
        dp.init(year, month-1, day,null);
        dp.setEnabled(false);

        tv = (TextView) findViewById(R.id.aff_tv_titre_id);
        tv.setText(title);

        Button bok = (Button) findViewById(R.id.aff_but_annuler_id);
        bok.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        // A la fin : Réponse de fin ok
        Intent result = new Intent();
        result.putExtra(TodoConstants.RESAFFICHEURTODO, "Une valeur de retour pour tester ...");
        setResult(Activity.RESULT_OK, result);
        this.finish();
    }
}
