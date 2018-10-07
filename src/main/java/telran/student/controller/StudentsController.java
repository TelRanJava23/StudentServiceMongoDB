package telran.student.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import telran.student.api.StudentsURI;
import telran.student.dto.ScoreDto;
import telran.student.dto.StudentDto;
import telran.student.dto.StudentEditDto;
import telran.student.dto.StudentResponseDto;
import telran.student.entities.Student;
import telran.student.service.IStudentService;

@RestController
public class StudentsController {

	@Autowired
	IStudentService studentService;

	@PostMapping(StudentsURI.STUDENT)
	public boolean addStudent(@RequestBody StudentDto student) {
		return studentService.addStudent(student);
	}

	@DeleteMapping(StudentsURI.STUDENT)
	public StudentResponseDto removeStudent(@RequestParam int id) {
		return studentService.deleteStudent(id);
	}

	@PutMapping(StudentsURI.STUDENT + "/{id}")
	public StudentDto editStudent(@PathVariable int id,
			@RequestBody StudentEditDto student) {
		return studentService.editStudent(id, student);
	}

	@GetMapping(StudentsURI.STUDENT + "/{id}")
	public StudentResponseDto getStudent(@PathVariable int id) {
			return studentService.getStudent(id);
	}

	@PutMapping(StudentsURI.TEACHER + "/{id}")
	public boolean addScore(@PathVariable int id,
			@RequestBody ScoreDto score) {
		return studentService.addScore(id, score);
		
	}
	
	@GetMapping(StudentsURI.STUDENTS+"/{name}")
	public List<Student> getByName(@PathVariable String name){
		return studentService.findByNameStartWith(name);
	}
	
	@GetMapping(StudentsURI.STUDENTS+"/alefbet/{str}")
	public List<Student> getStartWithAlefBet(@PathVariable String str){
		return studentService.findByNameAfterAlefBet(str);
	}
	
	@GetMapping(StudentsURI.STUDENTS+"/{exam}/{score}")
	List<Student> getBestStudents(@PathVariable String exam, @PathVariable int score){
		return studentService.getBestStudents(exam, score);
	}
	
}




