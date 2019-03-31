package com.hibernatespring;

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
 * A data access object (DAO) providing persistence and search support for Tmenu
 * entities. Transaction control of the save(), update() and delete() operations
 * can directly support Spring container-managed transactions or they can be
 * augmented to handle user-managed Spring transactions. Each of these methods
 * provides additional information for how to configure it for the desired type
 * of transaction control.
 * 
 * @see Tmenu
 * @author MyEclipse Persistence Tools
 */
@Transactional
public class TmenuDAO {
	private static final Logger log = LoggerFactory.getLogger(TmenuDAO.class);
	// property constants
	public static final String MENU_NAME = "menuName";
	public static final String MENU_TYPE = "menuType";
	public static final String MENU_KEY_OR_URL = "menuKeyOrUrl";
	public static final String FATHER_MENU_ID = "fatherMenuId";

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

	public void save(Tmenu transientInstance) {
		log.debug("saving Tmenu instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Tmenu persistentInstance) {
		log.debug("deleting Tmenu instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Tmenu findById(Integer id) {
		log.debug("getting Tmenu instance with id: " + id);
		try {
			Tmenu instance = (Tmenu) getCurrentSession().get(
					"com.hibernatespring.Tmenu", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Tmenu instance) {
		log.debug("finding Tmenu instance by example");
		try {
			List results = getCurrentSession()
					.createCriteria("com.hibernatespring.Tmenu")
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
		log.debug("finding Tmenu instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Tmenu as model where model."
					+ propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByMenuName(Object menuName) {
		return findByProperty(MENU_NAME, menuName);
	}

	public List findByMenuType(Object menuType) {
		return findByProperty(MENU_TYPE, menuType);
	}

	public List findByMenuKeyOrUrl(Object menuKeyOrUrl) {
		return findByProperty(MENU_KEY_OR_URL, menuKeyOrUrl);
	}

	public List findByFatherMenuId(Object fatherMenuId) {
		return findByProperty(FATHER_MENU_ID, fatherMenuId);
	}

	public List findAll() {
		log.debug("finding all Tmenu instances");
		try {
			String queryString = "from Tmenu";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Tmenu merge(Tmenu detachedInstance) {
		log.debug("merging Tmenu instance");
		try {
			Tmenu result = (Tmenu) getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Tmenu instance) {
		log.debug("attaching dirty Tmenu instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Tmenu instance) {
		log.debug("attaching clean Tmenu instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(
					instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static TmenuDAO getFromApplicationContext(ApplicationContext ctx) {
		return (TmenuDAO) ctx.getBean("TmenuDAO");
	}
}