package fr.iutblagnac.devmobtp2p2.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import fr.iutblagnac.devmobtp2p2.R;
import fr.iutblagnac.devmobtp2p2.model.Todo;
import fr.iutblagnac.devmobtp2p2.model.TodoListData;
import fr.iutblagnac.devmobtp2p2.utils.StringUtils;

public class TodoListAdapter extends BaseAdapter {

    private final Context context;
    private final LayoutInflater inflater;
    private final TodoListData todoListData;

    public TodoListAdapter(Context context, TodoListData todoListData) {
        this.context = context;
        this.inflater = LayoutInflater.from(context);
        this.todoListData = todoListData;
    }

    @Override
    public int getCount() {
        return todoListData.size();
    }

    @Override
    public Todo getItem(int pos) {
        return todoListData.get(pos);
    }

    @Override
    public long getItemId(int pos) {
        return getItem(pos).getId();
    }

    @Override
    public View getView(int pos, View convertView, ViewGroup viewGroup) {
        LinearLayout ll;

        if (convertView == null) {
            ll = (LinearLayout) inflater.inflate(R.layout.todo_view, viewGroup, false);
        } else {
            ll = (LinearLayout) convertView;
        }

        TextView tvDate = ll.findViewById(R.id.tv_todoviewer_date_id);
        TextView tvTitle = ll.findViewById(R.id.tv_todoviewer_titre_id);
        TextView tvTodo = ll.findViewById(R.id.tv_todoviewer_todo_id);
        TextView tvComment = ll.findViewById(R.id.tv_todoviewer_wwcomment_id);

        Todo todo = getItem(pos);

        tvDate.setText(todo.getDayDue() + "-" + todo.getMonthDue() + "-" + todo.getYearDue());
        tvTitle.setText(todo.getId() + " " + todo.getTitle());
        tvTodo.setText(StringUtils.truncate(todo.getTodo(), 40));
        tvComment.setText(StringUtils.truncate(todo.getWithWho() + " " + todo.getComment(), 40));

        int color = Color.BLACK;

        if (pos % 2 == 1) {
            color = Color.GRAY;
        }

        tvDate.setTextColor(color);
        tvTitle.setTextColor(color);
        tvTodo.setTextColor(color);
        tvComment.setTextColor(color);

        return ll;
    }
}
