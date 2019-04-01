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
 * A data access object (DAO) providing persistence and search support for Gps
 * entities. Transaction control of the save(), update() and delete() operations
 * can directly support Spring container-managed transactions or they can be
 * augmented to handle user-managed Spring transactions. Each of these methods
 * provides additional information for how to configure it for the desired type
 * of transaction control.
 * 
 * @see Gps
 * @author MyEclipse Persistence Tools
 */
@Transactional
public class GpsDAO {
	private static final Logger log = LoggerFactory.getLogger(GpsDAO.class);
	// property constants
	public static final String BUS_ID = "busId";
	public static final String BUS_LATI = "busLati";
	public static final String BUS_LONGI = "busLongi";
	public static final String STATUS = "status";
	public static final String LATI_SPHERE = "latiSphere";
	public static final String LONGI_SPHERE = "longiSphere";
	public static final String SPEED = "speed";
	public static final String DIRECTON = "directon";

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

	public void save(Gps transientInstance) {
		log.debug("saving Gps instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Gps persistentInstance) {
		log.debug("deleting Gps instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Gps findById(Integer id) {
		log.debug("getting Gps instance with id: " + id);
		try {
			Gps instance = (Gps) getCurrentSession().get(
					"com.hibernatespring.Gps", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Gps instance) {
		log.debug("finding Gps instance by example");
		try {
			List results = getCurrentSession()
					.createCriteria("com.hibernatespring.Gps")
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
		log.debug("finding Gps instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Gps as model where model."
					+ propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByBusId(Object busId) {
		return findByProperty(BUS_ID, busId);
	}

	public List findByBusLati(Object busLati) {
		return findByProperty(BUS_LATI, busLati);
	}

	public List findByBusLongi(Object busLongi) {
		return findByProperty(BUS_LONGI, busLongi);
	}

	public List findByStatus(Object status) {
		return findByProperty(STATUS, status);
	}

	public List findByLatiSphere(Object latiSphere) {
		return findByProperty(LATI_SPHERE, latiSphere);
	}

	public List findByLongiSphere(Object longiSphere) {
		return findByProperty(LONGI_SPHERE, longiSphere);
	}

	public List findBySpeed(Object speed) {
		return findByProperty(SPEED, speed);
	}

	public List findByDirecton(Object directon) {
		return findByProperty(DIRECTON, directon);
	}

	public List findAll() {
		log.debug("finding all Gps instances");
		try {
			String queryString = "from Gps";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Gps merge(Gps detachedInstance) {
		log.debug("merging Gps instance");
		try {
			Gps result = (Gps) getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Gps instance) {
		log.debug("attaching dirty Gps instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Gps instance) {
		log.debug("attaching clean Gps instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(
					instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static GpsDAO getFromApplicationContext(ApplicationContext ctx) {
		return (GpsDAO) ctx.getBean("GpsDAO");
	}
}