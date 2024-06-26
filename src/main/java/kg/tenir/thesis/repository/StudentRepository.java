package kg.tenir.thesis.repository;

import jakarta.persistence.Id;
import kg.tenir.thesis.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Id> {
    Student findById(Long id);

}
