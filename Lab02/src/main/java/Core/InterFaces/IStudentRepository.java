package Core.InterFaces;

import Core.Entities.Student;

public interface IStudentRepository {
    boolean add(Student s);
    Student findById(String id);
    boolean update(Student s);
    Student[] findAll();
    int size();
    boolean isFull();
    boolean existsId(String id);
}
