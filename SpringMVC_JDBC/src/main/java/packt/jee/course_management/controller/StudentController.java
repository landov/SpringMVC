package packt.jee.course_management.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import packt.jee.course_management.dto.CourseDTO;
import packt.jee.course_management.dto.StudentDTO;
import packt.jee.course_management.entity.Course;
import packt.jee.course_management.entity.Student;
import packt.jee.course_management.service.GenericService;

@Controller
public class StudentController {
	@Autowired
	GenericService entityService;
	@Autowired
	CourseController courseController;
	
	@RequestMapping(value = "/students")
	public String getStudents (Model model) {
		//model.addAttribute("students", entityService.getEntities(Student.class));
		List<Student> students = entityService.getEntities(Student.class);
		List<StudentDTO> studentDTOs = new ArrayList<StudentDTO>();
		for (Student student : students) {
			StudentDTO studentDTO = studentConverter(student);
			studentDTOs.add(studentDTO);
		}
		model.addAttribute("students", studentDTOs);
	
		return "students";
	}
	
	@RequestMapping("/addStudent") 
	public String addStudent (@ModelAttribute("student") StudentDTO student, Model model) { 
		if (student == null) student = new StudentDTO();
		model.addAttribute("student", student); 
		model.addAttribute("title", "Add Student");
		return "addStudent"; 
	}
	
	@RequestMapping("/doAddStudent") 
	public String doAddStudent (@ModelAttribute("student") StudentDTO studentDTO, Model model) 
	{
		Student student;
		try { 
			student = studentConverter(studentDTO);
			student.setEnrolledsince(new Date());
			//System.out.println(student.getEnrolledsince().toString());
			if (student.getId() == 0) {
				entityService.addEntity(student);
			} else {				
				entityService.updateEntity(student);
			}
		} catch	(Throwable th) {
			model.addAttribute("error", th.getLocalizedMessage()); 
			return "addStudent"; 
		} 
		return "redirect:students";
	}
	
	@RequestMapping("/student/update/{id}")
	public String updateStudent (@PathVariable int id, Model model) {
		Student student = entityService.getEntity(Student.class, id);
		StudentDTO studentDTO = studentConverter(student);
		model.addAttribute("student", studentDTO);
		model.addAttribute("title", "Update Student");
		return "addStudent";

	}
	
	@RequestMapping("/student/enroll/{id}")
	public String enrollStudent (@PathVariable int id, Model model) {
		Student student = entityService.getEntity(Student.class, id);
		StudentDTO studentDTO = studentConverter(student);
		model.addAttribute("student", studentDTO);
		List<CourseDTO> allCourses = courseController.courseListConverter(entityService.getEntities(Course.class)); 
		model.addAttribute("allCourses", allCourses);
		return "enrollCourse";
	}
	
	@RequestMapping("/student/doEnroll")
	public String doEnroll(@ModelAttribute("student") StudentDTO studentDTO) {
		//System.out.println(courseDTOs.toString());
		/*for (CourseDTO course : student.getCourses()) {
			System.out.println(course.getName()+course.isChecked());
		}*/
		System.out.println(studentDTO.toString());
		System.out.println("doEnroll");
		return "redirect:/students";
	}
	
	@RequestMapping("/student/delete/{id}")
	public String deleteStudent(@PathVariable("id") int studentId, Model model) {
		// TODO: Error handling
		entityService.deleteEntity(Student.class, studentId);
		return "redirect:/students";
	}
	
	private Student studentConverter(StudentDTO studentDTO) {
		Student student;
		if (studentDTO.getId() == 0) {
			student = new Student();			
		} else {
			student = entityService.getEntity(Student.class, studentDTO.getId());			
		}
		student.setFirstName(studentDTO.getFirstName());
		student.setLastName(studentDTO.getLastName());
		student.setEnrolledsince(studentDTO.getEnrolledsince());
		return student;
	}
	
	private StudentDTO studentConverter(Student student) {
		StudentDTO studentDTO = new StudentDTO();	
		studentDTO.setId(student.getId());
		studentDTO.setFirstName(student.getFirstName());
		studentDTO.setLastName(student.getLastName());
		studentDTO.setEnrolledsince(student.getEnrolledsince());
		List<Course> courses = student.getCourses();
		studentDTO.setCourses(courseController.courseListConverter(courses));
	/*	for(Course course : courses){
			CourseDTO courseDTO = courseController.courseConverter(course);
			if (student.getCourses().contains(course)){
				courseDTO.setChecked(true);
			}
			studentDTO.getCourses().add(courseDTO);
			System.out.println(courseDTO.getName() + " stdConv");
		}*/
		System.out.println(studentDTO.getCourses().toString());
		return studentDTO;
	}
}
