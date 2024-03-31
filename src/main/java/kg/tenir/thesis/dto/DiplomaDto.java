package kg.tenir.thesis.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class DiplomaDto {
    private long id;
    private String title;
    private long student_id;
    private long instructor_id;
    private Date year_of_publication;
    private String filePath;
    private String content;
}
