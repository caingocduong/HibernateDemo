package com.example.dao;

import com.example.entities.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Repository
public class EmployeeDAOImpl implements EmployeeDAO{

    private SessionFactory sessionFactory;

    @Autowired
    public EmployeeDAOImpl(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    private Session session(){

        return sessionFactory.getCurrentSession();
    }

    public List<Employee> retrieveEmployees() {
        CriteriaBuilder builder = session().getCriteriaBuilder();
        CriteriaQuery<Employee> criteriaQuery = builder.createQuery(Employee.class);
        criteriaQuery.from(Employee.class);

        return session().createQuery(criteriaQuery).getResultList();
    }

    @SuppressWarnings("unchecked")
    public Employee getEmployeeById(int id) {
        String hql = "from Employee where id="+id;
        Query query = session().createQuery(hql);
        List<Employee> employees = (List<Employee>) query.list();
        if(employees != null && !employees.isEmpty()){
            return  employees.get(0);
        }

        return null;
    }

    public void save(Employee emp) {
        session().save(emp);
    }

    public void update(Employee emp) {
        Employee newEmployee = session().load(Employee.class, emp.getEmployeeId());

        newEmployee.setEmployeeName(emp.getEmployeeName());
        newEmployee.setJob(emp.getJob());
        newEmployee.setLocation(emp.getLocation());
        newEmployee.setSalary(emp.getSalary());

        session().update(newEmployee);
    }

    public void delete(int id) {
        Employee empToDelete = getEmployeeById(id);
        session().delete(empToDelete);
    }
}
