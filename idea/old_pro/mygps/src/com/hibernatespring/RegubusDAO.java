package com.hibernatespring;

import java.util.Date;
import java.util.List;
import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.transaction.annotation.Transactional;

/**
 * A data access object (DAO) providing persistence and search support for
 * Regubus entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see Regubus
 * @author MyEclipse Persistence Tools
 */
@Transactional
public class RegubusDAO {
	private static final Logger log = LoggerFactory.getLogger(RegubusDAO.class);
	// property constants
	public static final String REG_START = "regStart";
	public static final String REG_END = "regEnd";
	public static final String REG_CONTENT = "regContent";
	public static final String BUS_ID = "busId";
	public static final String REG_NOTE = "regNote";

	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	private Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	protected void initDao() {
		// do nothing
	}

	public void save(Regubus transientInstance) {
		log.debug("saving Regubus instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Regubus persistentInstance) {
		log.debug("deleting Regubus instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Regubus findById(Integer id) {
		log.debug("getting Regubus instance with id: " + id);
		try {
			Regubus instance = (Regubus) getCurrentSession().get(
					"com.hibernatespring.Regubus", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Regubus instance) {
		log.debug("finding Regubus instance by example");
		try {
			List results = getCurrentSession()
					.createCriteria("com.hibernatespring.Regubus")
					.add(Example.create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding Regubus instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Regubus as model where model."
					+ propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByRegStart(Object regStart) {
		return findByProperty(REG_START, regStart);
	}

	public List findByRegEnd(Object regEnd) {
		return findByProperty(REG_END, regEnd);
	}

	public List findByRegContent(Object regContent) {
		return findByProperty(REG_CONTENT, regContent);
	}

	public List findByBusId(Object busId) {
		return findByProperty(BUS_ID, busId);
	}

	public List findByRegNote(Object regNote) {
		return findByProperty(REG_NOTE, regNote);
	}

	public List findAll() {
		log.debug("finding all Regubus instances");
		try {
			String queryString = "from Regubus";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Regubus merge(Regubus detachedInstance) {
		log.debug("merging Regubus instance");
		try {
			Regubus result = (Regubus) getCurrentSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Regubus instance) {
		log.debug("attaching dirty Regubus instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Regubus instance) {
		log.debug("attaching clean Regubus instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(
					instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static RegubusDAO getFromApplicationContext(ApplicationContext ctx) {
		return (RegubusDAO) ctx.getBean("RegubusDAO");
	}
}