package packt.jee.course_management.service;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import packt.jee.course_management.entity.JPAEntityFactoryBean;


@Component
public class GenericService {
	
	@Autowired
	JPAEntityFactoryBean entityFactoryBean;
	EntityManagerFactory factory;
	
	//private EntityManagerFactory factory;
	
	@PostConstruct
	private void init() {
		factory = entityFactoryBean.getEntityManagerFactory();
	}
	
	/*public GenericService(EntityManagerFactoryBean factoryBean) {
			this.factory = factoryBean.getEntityManagerFactory(); 
	}*/
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public <T> List<T>	getEntities(Class<T> entityClass) {
		EntityManager em = factory.createEntityManager(); 
		CriteriaBuilder cb =	em.getCriteriaBuilder(); 
		CriteriaQuery cq = 	cb.createQuery(); 
		cq.select(cq.from(entityClass));
		//TypedQuery<T> tq = em.createQuery(cq);
		List<T> entities = em.createQuery(cq).getResultList();
		em.close();
		return entities;
	}
	
	public <T> void addEntity(T entity) {
		EntityManager em = factory.createEntityManager();
		EntityTransaction txn = em.getTransaction();
		txn.begin();
		em.persist(entity);
		txn.commit();
	}
	
	public <T >void updateEntity(T entity) {
		EntityManager em = factory.createEntityManager();
		EntityTransaction txn = em.getTransaction();
		txn.begin();
		em.merge(entity);
		txn.commit();
	}
	
	public <T> T getEntity(Class<T> entityClass, int id) {
		EntityManager em = factory.createEntityManager();
		return em.find(entityClass, id);
	}

	public <T >void deleteEntity(Class<T> entityClass,int id) {
		EntityManager em = factory.createEntityManager();
		EntityTransaction txn = em.getTransaction();
		txn.begin();
		T mergedCourse = em.find(entityClass, id);
		em.remove(mergedCourse);
		txn.commit();
	}
}
