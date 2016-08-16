package startup;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

/**
 * Created by Daniel on 2016/4/24.
 */
public class HibernateBoot {
    private static SessionFactory factory;

    public static void init() {
        if (factory == null) {
            StandardServiceRegistry register = new StandardServiceRegistryBuilder().configure()
                    .build();
            factory = new MetadataSources().buildMetadata(register).buildSessionFactory();
        }
    }

    private HibernateBoot() {

    }

    public static Session openSession() {
        if (factory == null)
            init();
        return factory.openSession();
    }


    public static void closeSession(Session session) {
        session.close();
    }

    public static void closeConnection() {
        if (factory == null)
            return;
        factory.close();
        factory = null;
    }
}
