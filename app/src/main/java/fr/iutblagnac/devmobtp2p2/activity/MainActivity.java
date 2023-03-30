package fr.iutblagnac.devmobtp2p2.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import fr.iutblagnac.devmobtp2p2.R;
import fr.iutblagnac.devmobtp2p2.adapter.TodoListAdapter;
import fr.iutblagnac.devmobtp2p2.model.Todo;
import fr.iutblagnac.devmobtp2p2.model.TodoListData;
import fr.iutblagnac.devmobtp2p2.utils.TodoConstants;

public class MainActivity extends AppCompatActivity {

    private final ActivityResultLauncher<Intent> rLauncherView = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
        if (result.getResultCode() == RESULT_CANCELED) {
            Toast.makeText(this, "Modification annulée", Toast.LENGTH_SHORT).show();
        } else {
            Intent data = result.getData();
            assert data != null;
            String resultString = data.getStringExtra(TodoConstants.RESAFFICHEURTODO);
            Toast.makeText(this, resultString, Toast.LENGTH_SHORT).show();
        }
    });
    private TodoListAdapter tlAdapter;
    private TodoListData tld;
    private final ActivityResultLauncher<Intent> rLauncherAdd = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
        if (result.getResultCode() == RESULT_OK) {
            Intent data = result.getData();
            assert data != null;
            String title = data.getStringExtra(TodoConstants.TODO_TITLE);
            String todo = data.getStringExtra(TodoConstants.TODO_TODO);
            int dayDue = data.getIntExtra(TodoConstants.TODO_DUEDAY, 0);
            int monthDue = data.getIntExtra(TodoConstants.TODO_DUEMONTH, 0);
            int yearDue = data.getIntExtra(TodoConstants.TODO_DUEYEAR, 0);
            String comment = data.getStringExtra(TodoConstants.TODO_COMMENT);
            String withWho = data.getStringExtra(TodoConstants.TODO_WHITHWHO);

            Todo newTodo = new Todo(title, todo, withWho, comment, dayDue, monthDue, yearDue);
            tld.add(newTodo);
            tlAdapter.notifyDataSetChanged();
        }
    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tld = new TodoListData();
        tlAdapter = new TodoListAdapter(this, tld);

        ListView lvTodo = findViewById(R.id.lv_todo_list);
        lvTodo.setAdapter(tlAdapter);

        this.registerForContextMenu(lvTodo);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        MenuInflater inflater = getMenuInflater();

        menu.setHeaderTitle(getResources().getString(R.string.listview_menu_title));

        inflater.inflate(R.menu.list_todo_menu, menu);

        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        int index = info.position;

        Todo todo = tlAdapter.getItem(index);

        if (item.getItemId() == R.id.listview_menu_delete_todo_id) {
            handleDeleteTodoItem(todo);
            return true;
        } else if (item.getItemId() == R.id.listview_menu_modify_todo_id) {
            Toast.makeText(this, "Modification " + todo, Toast.LENGTH_SHORT).show();
        } else if (item.getItemId() == R.id.listview_menu_show_todo_id) {
            handleShowTodoItem(todo);
            return true;
        }

        return super.onContextItemSelected(item);
    }

    private void handleShowTodoItem(Todo todo) {
        Intent intent = new Intent(this, AfficheurTodo.class);

        intent.putExtra(TodoConstants.TODO_TITLE, todo.getTitle());
        intent.putExtra(TodoConstants.TODO_TODO, todo.getTodo());
        intent.putExtra(TodoConstants.TODO_DUEDAY, todo.getDayDue());
        intent.putExtra(TodoConstants.TODO_DUEMONTH, todo.getMonthDue());
        intent.putExtra(TodoConstants.TODO_DUEYEAR, todo.getYearDue());
        intent.putExtra(TodoConstants.TODO_COMMENT, todo.getComment());
        intent.putExtra(TodoConstants.TODO_WHITHWHO, todo.getWithWho());
        intent.putExtra(TodoConstants.TODO_ID, todo.getId());

        rLauncherView.launch(intent);
    }

    private void handleDeleteTodoItem(Todo todo) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(getResources().getString(R.string.listview_menu_delete_todo_title));
        builder.setMessage(todo.getTitle() + " : " + todo.getTodo());
        builder.setPositiveButton(getResources().getString(R.string.ok), (dialog, which) -> {
            tld.removeById(todo.getId());
            tlAdapter.notifyDataSetChanged();
            dialog.dismiss();
        });
        builder.setNegativeButton(getResources().getString(R.string.cancel), (dialog, which) -> dialog.cancel());
        builder.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_options, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.main_menu_ajouter_todo_id) {
            Intent intent = new Intent(this, EditeurTodo.class);
            rLauncherAdd.launch(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}