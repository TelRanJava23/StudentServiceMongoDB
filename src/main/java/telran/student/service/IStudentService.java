package telran.student.service;

import java.util.List;

import telran.student.dto.ScoreDto;
import telran.student.dto.StudentDto;
import telran.student.dto.StudentEditDto;
import telran.student.dto.StudentResponseDto;
import telran.student.entities.Student;

public interface IStudentService {
	
	boolean addStudent(StudentDto student);
	
	StudentResponseDto deleteStudent(int id);
	
	StudentDto editStudent(int id, StudentEditDto student);
	
	StudentResponseDto getStudent(int id);
	
	boolean addScore(int id, ScoreDto score);
	
	List<Student> findByNameStartWith(String name);

}
