import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;

public class StudentServiceTest {
    
    private StudentService studentService;
    private Student testStudent1;
    private Student testStudent2;
    private Student testStudent3;
    
    @Before
    public void setUp() {
        // Setup method - runs before each test
        System.out.println("Setting up test fixtures...");
        
        // Initialize the service
        studentService = new StudentService();
        
        // Create test students
        testStudent1 = new Student(1, "John Doe", 85.5);
        testStudent2 = new Student(2, "Jane Smith", 92.0);
        testStudent3 = new Student(3, "Bob Johnson", 78.5);
        
        System.out.println("Test fixtures ready.");
    }
    
    @After
    public void tearDown() {
        // Teardown method - runs after each test
        System.out.println("Cleaning up test fixtures...");
        
        // Clear all students
        studentService.clearAllStudents();
        
        // Reset references
        studentService = null;
        testStudent1 = null;
        testStudent2 = null;
        testStudent3 = null;
        
        System.out.println("Test cleanup completed.");
    }
    
    @Test
    public void testAddStudent_ValidStudent_ReturnsTrue() {
        // Arrange
        int initialCount = studentService.getStudentCount();
        
        // Act
        boolean result = studentService.addStudent(testStudent1);
        
        // Assert
        assertTrue("Adding valid student should return true", result);
        assertEquals("Student count should increase by 1", 
                    initialCount + 1, studentService.getStudentCount());
        assertEquals("Added student should be findable", 
                    testStudent1, studentService.findStudentById(1));
    }
    
    @Test
    public void testAddStudent_NullStudent_ReturnsFalse() {
        // Arrange
        int initialCount = studentService.getStudentCount();
        
        // Act
        boolean result = studentService.addStudent(null);
        
        // Assert
        assertFalse("Adding null student should return false", result);
        assertEquals("Student count should remain unchanged", 
                    initialCount, studentService.getStudentCount());
    }
    
    @Test
    public void testAddStudent_DuplicateId_ReturnsFalse() {
        // Arrange
        studentService.addStudent(testStudent1);
        Student duplicateStudent = new Student(1, "Different Name", 90.0);
        int countAfterFirst = studentService.getStudentCount();
        
        // Act
        boolean result = studentService.addStudent(duplicateStudent);
        
        // Assert
        assertFalse("Adding student with duplicate ID should return false", result);
        assertEquals("Student count should not increase", 
                    countAfterFirst, studentService.getStudentCount());
    }
    
    @Test
    public void testRemoveStudent_ExistingStudent_ReturnsTrue() {
        // Arrange
        studentService.addStudent(testStudent1);
        studentService.addStudent(testStudent2);
        int initialCount = studentService.getStudentCount();
        
        // Act
        boolean result = studentService.removeStudent(1);
        
        // Assert
        assertTrue("Removing existing student should return true", result);
        assertEquals("Student count should decrease by 1", 
                    initialCount - 1, studentService.getStudentCount());
        assertNull("Removed student should not be findable", 
                  studentService.findStudentById(1));
    }
    
    @Test
    public void testRemoveStudent_NonExistentStudent_ReturnsFalse() {
        // Arrange
        studentService.addStudent(testStudent1);
        int initialCount = studentService.getStudentCount();
        
        // Act
        boolean result = studentService.removeStudent(999);
        
        // Assert
        assertFalse("Removing non-existent student should return false", result);
        assertEquals("Student count should remain unchanged", 
                    initialCount, studentService.getStudentCount());
    }
    
    @Test
    public void testFindStudentById_ExistingStudent_ReturnsStudent() {
        // Arrange
        studentService.addStudent(testStudent1);
        studentService.addStudent(testStudent2);
        
        // Act
        Student found = studentService.findStudentById(2);
        
        // Assert
        assertNotNull("Should find existing student", found);
        assertEquals("Should return correct student", testStudent2, found);
        assertEquals("Student should have correct name", "Jane Smith", found.getName());
    }
    
    @Test
    public void testFindStudentById_NonExistentStudent_ReturnsNull() {
        // Arrange
        studentService.addStudent(testStudent1);
        
        // Act
        Student found = studentService.findStudentById(999);
        
        // Assert
        assertNull("Should return null for non-existent student", found);
    }
    
    @Test
    public void testFindStudentByName_ExistingStudent_ReturnsStudent() {
        // Arrange
        studentService.addStudent(testStudent1);
        studentService.addStudent(testStudent2);
        
        // Act
        Student found = studentService.findStudentByName("John Doe");
        
        // Assert
        assertNotNull("Should find existing student by name", found);
        assertEquals("Should return correct student", testStudent1, found);
        assertEquals("Student should have correct ID", 1, found.getId());
    }
    
    @Test
    public void testFindStudentByName_NullName_ReturnsNull() {
        // Arrange
        studentService.addStudent(testStudent1);
        
        // Act
        Student found = studentService.findStudentByName(null);
        
        // Assert
        assertNull("Should return null for null name", found);
    }
    
    @Test
    public void testCalculateAverageGrade_MultipleStudents_ReturnsCorrectAverage() {
        // Arrange
        studentService.addStudent(testStudent1); // 85.5
        studentService.addStudent(testStudent2); // 92.0
        studentService.addStudent(testStudent3); // 78.5
        double expectedAverage = (85.5 + 92.0 + 78.5) / 3;
        
        // Act
        double actualAverage = studentService.calculateAverageGrade();
        
        // Assert
        assertEquals("Should calculate correct average", 
                    expectedAverage, actualAverage, 0.01);
    }
    
    @Test
    public void testCalculateAverageGrade_NoStudents_ReturnsZero() {
        // Arrange
        // No students added (empty service)
        
        // Act
        double average = studentService.calculateAverageGrade();
        
        // Assert
        assertEquals("Average of empty list should be 0.0", 0.0, average, 0.01);
    }
    
    @Test
    public void testGetStudentCount_EmptyService_ReturnsZero() {
        // Arrange
        // Service is empty from setUp
        
        // Act
        int count = studentService.getStudentCount();
        
        // Assert
        assertEquals("Empty service should have 0 students", 0, count);
    }
    
    @Test
    public void testGetStudentCount_AfterAddingStudents_ReturnsCorrectCount() {
        // Arrange
        studentService.addStudent(testStudent1);
        studentService.addStudent(testStudent2);
        
        // Act
        int count = studentService.getStudentCount();
        
        // Assert
        assertEquals("Should return correct student count", 2, count);
    }
}
