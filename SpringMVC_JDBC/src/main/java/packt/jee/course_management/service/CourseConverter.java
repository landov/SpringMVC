package packt.jee.course_management.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;
import org.springframework.stereotype.Component;

import packt.jee.course_management.controller.CourseController;
import packt.jee.course_management.dto.CourseDTO;
import packt.jee.course_management.entity.Course;

public class CourseConverter implements Converter<String, CourseDTO> {

	@Override
	public CourseDTO convert(String source) {
		CourseDTO courseDTO = new CourseDTO();
		courseDTO.setId(Integer.parseInt(source));
		return courseDTO;
	}

}


	
		


