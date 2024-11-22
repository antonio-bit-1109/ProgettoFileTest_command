package manager;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class StudentManagerTest {

    private static final String TEST_FILE = "test_students.txt";

    @BeforeEach
    void setUp() throws IOException {
        // Crea un file vuoto prima di ogni test
        File file = new File(TEST_FILE);
        if (!file.exists()) {
            file.createNewFile();
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(TEST_FILE))) {
            writer.write(""); // Pulisce il file
        }
    }

    @AfterEach
    void tearDown() {
        // Elimina il file dopo ogni test
        File file = new File(TEST_FILE);
        if (file.exists()) {
            file.delete();
        }
    }

    @Test
    void testAddStudent() throws IOException {
        StudentManager manager = new StudentManager(TEST_FILE);
        manager.addStudent("Mario", "Rossi", 20);

        List<String> students = manager.getAllStudents();
        assertEquals(1, students.size());
        assertEquals("Mario,Rossi,20", students.get(0));
    }

    @Test
    void testGetAllStudents() throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(TEST_FILE))) {
            writer.write("Mario,Rossi,20\nLucia,Bianchi,22\n");
        }

        StudentManager manager = new StudentManager(TEST_FILE);
        List<String> students = manager.getAllStudents();

        assertEquals(2, students.size());
        assertEquals("Mario,Rossi,20", students.get(0));
        assertEquals("Lucia,Bianchi,22", students.get(1));
    }

    @Test
    void testSearchStudent() throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(TEST_FILE))) {
            writer.write("Mario,Rossi,20\nLucia,Bianchi,22\n");
        }

        StudentManager manager = new StudentManager(TEST_FILE);
        String result = manager.searchStudent("Lucia");

        assertEquals("Lucia,Bianchi,22", result);
    }

    @Test
    void testSearchStudentNotFound() throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(TEST_FILE))) {
            writer.write("Mario,Rossi,20\n");
        }

        StudentManager manager = new StudentManager(TEST_FILE);
        String result = manager.searchStudent("Anna");

        assertNull(result, "Expected null when student is not found");
    }

    @Test
    void testHandleEmptyFile() throws IOException {
        StudentManager manager = new StudentManager(TEST_FILE);
        List<String> students = manager.getAllStudents();

        assertTrue(students.isEmpty(), "Expected an empty list for an empty file");
    }
}
