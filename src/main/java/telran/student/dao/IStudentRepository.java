package telran.student.dao;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import telran.student.entities.Student;

public interface IStudentRepository extends MongoRepository<Student, Integer> {
	List<Student> findByNameRegex(String regex);

}
