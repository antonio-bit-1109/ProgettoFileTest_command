package command.interf.impl;

import java.util.List;

public abstract class Command<T, U> {
    
    //  void addStudent(String nome, String cognome, int eta);
    public abstract void addStudent(T nome, T cognome, U eta);

    //List<String> getAllStudents();
    public abstract List<T> getAllStudents();

    //String searchStudent(String studentName);
    public abstract T searchStudent(T studentName);

    public abstract void Exit();
}
