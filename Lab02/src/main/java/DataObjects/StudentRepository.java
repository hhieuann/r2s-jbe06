package DataObjects;

import Core.Entities.Student;
import Core.InterFaces.IStudentRepository;

public class StudentRepository implements IStudentRepository {
    private static final int MAX = 100;
    private final Student[] data = new Student[MAX];
    private int count = 0;

    @Override
    public boolean add(Student s) {
        if (isFull()) return false;
        data[count++] = s;
        return true;
    }

    @Override
    public Student findById(String id) {
        for (int i = 0; i < count; i++) {
            if (data[i].getId().equalsIgnoreCase(id)) return data[i];
        }
        return null;
    }

    @Override
    public boolean update(Student s) {
        // Vì Student là object tham chiếu, nếu service đã set lại field thì coi như update xong
        // Ở đây vẫn kiểm tra tồn tại để trả về true/false
        return findById(s.getId()) != null;
    }

    @Override
    public Student[] findAll() {
        Student[] result = new Student[count];
        for (int i = 0; i < count; i++) result[i] = data[i];
        return result;
    }

    @Override
    public int size() {
        return count;
    }

    @Override
    public boolean isFull() {
        return count >= MAX;
    }

    @Override
    public boolean existsId(String id) {
        return findById(id) != null;
    }
}
