import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AssertionsTest {
    
    @Test
    public void testAssertions() {
        // Assert equals - checks if two values are equal
        assertEquals(5, 2 + 3, "2 + 3 should equal 5");
        
        // Assert true - checks if condition is true
        assertTrue(5 > 3, "5 should be greater than 3");
        
        // Assert false - checks if condition is false
        assertFalse(5 < 3, "5 should not be less than 3");
        
        // Assert null - checks if object is null
        assertNull(null, "null should be null");
        
        // Assert not null - checks if object is not null
        assertNotNull(new Object(), "new Object() should not be null");
        
        // Additional useful assertions
        assertEquals("Hello", "Hello", "Strings should be equal");
        assertNotEquals(10, 5, "10 should not equal 5");
        
        // Array assertions
        int[] expected = {1, 2, 3};
        int[] actual = {1, 2, 3};
        assertArrayEquals(expected, actual, "Arrays should be equal");
        
        // Exception assertions
        assertThrows(ArithmeticException.class, () -> {
            int result = 10 / 0;
        }, "Division by zero should throw ArithmeticException");
    }
    
    @Test
    public void testStringAssertions() {
        String str = "JUnit Testing";
        
        assertTrue(str.contains("JUnit"), "String should contain 'JUnit'");
        assertTrue(str.startsWith("JUnit"), "String should start with 'JUnit'");
        assertTrue(str.endsWith("Testing"), "String should end with 'Testing'");
        assertEquals(13, str.length(), "String length should be 13");
    }
    
    @Test
    public void testNumericAssertions() {
        double pi = 3.14159;
        
        // Delta for floating point comparison
        assertEquals(3.14, pi, 0.01, "Pi should be approximately 3.14");
        
        assertTrue(pi > 3.0, "Pi should be greater than 3.0");
        assertTrue(pi < 4.0, "Pi should be less than 4.0");
    }
}
