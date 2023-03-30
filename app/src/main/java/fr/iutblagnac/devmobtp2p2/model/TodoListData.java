package fr.iutblagnac.devmobtp2p2.model;

import java.util.ArrayList;
import java.util.List;

public class TodoListData {

    private List<Todo> internalTodoList;

    public TodoListData() {

        this.internalTodoList = new ArrayList<>();
        this.internalTodoList.add(new Todo("devoir 1",
                "Deposer le TP1 de CDAM deja en retard"));
        this.internalTodoList.add(new Todo("devoir 2",
                "Deposer le TP2 de CDAM deja en retard"));
        this.internalTodoList.add(new Todo("devoir 3",
                "Deposer le TP3 de CDAM, il est encore temps"));
        this.internalTodoList.add(new Todo("Courses",
                "Du lait, du beurre et des epinards"));
        this.internalTodoList.add(new Todo("Fete",
                "Organiser jeudi soir !! avant jeudi soir !!"));
        this.internalTodoList.add(new Todo("devoir 4",
                "Deposer le TP4, il est encore temps", "My colleague", "", 23, 3, 2023));
        this.internalTodoList.add(new Todo("PJT", "Preparer rendu pjt",
                "My colleagues", "Ya du taf a faire\nFaut pas trainer",
                24, 3, 2023));
        for (int i=0; i<10; i++) {
            this.internalTodoList.add(new Todo("Titre " + i, "Todo " + i));
        }
    }

    public int size() {
        return this.internalTodoList.size();
    }

    public Todo get(int position) {
        return this.internalTodoList.get(position);
    }

    public Todo getById (int id) {
        for (Todo t : this.internalTodoList) {
            if (t.getId() == id) {
                return t;
            }
        }
        return null;
    }

    public boolean add(Todo td) {
        return this.internalTodoList.add(td);
    }

    public void removeById(int id) {
        for (int i = 0; i < this.internalTodoList.size(); i++) {
            Todo d = this.internalTodoList.get(i);
            if (d.getId() == id) {
                this.internalTodoList.remove(d);
                return;
            }
        }
    }

    public void remove(Todo t) {
        this.internalTodoList.remove(t);
    }

    public void setValuesAt (int position, String title, String todo, String withWho, String comment,
                           int day, int month, int year) {
        this.internalTodoList.get(position).setValues(title, todo, withWho, comment, day, month, year);
    }
}

