package telran.student.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import telran.student.dao.IStudentRepository;
import telran.student.dto.NotFoundStudentException;
import telran.student.dto.ScoreDto;
import telran.student.dto.StudentDto;
import telran.student.dto.StudentEditDto;
import telran.student.dto.StudentResponseDto;
import telran.student.entities.Student;

@Service
public class StudentServiceImpl implements IStudentService {

	@Autowired
	IStudentRepository studentRepository;

	@Override
	public boolean addStudent(StudentDto studentDto) {
		Student student = studentRepository.findById(studentDto.getId()).orElse(null);
		if (student != null) {
			return false;
		}
		student = new Student(studentDto.getId(), studentDto.getName(), studentDto.getPassword());
		studentRepository.save(student);
		return true;
	}

	@Override
	public StudentResponseDto deleteStudent(int id) {
		Student student = studentRepository.findById(id).orElse(null);
		if (student == null) {
			return null;
		}
		studentRepository.deleteById(id);
		return studentToStudentResponseDto(student);
	}

	@Override
	public StudentDto editStudent(int id, StudentEditDto studentDto) {
		Student student = studentRepository.findById(id).orElse(null);
		if (student == null) {
			return null;
		}
		if (studentDto.getName() != null) {
			student.setName(studentDto.getName());
		}
		if (studentDto.getPassword() != null) {
			student.setPassword(studentDto.getPassword());
		}
		studentRepository.save(student);
		return studentToStudentDto(student);
	}

	@Override
	public StudentResponseDto getStudent(int id) {
		Student student = studentRepository.findById(id).orElse(null);
		if (student == null) {
			return null;
		}
		return studentToStudentResponseDto(student);
	}

	@Override
	public boolean addScore(int id, ScoreDto score) {
		Student student = studentRepository.findById(id).orElse(null);
		if (student == null) {
			throw new NotFoundStudentException();
		}
		boolean res = student.addScore(score.getExamName(), score.getScore());
		studentRepository.save(student);
		return res;
	}

	private StudentDto studentToStudentDto(Student student) {

		return StudentDto.builder().id(student.getId()).name(student.getName()).password(student.getPassword()).build();
	}

	private StudentResponseDto studentToStudentResponseDto(Student student) {
		return StudentResponseDto.builder().id(student.getId()).name(student.getName()).scores(student.getScores())
				.build();
	}

	@Override
	public List<Student> findByNameStartWith(String name) {
		return studentRepository.findByNameStartingWith(name);
	}

	@Override
	public List<Student> findByNameAfterAlefBet(String name) {
		
		return studentRepository.findByNameAfterAlefBet(name)
				.collect(Collectors.toList());
	}

	@Override
	public List<Student> getBestStudents(String exam, int score) {
		return studentRepository.findByDynamicField("scores."+exam, score);
		
//		return studentRepository.findAllBy()
//				.filter(s -> s.getScores().containsKey(exam))
//				.filter(s -> s.getScores().get(exam) > score)
//				.collect(Collectors.toList());
	}

	
}
