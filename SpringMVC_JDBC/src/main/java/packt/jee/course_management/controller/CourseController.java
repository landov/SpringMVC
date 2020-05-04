package packt.jee.course_management.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import packt.jee.course_management.dto.CourseDTO;
import packt.jee.course_management.dto.StudentDTO;
import packt.jee.course_management.dto.TeacherDTO;
import packt.jee.course_management.entity.Course;
import packt.jee.course_management.entity.Student;
import packt.jee.course_management.entity.Teacher;
import packt.jee.course_management.service.GenericService;

@Controller
public class CourseController {

	@Autowired
	GenericService entityService;
	@Autowired
	StudentController studentController;
	
	@RequestMapping(value = "/courses")
	public String getCourses (Model model) {
		List<Course> courses = entityService.getEntities(Course.class);
		List<CourseDTO> courseDTOs = new ArrayList<CourseDTO>();
		for (Course course : courses) {
			CourseDTO courseDTO = courseConverter(course);
			courseDTOs.add(courseDTO);
		}
		model.addAttribute("courses", courseDTOs);
		return "courses";
	}
	
	@RequestMapping("/addCourse") 
	public String addCourse (@ModelAttribute("course") CourseDTO course, Model model) { 
		if (course == null) course = new CourseDTO();
		List<Teacher> teachers = entityService.getEntities(Teacher.class);
		model.addAttribute("course", course); 
		model.addAttribute("teachers", teachers);
		model.addAttribute("title", "Add Course");
		return "addCourse"; 
	}
	
	@RequestMapping("/doAddCourse") 
	public String doAddCourse (@ModelAttribute("course") CourseDTO courseDTO, Model model) 
	{
		Course course;
		try { 
			course = courseConverter(courseDTO, true);
			if (course.getId() == 0) {
				entityService.addEntity(course);
			} else {				
				entityService.updateEntity(course);
			}
		} catch	(Throwable th) {
			model.addAttribute("error", th.getLocalizedMessage()); 
			return "addCourse"; 
		} 
		return "redirect:courses";
	}
	
	@RequestMapping("/course/update/{id}")
	public String updateCourse (@PathVariable int id, Model model) {
		Course course = entityService.getEntity(Course.class, id);
		CourseDTO courseDTO = courseConverter(course);
		List<Teacher> teachers = entityService.getEntities(Teacher.class);
		model.addAttribute("teachers", teachers);
		model.addAttribute("course", courseDTO);
		model.addAttribute("title", "Update Course "+courseDTO.getId());
		return "addCourse";

	}
	
	@RequestMapping("/course/details/{id}")
	public String courseDetails (@PathVariable int id, Model model) {
		Course course = entityService.getEntity(Course.class, id);
		CourseDTO courseDTO = courseConverter(course);
		model.addAttribute("course", courseDTO);
		List<Student> students = course.getStudents();
		List<StudentDTO> studentDTOs = new ArrayList<StudentDTO>();
		for (Student student : students) {
			StudentDTO studentDTO = studentController.studentConverter(student, false);
			studentDTOs.add(studentDTO);
		}
		model.addAttribute("students", studentDTOs);
		return "courseDetails";

	}
	
	@RequestMapping("/course/delete/{id}")
	public String deleteCourse(@PathVariable("id") int courseId, Model model) {
		// TODO: Error handling
		entityService.deleteEntity(Course.class, courseId);
		return "redirect:/courses";
	}
	
	public Course courseConverter(CourseDTO courseDTO, boolean update) {
		Course course;
		if (courseDTO.getId() == 0) {
			course = new Course();
			update = true;
			
		} else {
			course = entityService.getEntity(Course.class, courseDTO.getId());			
		}
		if (update) {
			course.setName(courseDTO.getName());
			course.setCredits(courseDTO.getCredits());				
			Teacher teacher;
			teacher = entityService.getEntity(Teacher.class, courseDTO.getTeacherId());
			course.setTeacher(teacher);
		}
		return course;
	}
		
	public CourseDTO courseConverter(Course course) {
		
		CourseDTO courseDTO = new CourseDTO();	
		courseDTO.setId(course.getId());
		courseDTO.setName(course.getName());
		courseDTO.setCredits(course.getCredits());
		Teacher teacher = course.getTeacher();
		if (teacher != null) {
			courseDTO.setTeacherId(teacher.getId());
			courseDTO.setTeacherName(teacher.getFirstName()+" "+teacher.getLastName());
		} else {
			courseDTO.setTeacherName("no teacher assigned");
		}
		return courseDTO;	
	}
	
	public List<CourseDTO> courseListConverter(List<Course> courses){
		List<CourseDTO> courseDTOs = new ArrayList<CourseDTO>();
		for(Course course : courses) {
			CourseDTO courseDTO = new CourseDTO();
			courseDTOs.add(courseConverter(course));
		}
		return courseDTOs;
	}
	
	public List<Course> courseListConverter(List<CourseDTO> courseDTOs, boolean update){
		List<Course> courses = new ArrayList<Course>();
		for (CourseDTO courseDTO : courseDTOs) {
			Course course = courseConverter(courseDTO, false);
			courses.add(course);
		}
		return courses;
	}
}
