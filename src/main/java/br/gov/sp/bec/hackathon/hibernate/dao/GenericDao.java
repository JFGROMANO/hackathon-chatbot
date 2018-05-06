package br.gov.sp.bec.hackathon.hibernate.dao;

import java.lang.reflect.ParameterizedType;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import br.gov.sp.bec.hackathon.hibernate.model.OCProposta;


public abstract class GenericDao<T> {
	protected Class<?> classe;
	private Session session;
	
	@SuppressWarnings("unchecked")
	public GenericDao(){
		this.classe = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}
	
	public void save(T t){
		Transaction transation = getSession().beginTransaction();
		getSession().save(t);
		transation.commit();
		closeSession();
		
	}
	
	public void save(List<T> t) {
		Transaction transation = getSession().beginTransaction();
		for (T t2 : t) {
			getSession().save(t2);
		}
		transation.commit();
		closeSession();
	}
	
	public void saveOrUpdate(T t){
		Transaction transation = getSession().beginTransaction();
		getSession().saveOrUpdate(t);
		transation.commit();
		closeSession();
	}
	
	public void saveOrUpdate(List<T> t) {
		Transaction transation = getSession().beginTransaction();
		for (T t2 : t) {
			getSession().saveOrUpdate(t2);
		}
		transation.commit();
		closeSession();
	}
	
	public void delete(T t){
		Transaction transation = getSession().beginTransaction();
		getSession().delete(t);
		transation.commit();
		getSession().close();
	}
	
	public void alter(T t){
		Transaction transation = getSession().beginTransaction();
		getSession().update(t);
		transation.commit();
		closeSession();
	}
	
	public List<T> findAll(){		
		@SuppressWarnings("unchecked")
		CriteriaQuery<T> cq = (CriteriaQuery<T>) getSession().getCriteriaBuilder().createQuery(this.classe);
		cq.from(this.classe);
		List<T> lista = getSession().createQuery(cq).getResultList();
		closeSession();
		return lista;
	}
	
	public List<T> findAllLike(String palavra, String coluna){
		return findAllLike(palavra,coluna,null);
	}
	
	public List<T> findAllLike(String palavra, String coluna, String groupBy){		
		CriteriaBuilder builder = getSession().getCriteriaBuilder();
		CriteriaQuery<T> query = (CriteriaQuery<T>) builder.createQuery(this.classe);
		
		Root<T> from = (Root<T>) query.from(this.classe);
		Predicate predicate = builder.and();
		 predicate = builder.and(predicate, 
			        builder.like(from.<String>get(coluna), "%"+palavra+"%"));
		 TypedQuery<T> typedQuery;
		 if(groupBy == null) {
			 typedQuery = getSession().createQuery(
					    query.select(from )
					    .where( predicate )
					    .orderBy(builder.asc(from.get(coluna)))
					);
		 }else {
			 typedQuery = getSession().createQuery(
					    query.select(from )
					    .where( predicate )
					    .orderBy(builder.asc(from.get(coluna)))
					    .groupBy(from.get(groupBy))
					);
		 }
		List<T> results = typedQuery.getResultList();
		closeSession();
		return results;
	}
	
	public List<T> getAsList(String[] param, Object[] value){
		String parametros = "";
		for(int i = 0; i < param.length; i++) {
			parametros += "AND " + param[i] + "= :val" + i + " ";
		}
		parametros = parametros.replaceFirst("AND", "");
		@SuppressWarnings("unchecked")
		Query<T> query = getSession().createQuery("from " + this.classe.getSimpleName() + " where " + parametros);
		
		for(int v = 0; v < value.length; v++) {
			query.setParameter("val" + v, value[v]);
		}

		List<T> list = query.list();
		closeSession();
		return list;
	}
	
	public List<Long> selectDistinct(String coluna, String tabela){
		List<Long> resultado = new ArrayList<Long>();
		String query = "select distinct " + coluna + " from " + tabela;
	    ListIterator<Object[]> ObjectsIterator = getSession().createSQLQuery(query).list().listIterator();
	    while (ObjectsIterator.hasNext())
	    {
	     Object object = (Object) ObjectsIterator.next();
	     BigInteger codigo = (BigInteger) object;
	     resultado.add(codigo.longValue());
	    }
	    closeSession();
	    return  resultado;
	}
	
	public List<T> getAsList(String param, Object value){
		return getAsList(new String[] {param}, new Object[] {value});
	}
	
	
	protected Session getSession() {
		if(this.session == null || !this.session.isOpen()){
			SessionFactory factory = new Configuration().configure().buildSessionFactory();
			this.session = factory.openSession();
		}
		return this.session;
	}
	
	protected void closeSession() {
		if(this.session != null && this.session.isOpen()){
			this.session.getSessionFactory().close();
			this.session.close();
		}
	}
}