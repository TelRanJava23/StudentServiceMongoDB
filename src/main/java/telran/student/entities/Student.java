package telran.student.entities;

import java.util.HashMap;
import java.util.Map;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = {"id"})
@Document(collection="Student")
public class Student {
	@Id
	int id;
	String name;
	String password;
	Map<String, Integer> scores;

	public Student(int id, String name, String password) {
		this.id = id;
		this.name = name;
		this.password = password;
		this.scores = new HashMap<>();
	}

	public boolean addScore(String examName, int score) {
		return scores.put(examName, score) != null;
	}

}
