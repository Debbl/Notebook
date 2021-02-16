package run.aiwan.service;

import org.springframework.stereotype.Service;
import run.aiwan.domain.Student;

import java.util.List;

public interface StudentService {
    List<Student> queryStudentsService();
}
