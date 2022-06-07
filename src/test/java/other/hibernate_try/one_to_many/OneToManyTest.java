package other.hibernate_try.one_to_many;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.util.HashSet;
import java.util.Set;

public class OneToManyTest {


    public static void main(String[] args) {
        AccountEntity account1 = new AccountEntity();
        account1.setAccountNumber("Account detail 1");

        AccountEntity account2 = new AccountEntity();
        account2.setAccountNumber("Account detail 2");

        AccountEntity account3 = new AccountEntity();
        account3.setAccountNumber("Account detail 3");

//Add new Employee object
        EmployeeEntity employee = new EmployeeEntity();
        employee.setEmail("demo-user-first@mail.com");

        Set<AccountEntity> accountList = new HashSet<AccountEntity>();
        accountList.add(account1);
        accountList.add(account2);
        accountList.add(account3);

        employee.setEmail("employee@gmail.com");
        employee.setAccounts(accountList);


//Save Employee
        Configuration cfg = new Configuration().addAnnotatedClass(other.hibernate_try.one_to_many.AccountEntity.class)
                .addAnnotatedClass(EmployeeEntity.class).configure();
        ServiceRegistry serviceRegistry =  new StandardServiceRegistryBuilder().applySettings(cfg.getProperties()).build();
        SessionFactory sessionFactory = cfg.buildSessionFactory(serviceRegistry);
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.persist(employee);
        session.getTransaction().commit();



    }

}
