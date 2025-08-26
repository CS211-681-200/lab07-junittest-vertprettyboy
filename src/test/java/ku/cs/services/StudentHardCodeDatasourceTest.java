package ku.cs.services;

import ku.cs.models.Student;
import ku.cs.models.StudentList;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StudentHardCodeDatasourceTest {

    @Test
    @DisplayName("readData() ต้องไม่เป็น null และมี 4 คน")
    void testReadData_NotNull_And_Size4() {
        StudentHardCodeDatasource ds = new StudentHardCodeDatasource();
        StudentList list = ds.readData();

        assertNotNull(list);
        assertEquals(4, list.getStudents().size());
    }

    @Test
    @DisplayName("รายชื่อและรหัสต้องตรงตามที่ hard-code")
    void testReadData_Ids_And_Names() {
        StudentHardCodeDatasource ds = new StudentHardCodeDatasource();
        StudentList list = ds.readData();

        Student s1 = list.findStudentById("6710400001");
        Student s2 = list.findStudentById("6710400002");
        Student s3 = list.findStudentById("6710400003");
        Student s4 = list.findStudentById("6710400004");

        assertAll(
                () -> assertNotNull(s1),
                () -> assertNotNull(s2),
                () -> assertNotNull(s3),
                () -> assertNotNull(s4),
                () -> assertEquals("First",  s1.getName()),
                () -> assertEquals("Second", s2.getName()),
                () -> assertEquals("Third",  s3.getName()),
                () -> assertEquals("Fourth", s4.getName())
        );
    }

    @Test
    @DisplayName("คะแนนเริ่มต้นของทุกคนต้องเป็น 0")
    void testReadData_DefaultScoresAreZero() {
        StudentHardCodeDatasource ds = new StudentHardCodeDatasource();
        StudentList list = ds.readData();

        for (Student s : list.getStudents()) {
            assertEquals(0.0, s.getScore(), 0.000001);
        }
    }

    @Test
    @DisplayName("เรียก readData() สองครั้งควรได้ออบเจ็กต์ใหม่ (แก้ชุดแรกไม่กระทบชุดหลัง)")
    void testReadData_ReturnsIndependentLists() {
        StudentHardCodeDatasource ds = new StudentHardCodeDatasource();

        StudentList first = ds.readData();
        first.getStudents().clear();
        assertEquals(0, first.getStudents().size());

        StudentList second = ds.readData();
        assertEquals(4, second.getStudents().size());
        assertNotNull(second.findStudentById("6710400001"));
    }

    @Test
    @DisplayName("หา id ที่ไม่มีอยู่ควรได้ null")
    void testFindMissingIdFromReadData() {
        StudentHardCodeDatasource ds = new StudentHardCodeDatasource();
        StudentList list = ds.readData();

        assertNull(list.findStudentById("0000000000"));
    }
}
