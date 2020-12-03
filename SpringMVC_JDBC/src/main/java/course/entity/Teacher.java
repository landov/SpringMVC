package course.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "person")
public class Teacher extends Person{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6643581655746904957L;
	@NotNull
	@Column(name="designation")
	private String designation;
	
	
	
	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public boolean isValidTeacher() {
		return getFirstName() != null; }
}
