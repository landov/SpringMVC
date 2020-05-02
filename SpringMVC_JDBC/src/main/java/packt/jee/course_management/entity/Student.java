package packt.jee.course_management.entity;

import java.util.Date;
import java.util.List;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import static javax.persistence.CascadeType.MERGE;
import static javax.persistence.CascadeType.REFRESH;
import javax.persistence.JoinColumn;


@Entity
@Table(name = "person")
public class Student extends Person{

	private static final long serialVersionUID = 1L;
	@Temporal(TemporalType.DATE)
	@Column(name="enrolled_since")
	private Date enrolledsince;
	
	@ManyToMany(cascade = { MERGE, REFRESH })
	@JoinTable(
			name="course_student", 
			joinColumns = @JoinColumn(name = "student_id", referencedColumnName = "id"), 
			inverseJoinColumns = @JoinColumn(name = "course_id", referencedColumnName = "id")
	)
	private List<Course> courses;
	
	
	public Date getEnrolledsince() {
		return enrolledsince;
	}
	public void setEnrolledsince(Date enrolledsince) {
		this.enrolledsince = enrolledsince;
	}
	
	public void setCourses(List<Course> courses) {
		this.courses = courses;
	}
	public List<Course> getCourses() {
		return courses;
	}

	
	
	
}
