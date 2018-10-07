package telran.student.dao;

import java.util.List;
import java.util.stream.Stream;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import telran.student.entities.Student;

public interface IStudentRepository extends MongoRepository<Student, Integer> {

	List<Student> findByNameStartingWith(String str);

	@Query("{'name':{$gt:?0}}")
	Stream<Student> findByNameAfterAlefBet(String str);
	
	Stream<Student> findAllBy();
	
	@Query("{?0:{$gt: ?1}}")
	List<Student> findByDynamicField(String exam, int score);

}
