package course.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
	
	@ManyToMany(cascade = { CascadeType.ALL }, fetch = FetchType.EAGER, mappedBy ="students")
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
