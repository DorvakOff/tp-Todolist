package fr.iutblagnac.devmobtp2p2.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import fr.iutblagnac.devmobtp2p2.R;
import fr.iutblagnac.devmobtp2p2.activity.MainActivity;
import fr.iutblagnac.devmobtp2p2.utils.TodoConstants;

public class EditeurTodo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editeur_todo);


    }

    public void doCancel(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        setResult(RESULT_CANCELED, intent);
        finish();
    }

    public void doValidate(View view) {
        DatePicker dp = findViewById(R.id.dp_date_id);
        EditText title = findViewById(R.id.et_enter_title);
        EditText todo = findViewById(R.id.et_enter_todo);
        EditText comment = findViewById(R.id.et_comment);
        EditText withWho = findViewById(R.id.et_personnes);

        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra(TodoConstants.TODO_TITLE, title.getText().toString());
        intent.putExtra(TodoConstants.TODO_TODO, todo.getText().toString());
        intent.putExtra(TodoConstants.TODO_COMMENT, comment.getText().toString());
        intent.putExtra(TodoConstants.TODO_WHITHWHO, withWho.getText().toString());
        intent.putExtra(TodoConstants.TODO_DUEDAY, dp.getDayOfMonth());
        intent.putExtra(TodoConstants.TODO_DUEMONTH, dp.getMonth());
        intent.putExtra(TodoConstants.TODO_DUEYEAR, dp.getYear());
        setResult(RESULT_OK, intent);
        finish();
    }

    public void makeToast(String message) {
        Toast toast = Toast.makeText(this, message, Toast.LENGTH_SHORT);
        toast.show();
    }
}