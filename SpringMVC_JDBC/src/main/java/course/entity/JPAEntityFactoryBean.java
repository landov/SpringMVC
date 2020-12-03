package course.entity;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.springframework.stereotype.Component;

@Component
public class JPAEntityFactoryBean {
	EntityManagerFactory entityManagerFactory;
	
	@PostConstruct
	public void init() {
		entityManagerFactory = 
				Persistence.createEntityManagerFactory("SpringMVC_JDBC");
	}
	
	public EntityManagerFactory getEntityManagerFactory() {
		return entityManagerFactory;
	}
}
