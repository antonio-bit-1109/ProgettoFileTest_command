package command.interf;

import java.util.List;

public interface Istudent<T, U, K> {

    //  void addStudent(String nome, String cognome, int eta);
    void addStudent(T nome, T cognome, U eta);

    //List<String> getAllStudents();
    List<K> getAllStudents();

    //String searchStudent(String studentName);
    T searchStudent(T studentName);
}

