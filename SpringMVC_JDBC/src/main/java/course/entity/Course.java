package course.entity;


import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;


import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import course.exception.EnrolmentFullException;


@Entity
@NamedQuery(name="Course.findAll", query="SELECT c FROM Course c")
public class Course implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	@NotNull
	@Column(name="name")
	private String name;
	@Min(1)
	@Column(name="credits")
	private int credits;
	@ManyToOne(cascade = { CascadeType.MERGE, CascadeType.REFRESH })
	private Teacher teacher;
	
	@ManyToMany(cascade = { CascadeType.ALL }, fetch = FetchType.EAGER)
	@JoinTable(
			name="course_student", 
			joinColumns = @JoinColumn(name = "course_id", referencedColumnName = "id"), 
			inverseJoinColumns = @JoinColumn(name = "student_id", referencedColumnName = "id")
	)
	private List<Student> students;
	
	private int maxStudents;
	
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Course other = (Course) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getCredits() {
		return credits;
	}
	public void setCredits(int credits) {
		this.credits = credits;		
	}
	
	
	
	public int getMaxStudents() {
		return maxStudents;
	}
	public void setMaxStudents(int maxStudents) {
		this.maxStudents = maxStudents;
	}
	public boolean isValidCourse() {
		return name != null && credits != 0 && name.trim().length() > 0; }
	
	public Teacher getTeacher() {
		return teacher;
	}
	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public List<Student> getStudents() {
		return students;
	}
	public void setStudents(List<Student> students) {
		this.students = students;
	}
	
	public void addStudent (Student student)
			throws EnrolmentFullException, SQLException {
			//get current enrolement first
			int currentEnrolment = getSutdentsInCourse();
			if (currentEnrolment >= getMaxStudents())
			throw new EnrolmentFullException("Course if full. Enrolment closed");
	}
	
	public int getSutdentsInCourse() {
		return students.size();
	}
	
	
}
