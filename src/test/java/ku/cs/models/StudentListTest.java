package ku.cs.models;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class StudentListTest {
    @Test
    @DisplayName("ทดสอบการเพิ่มนักเรียนใหม่")
    void testAddNewStudent() {
        StudentList list = new StudentList();
        list.addNewStudent("1", "Alice");
        assertEquals(1, list.getStudents().size());
        assertEquals("Alice", list.getStudents().get(0).getName());
    }

    @Test
    @DisplayName("ทดสอบการเพิ่มนักเรียนใหม่ใส่คะแนน")
    void testAddStudent() {
        StudentList list = new StudentList();
        list.addNewStudent("1", "Alice", 80);
        assertEquals(1, list.getStudents().size());
        assertEquals("Alice", list.getStudents().get(0).getName());
        assertEquals(80, list.getStudents().get(0).getScore());
    }

    @Test
    @DisplayName("หาว่าเจอหรือไม่เจอ")
    void testfindStudentById() {
        StudentList list = new StudentList();
        list.addNewStudent("1", "Alice", 80);
        list.addNewStudent("18", "Havert", 60);

        Student s1 = list.findStudentById("1");
        assertNotNull(s1); //หาเจอ
        assertEquals("Alice", s1.getName());

        Student s2 = list.findStudentById("19");
        assertNull(s2); //ไม่เจอ
    }

    @Test
    @DisplayName("หาว่าค้นหาชื่อเจอไหม")
    void testfilterByName() {
        StudentList list = new StudentList();
        list.addNewStudent("1", "Alice", 80);
        list.addNewStudent("18", "Havert", 60);

        StudentList result1 = list.filterByName("ali");
        assertEquals(1, result1.getStudents().size());
        assertTrue(result1.getStudents().stream().anyMatch(s -> s.getName().equals("Alice")));

        StudentList result2 = list.filterByName("vert");
        assertEquals(1, result2.getStudents().size());
        assertTrue(result2.getStudents().stream().anyMatch(s -> s.getName().equals("Havert")));


        StudentList result3 = list.filterByName("zzz");
        assertEquals(0, result3.getStudents().size());
    }

    @Test
    @DisplayName("เช็คว่ามีไอดีจริงไหมก่อนเพิ่มคะแนน")
    void testgiveScoreToId(){
        StudentList list = new StudentList();
        list.addNewStudent("1", "Alice");
        list.addNewStudent("18", "Havert");

        list.giveScoreToId("1", 50);
        list.giveScoreToId("18", 60);

        Student s = list.findStudentById("1");
        assertEquals(50, s.getScore());

        Student s2 = list.findStudentById("18");
        assertEquals(60, s2.getScore());

    }

}
