import java.util.ArrayList;
import java.util.List;

public class StudentService {
    private List<Student> students;
    
    public StudentService() {
        this.students = new ArrayList<>();
    }
    
    public boolean addStudent(Student student) {
        if (student == null || findStudentById(student.getId()) != null) {
            return false;
        }
        return students.add(student);
    }
    
    public boolean removeStudent(int id) {
        Student student = findStudentById(id);
        if (student != null) {
            return students.remove(student);
        }
        return false;
    }
    
    public Student findStudentById(int id) {
        for (Student student : students) {
            if (student.getId() == id) {
                return student;
            }
        }
        return null;
    }
    
    public Student findStudentByName(String name) {
        if (name == null) return null;
        
        for (Student student : students) {
            if (name.equals(student.getName())) {
                return student;
            }
        }
        return null;
    }
    
    public double calculateAverageGrade() {
        if (students.isEmpty()) {
            return 0.0;
        }
        
        double sum = 0.0;
        for (Student student : students) {
            sum += student.getGrade();
        }
        return sum / students.size();
    }
    
    public int getStudentCount() {
        return students.size();
    }
    
    public List<Student> getAllStudents() {
        return new ArrayList<>(students);
    }
    
    public void clearAllStudents() {
        students.clear();
    }
}
