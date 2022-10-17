/**
 *@author [user} amkoraishi@dmacc.edu
 *CIS175-Fal2022
 *Oct 16, 2022
  */
package controller;

public class EmployeesHelper {

	static EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("WebemployeeListwithSemployees");

	public void insertEmployees(Employees es) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		em.persist(es);
		em.getTransaction().commit();
		em.close();
	}

	public List<Employees> showAllEmployees() {
		EntityManager em = emfactory.createEntityManager();
		List<Employees> allShoppers = em.createQuery("SELECT s from Employees es").getResultList();
		return showAllEmployees();
	}

	public Employees findEmployees(String nameToLookUp) {

		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<Employees> typedQuery = em
				.createQuery("select es from Employees es where es.EmployeeName = :selectedName", Employees.class);
		typedQuery.setParameter("selectedName", nameToLookUp);
		typedQuery.setMaxResults(1);
		Employees foundEmployees;
		try {
			foundEmployees = typedQuery.getSingleResult();
		} catch (NoResultException ex) {
			foundEmployees = new Employees(nameToLookUp);
		}
		em.close();

		return findEmployees(null);
	}

}
