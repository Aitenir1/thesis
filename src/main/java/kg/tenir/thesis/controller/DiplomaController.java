package kg.tenir.thesis.controller;

import kg.tenir.thesis.dto.DiplomaDto;
import kg.tenir.thesis.entity.Diploma;
import kg.tenir.thesis.service.DiplomaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;

@RestController
public class DiplomaController {
    private final DiplomaService diplomaService;

    public DiplomaController(DiplomaService diplomaService) {
        this.diplomaService = diplomaService;
    }

    @GetMapping("/")
    public List<DiplomaDto> list() {
        return diplomaService.getDiplomas();
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<Diploma> getDetail(@PathVariable long id) {
        Diploma diploma = diplomaService.findById(id);
        return ResponseEntity.ok(diploma);
    }

    @PostMapping("/diploma")
    public String createDiploma(@RequestParam("title") String title,
                                @RequestParam("student") Long student,
                                @RequestParam("year_of_publication") Date year_of_publication,
                                @RequestParam("file") MultipartFile file)  {

        System.out.println("Hello, world");
        diplomaService.addDiploma(
                title,
                student,
                year_of_publication,
                file
        );

        return "Created new diploma";
    }

    @PutMapping("edit/{id}")
    public ResponseEntity<Diploma> edit(@PathVariable long id,
                                        @RequestParam("title") String title,
                                        @RequestParam("student") Long student,
                                        @RequestParam("year_of_publication") Date year_of_publication,
                                        @RequestParam("file") MultipartFile file) {
        Diploma diploma = diplomaService.findById(id);

        return ResponseEntity.ok(diploma);
    }

}
