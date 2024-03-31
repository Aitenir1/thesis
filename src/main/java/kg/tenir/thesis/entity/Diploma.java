package kg.tenir.thesis.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@Entity
@Table(name = "diploma")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Diploma {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String title;
    @OneToOne
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne
    @JoinColumn(name = "instructor_id")
    private Instructor instructor;

    private Date year_of_publication;

    private String filePath;

    @Column(length=100000)
    private String content;
}
