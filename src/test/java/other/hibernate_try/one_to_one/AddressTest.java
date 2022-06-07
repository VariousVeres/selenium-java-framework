package other.hibernate_try.one_to_one;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class AddressTest {
    private Session session = null;

    private Session createHibernateSession() {
        SessionFactory sessionFactory = null;
        ServiceRegistry serviceRegistry = null;
        try {
            try {
                Configuration cfg = new Configuration().addAnnotatedClass(User.class)
                .addAnnotatedClass(Address.class).configure();
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
    private void recordsAdd(List<User> list) {
        try {
            System.out.println("Adding records to DB");
            Transaction tx = session.beginTransaction();
            for (User user : list) {
                session.save(user);
            }
            tx.commit();
            System.out.println("\tRecords added");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
        AddressTest test = new AddressTest();
        test.session= test.createHibernateSession();
        test.session.beginTransaction();
        Address address = new Address();
        address.setHouseNumber(25);
        address.setStreet("Street");
        User user = new User();
        user.setName("Sveta");//
        user.setAddress(address);

        test.session.save(address);
        test.session.save(user);
        test.session.close();

    }
}
