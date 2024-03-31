package kg.tenir.thesis.service.impl;

import io.minio.BucketExistsArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import kg.tenir.thesis.dto.DiplomaDto;
import kg.tenir.thesis.entity.Diploma;
import kg.tenir.thesis.entity.Student;
import kg.tenir.thesis.exception.DiplomaCreateException;
import kg.tenir.thesis.repository.DiplomaRepository;
import kg.tenir.thesis.repository.StudentRepository;
import kg.tenir.thesis.service.DiplomaService;
import kg.tenir.thesis.service.props.MinioProperties;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DiplomaServiceImpl implements DiplomaService {
    private final DiplomaRepository diplomaRepository;
    private final StudentRepository studentRepository;
//    @Autowired
    private final MinioClient minioClient;
    private final MinioProperties minioProperties;
    private final String DIRECTORY_PATH = "/Users/aitenirzhamakeev/Desktop/theses/";

    @Override
    public List<DiplomaDto> getDiplomas() {
        return diplomaRepository.getDiplomaList();
    }

    @Override
    public Diploma findById(long id) {
        Optional<Diploma> diploma = diplomaRepository.findById(id);

        return diploma.orElse(null);
    }

    @Override
    public void addDiploma(String title, Long student_id, Date year_of_publication, MultipartFile file) {
        Student student = studentRepository.findById(student_id);

        Diploma diploma = new Diploma();

        String fileName = uploadFile(file, student, year_of_publication);

        diploma.setTitle(title);
        diploma.setStudent(student);
        diploma.setYear_of_publication(year_of_publication);
        diploma.setFilePath(fileName);

        diplomaRepository.save(diploma);
    }

    public String uploadFile(MultipartFile file, Student student, Date yearOfPublication) {
        try {
            createBucket();
        } catch (Exception e) {
            throw new DiplomaCreateException("Diploma file upload failed: " + e.getMessage());
        }

        if (file.isEmpty() || file.getOriginalFilename() == null) {
            throw new DiplomaCreateException("Diploma file must have name!");
        }

        String fileName = file.getOriginalFilename();

        String newFileName = generateFileName(fileName, student, yearOfPublication);
        InputStream inputStream;
        try {
            inputStream = file.getInputStream();
        } catch (Exception e) {
            throw new DiplomaCreateException("Diploma file upload failed" + e.getMessage());
        }
        saveFile(inputStream, fileName);
        return newFileName;
    }

    @SneakyThrows
    private void saveFile(InputStream inputStream, String fileName) {
        minioClient.putObject(PutObjectArgs.builder().stream(inputStream, inputStream.available(), -1).object(fileName).build());
    }

    private String generateFileName(String originalFileName, Student student, Date year_of_publication) {
        String extension = originalFileName.substring(originalFileName.lastIndexOf("."));

        SimpleDateFormat df = new SimpleDateFormat("yyyy");
        String year = df.format(year_of_publication);

        String newFileName = "%s_%d_%s_%s".formatted(
                year,
                student.getId(),
                student.getFirstName(),
                student.getLastName()
        );

        System.out.println(newFileName);
        return newFileName + getExtension(originalFileName);
    }

    private String getExtension(final String fileName) {
        return fileName.substring(fileName.lastIndexOf(".") + 1);
    }
    @SneakyThrows
    private void createBucket() {
        System.out.println(minioProperties.getBucket());
        boolean found = minioClient.bucketExists(BucketExistsArgs.builder().bucket(minioProperties.getBucket()).build());
        if (!found) {
            minioClient.makeBucket(MakeBucketArgs.builder().bucket(minioProperties.getBucket()).build());
        }
    }
}
