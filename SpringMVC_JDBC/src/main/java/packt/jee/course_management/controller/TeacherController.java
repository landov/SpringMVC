package packt.jee.course_management.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import packt.jee.course_management.dto.TeacherDTO;
import packt.jee.course_management.entity.Teacher;
import packt.jee.course_management.service.GenericService;

@Controller
public class TeacherController {
	@Autowired
	GenericService entityService;
	
	@RequestMapping(value = "/teachers")
	public String getTeachers (Model model) {
		//model.addAttribute("teachers", entityService.getEntities(Teacher.class));
		List<Teacher> teachers = entityService.getEntities(Teacher.class);
		List<TeacherDTO> teacherDTOs = new ArrayList<TeacherDTO>();
		for (Teacher teacher : teachers) {
			TeacherDTO teacherDTO = teacherConverter(teacher);
			teacherDTOs.add(teacherDTO);
		}
		model.addAttribute("teachers", teacherDTOs);
	
		return "teachers";
	}
	
	@RequestMapping("/addTeacher") 
	public String addTeacher (@ModelAttribute("teacher") TeacherDTO teacher, Model model) { 
		if (teacher == null) teacher = new TeacherDTO();
		model.addAttribute("teacher", teacher); 
		model.addAttribute("title", "Add Teacher");
		return "addTeacher"; 
	}
	
	@RequestMapping("/doAddTeacher") 
	public String doAddTeacher (@ModelAttribute("teacher") TeacherDTO teacherDTO, Model model) 
	{
		Teacher teacher;
		try { 
			teacher = teacherConverter(teacherDTO);
			if (teacher.getId() == 0) {
				entityService.addEntity(teacher);
			} else {				
				entityService.updateEntity(teacher);
			}
		} catch	(Throwable th) {
			model.addAttribute("error", th.getLocalizedMessage()); 
			return "addTeacher"; 
		} 
		return "redirect:teachers";
	}
	
	@RequestMapping("/teacher/update/{id}")
	public String updateTeacher (@PathVariable int id, Model model) {
		Teacher teacher = entityService.getEntity(Teacher.class, id);
		TeacherDTO teacherDTO = teacherConverter(teacher);
		model.addAttribute("teacher", teacherDTO);
		model.addAttribute("title", "Update Teacher");
		return "addTeacher";

	}
	
	@RequestMapping("/teacher/delete/{id}")
	public String deleteTeacher(@PathVariable("id") int teacherId, Model model) {
		// TODO: Error handling
		entityService.deleteEntity(Teacher.class, teacherId);
		return "redirect:/teachers";
	}
	
	private Teacher teacherConverter(TeacherDTO teacherDTO) {
		Teacher teacher;
		if (teacherDTO.getId() == 0) {
			teacher = new Teacher();
			teacher.setFirstName(teacherDTO.getFirstName());
			teacher.setLastName(teacherDTO.getLastName());
			teacher.setDesignation(teacherDTO.getDesignation());				
			return teacher;
		} else {
			teacher = entityService.getEntity(Teacher.class, teacherDTO.getId());
			teacher.setFirstName(teacherDTO.getFirstName());
			teacher.setLastName(teacherDTO.getLastName());
			teacher.setDesignation(teacherDTO.getDesignation());
			return teacher;
		}
	}
	
	private TeacherDTO teacherConverter(Teacher teacher) {
		TeacherDTO teacherDTO = new TeacherDTO();	
		teacherDTO.setId(teacher.getId());
		teacherDTO.setFirstName(teacher.getFirstName());
		teacherDTO.setLastName(teacher.getLastName());
		teacherDTO.setDesignation(teacher.getDesignation());
		return teacherDTO;
		
		
	}
}
