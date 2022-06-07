package other.hibernate_try.first_example;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class PersonTest {
    private Session session = null;

    private Session createHibernateSession() {
        SessionFactory sessionFactory = null;
        ServiceRegistry serviceRegistry = null;
        try {
            try {
                Configuration cfg = new Configuration().addResource("person.hbm.xml").configure();
                serviceRegistry = new StandardServiceRegistryBuilder().
                        applySettings(cfg.getProperties()).build();
                sessionFactory = cfg.buildSessionFactory(serviceRegistry);
            } catch (Throwable e) {
                System.err.println("Failed to create sessionFactory object." + e);
                throw new ExceptionInInitializerError(e);
            }
            session = sessionFactory.openSession();
            System.out.println("Session creating");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return session;
    }

    /**
     * Adding records to DB
     */
    private void recordsAdd(List<Person> list) {
        try {
            System.out.println("Adding recorde to DB");
            Transaction tx = session.beginTransaction();
            for (Person person : list) {
                session.save(person);
            }
            tx.commit();
            System.out.println("\tRecords added");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Reading records from DB
     */
    private List<Person>  recordsRead()    {
        System.out.println("\nReading records");
        String query = "select p from " + Person.class.getSimpleName() + " p";
        @SuppressWarnings("unchecked")
        List<Person> list = (List<Person>)session.createQuery(query).list();
        System.out.println(list);
        return list;
    }

    /**
     * Find record by id
     */
    private Person recordFind(int id)
    {
        System.out.println("\nFinding record by id");
        Person person =  (Person) session.load(Person.class, id);
        System.out.println(person);
        return person;
    }

    public static void main(String[] args) {
        PersonTest p = new PersonTest();
        p.session= p.createHibernateSession();
        List<Person> personList = new ArrayList<>();
        Person karen = new Person();
        karen.setId(2);
        karen.setName("Sveta");
        personList.add(karen);
//        p.recordsAdd(personList);
        List<Person> personsFromDBList= p.recordsRead();
//        p.recordFind(2);
        p.session.close();

    }
}
