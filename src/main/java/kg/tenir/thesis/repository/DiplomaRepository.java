package kg.tenir.thesis.repository;

import jakarta.persistence.Id;
import kg.tenir.thesis.dto.DiplomaDto;
import kg.tenir.thesis.entity.Diploma;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DiplomaRepository extends JpaRepository<Diploma, Id> {
    Optional<Diploma> findById(long id);

    @Query("SELECT new kg.tenir.thesis.dto.DiplomaDto(d.id, d.title, s.id, i.id, d.year_of_publication, d.filePath, d.content) FROM Diploma d JOIN d.student s JOIN d.instructor i")
    List<DiplomaDto> getDiplomaList();
}
