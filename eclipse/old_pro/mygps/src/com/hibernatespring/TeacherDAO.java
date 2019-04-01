package com.hibernatespring;

import java.util.List;

import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.transaction.annotation.Transactional;

/**
 * A data access object (DAO) providing persistence and search support for
 * Teacher entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see Teacher
 * @author MyEclipse Persistence Tools
 */
@Transactional
public class TeacherDAO {
	private static final Logger log = LoggerFactory.getLogger(TeacherDAO.class);
	// property constants
	public static final String JOB_NUMBER = "jobNumber";
	public static final String TEA_NAME = "teaName";
	public static final String DEPARTMENT = "department";
	public static final String GENDER = "gender";
	public static final String TELPHONE = "telphone";
	public static final String MOBILE = "mobile";
	public static final String _EMAIL = "EMail";
	public static final String ID_TOKEN = "idToken";
	public static final String WE_CHAT_OPEN_ID = "weChatOpenId";

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

	public void save(Teacher transientInstance) {
		log.debug("saving Teacher instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Teacher persistentInstance) {
		log.debug("deleting Teacher instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Teacher findById(Integer id) {
		log.debug("getting Teacher instance with id: " + id);
		try {
			Teacher instance = (Teacher) getCurrentSession().get(
					"com.hibernatespring.Teacher", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Teacher instance) {
		log.debug("finding Teacher instance by example");
		try {
			List results = getCurrentSession()
					.createCriteria("com.hibernatespring.Teacher")
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
		log.debug("finding Teacher instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Teacher as model where model."
					+ propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

//	public List findByJobNumber(Object jobNumber) {
//		return findByProperty(JOB_NUMBER, jobNumber);
//	}
//
//	public List findByTeaName(Object teaName) {
//		return findByProperty(TEA_NAME, teaName);
//	}
//
//	public List findByDepartment(Object department) {
//		return findByProperty(DEPARTMENT, department);
//	}
	
	 //��ȡ��ʦ��Ϣ
    public List findTeacher() {
 
		try {
			String queryString="select TeacherID,JobNumber,TeaName,Mobile,E_Mail,ID_Token,Department,Gender,Telphone from teacher";		
			SQLQuery queryObject = getCurrentSession().createSQLQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
    }
	
	//���ݹ��Ż�ȡ��ʦ��Ϣ
    public List findByJobNumber(String neirong) {
 
		try {
			String queryString="select TeacherID,JobNumber,TeaName,Mobile,E_Mail,ID_Token,Department,Gender,Telphone from teacher where JobNumber like ? ";	
			SQLQuery queryObject = getCurrentSession().createSQLQuery(queryString);
			queryObject.setParameter(0, neirong);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
    }
    
  //����������ȡ��ʦ��Ϣ
    public List findByTeaName(String neirong) {
 
		try {
			String queryString="select TeacherID,JobNumber,TeaName,Mobile,E_Mail,ID_Token,Department,Gender,Telphone from teacher where TeaName like ? ";	
			SQLQuery queryObject = getCurrentSession().createSQLQuery(queryString);
			queryObject.setParameter(0, neirong);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
    }
    
  //���ݲ��Ż�ȡ��ʦ��Ϣ
    public List findByDepartment(String neirong) {
 
		try {
			String queryString="select TeacherID,JobNumber,TeaName,Mobile,E_Mail,ID_Token,Department,Gender,Telphone from teacher where Department like ? ";	
			SQLQuery queryObject = getCurrentSession().createSQLQuery(queryString);
			queryObject.setParameter(0, neirong);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
    }
    

	public List findByGender(Object gender) {
		return findByProperty(GENDER, gender);
	}

	public List findByTelphone(Object telphone) {
		return findByProperty(TELPHONE, telphone);
	}

	public List findByMobile(Object mobile) {
		return findByProperty(MOBILE, mobile);
	}

	public List findByEMail(Object EMail) {
		return findByProperty(_EMAIL, EMail);
	}

	public List findByIdToken(Object idToken) {
		return findByProperty(ID_TOKEN, idToken);
	}

	public List findByWeChatOpenId(Object weChatOpenId) {
		return findByProperty(WE_CHAT_OPEN_ID, weChatOpenId);
	}

	public List findAll() {
		log.debug("finding all Teacher instances");
		try {
			String queryString = "from Teacher";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Teacher merge(Teacher detachedInstance) {
		log.debug("merging Teacher instance");
		try {
			Teacher result = (Teacher) getCurrentSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Teacher instance) {
		log.debug("attaching dirty Teacher instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Teacher instance) {
		log.debug("attaching clean Teacher instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(
					instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static TeacherDAO getFromApplicationContext(ApplicationContext ctx) {
		return (TeacherDAO) ctx.getBean("TeacherDAO");
	}
	
	
	//��ѯ���н�ʦ�Ĺ��ź�����
		public List findAllJobNumberAndName() {
			log.debug("finding all Teachers' JobNumber And Name");
			try {
				String queryString = "select teacher.jobNumber, teacher.teaName, teacher.department from Teacher as teacher";
				//		+ " where teacher.teacherName='������'";
				Query queryObject = getCurrentSession().createQuery(queryString);
				return queryObject.list();
			} catch (RuntimeException re) {
				log.error("find all failed", re);
				throw re;
			}
		}
		
		//����һ����ʦ�Ĺ��ź���֤�룬��ȡ��ʦ��Ϣ
	    public List findByJobNumberAndToken(String jobNumber, String idToken) {
	        try {
	        	String queryString = "from Teacher as model where model.id.jobNumber = ? " 
	           						+ " and model.id.idToken = ?";
	           	Query queryObject = getCurrentSession().createQuery(queryString);
	  		 	queryObject.setParameter(0, jobNumber);
	  		 	queryObject.setParameter(1, idToken);
	  		 	return queryObject.list();
	        } catch (RuntimeException re) {
	           log.error("find by TeacherJobNumber failed", re);
	           throw re;
	        }
	  	}
}