package course.service;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import course.entity.JPAEntityFactoryBean;


@Component
public class GenericService {
	
	//@Autowired
	//JPAEntityFactoryBean entityFactoryBean;
	//EntityManagerFactory factory;
	
	//@PersistenceContext(unitName = "SpringMVC_JDBC")
	private static final EntityManager em = Persistence.createEntityManagerFactory("SpringMVC_JDBC").createEntityManager();
	
	//private EntityManagerFactory factory;
	
	@PostConstruct
	private void init() {
		System.out.println("gs");
		//factory = entityFactoryBean.getEntityManagerFactory();
	}
	
	/*public GenericService(EntityManagerFactoryBean factoryBean) {
			this.factory = factoryBean.getEntityManagerFactory(); 
	}*/
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public <T> List<T>	getEntities(Class<T> entityClass) {
		//EntityManager em = factory.createEntityManager(); 
		CriteriaBuilder cb =	em.getCriteriaBuilder(); 
		CriteriaQuery cq = 	cb.createQuery(); 
		cq.select(cq.from(entityClass));
		//TypedQuery<T> tq = em.createQuery(cq);
		List<T> entities = em.createQuery(cq).getResultList();
		//em.close();
		return entities;
	}
	
	public <T> void addEntity(T entity) {
		//EntityManager em = factory.createEntityManager();
		EntityTransaction txn = em.getTransaction();
		txn.begin();
		em.persist(entity);
		txn.commit();
		//em.close();
	}
	
	public <T> void updateEntity(T entity) {
		//EntityManager em = factory.createEntityManager();
		EntityTransaction txn = em.getTransaction();
		txn.begin();
		em.merge(entity);
		txn.commit();
		//em.close();
	}
	
	public <T> T getEntity(Class<T> entityClass, int id) {
		//em.flush();
		//Entitem.r
		em.getEntityManagerFactory().getCache().evictAll();
		T entity = em.find(entityClass, id);
		//em.refresh(entity);
		//em.merge(entity);
		//em.close();
		return entity;
	}

	public <T >void deleteEntity(Class<T> entityClass,int id) {
		//EntityManager em = factory.createEntityManager();
		EntityTransaction txn = em.getTransaction();
		txn.begin();
		T mergedCourse = em.find(entityClass, id);
		em.remove(mergedCourse);
		txn.commit();
		//em.close();
	}
}
