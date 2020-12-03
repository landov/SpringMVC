package course.service;

import org.springframework.core.convert.converter.Converter;
import course.dto.CourseDTO;

public class CourseConverter implements Converter<String, CourseDTO> {

	@Override
	public CourseDTO convert(String source) {
		CourseDTO courseDTO = new CourseDTO();
		courseDTO.setId(Integer.parseInt(source));
		return courseDTO;
	}

}


	
		


