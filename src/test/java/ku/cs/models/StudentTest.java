package ku.cs.models;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class StudentTest {

    @Test
    void testCalculateGrade(){
        Student s = new Student("6xxxxxxxxx", "StudentTest");
        s.addScore(85);
        assertEquals("A", s.grade());
    }

    @Test
    @DisplayName("สร้าง Student แบบไม่ใส่คะแนน -> score ต้องเป็น 0")
    void testConstructorWithoutScore() {
        Student s = new Student("1", "Test");
        assertEquals(0, s.getScore());
    }

    @Test
    @DisplayName("สร้าง Student แบบกำหนดคะแนนตั้งแต่แรก")
    void testConstructorWithScore() {
        Student s = new Student("2", "Test", 75);
        assertEquals(75, s.getScore());
    }

    @Test
    @DisplayName("ทดสอบการเพิ่มคะแนนปกติ")
    void testAddScoreNormal() {
        Student s = new Student("3", "Test");
        s.addScore(10);
        assertEquals(10, s.getScore());
    }

    @Test
    @DisplayName("ทดสอบการเพิ่มคะแนนเป็น 0")
    void testAddScoreZero() {
        Student s = new Student("4", "Test");
        s.addScore(0);
        assertEquals(0, s.getScore());
    }

    @Test
    @DisplayName("ทดสอบการเพิ่มคะแนนติดลบ (ไม่ควรเปลี่ยน)")
    void testAddScoreNegative() {
        Student s = new Student("5", "Test");
        s.addScore(-10);
        assertEquals(0, s.getScore());
    }

    @Test
    @DisplayName("ทดสอบการเปลี่ยนชื่อปกติ")
    void testChangeNameNormal() {
        Student s = new Student("6", "OldName");
        s.changeName("NewName");
        assertEquals("NewName", s.getName());
    }

    @Test
    @DisplayName("ทดสอบการเปลี่ยนชื่อว่าง (ไม่ควรเปลี่ยน)")
    void testChangeNameEmpty() {
        Student s = new Student("7", "OldName");
        s.changeName("   ");
        assertEquals("OldName", s.getName());
    }

    @Test
    @DisplayName("ทดสอบการเปลี่ยนชื่อมี space (trim แล้ว)")
    void testChangeNameTrim() {
        Student s = new Student("8", "OldName");
        s.changeName("   Alice   ");
        assertEquals("Alice", s.getName());
    }

    @Test
    @DisplayName("ทดสอบการคำนวณเกรดทุกช่วง")
    void testGradeSystem() {
        assertEquals("A", new Student("9","Test",85).grade());
        assertEquals("B", new Student("9","Test",75).grade());
        assertEquals("C", new Student("9","Test",65).grade());
        assertEquals("D", new Student("9","Test",55).grade());
        assertEquals("F", new Student("9","Test",40).grade());
    }

    @Test
    @DisplayName("ทดสอบ isId()")
    void testIsId() {
        Student s = new Student("10", "Test");
        assertTrue(s.isId("10"));
        assertFalse(s.isId("99"));
    }

    @Test
    @DisplayName("ทดสอบ isNameContains() แบบ case-insensitive")
    void testIsNameContains() {
        Student s = new Student("11", "Alice Wonderland");
        assertTrue(s.isNameContains("alice"));
        assertTrue(s.isNameContains("WONDER"));
        assertFalse(s.isNameContains("Bob"));
    }

    @Test
    @DisplayName("ทดสอบ toString() มีข้อมูลครบ")
    void testToString() {
        Student s = new Student("12", "Test", 50);
        String text = s.toString();
        assertTrue(text.contains("id"));
        assertTrue(text.contains("name"));
        assertTrue(text.contains("score"));
    }
}
