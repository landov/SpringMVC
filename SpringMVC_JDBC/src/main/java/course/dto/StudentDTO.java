package course.dto;

import java.util.Date;
import java.util.List;

public class StudentDTO {
	
	private int id;
	private String firstName;
	private String lastName;
	private Date enrolledsince;
	private List<CourseDTO> courses;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public Date getEnrolledsince() {
		return enrolledsince;
	}
	public void setEnrolledsince(Date enrolledsince) {
		this.enrolledsince = enrolledsince;
	}
	
	
	public List<CourseDTO> getCourses() {
		return courses;
	}
	public void setCourses(List<CourseDTO> courses) {
		this.courses = courses;
	}
	
	

}
