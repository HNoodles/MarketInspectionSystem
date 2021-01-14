package com.fudan.market_inspection.utils;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class DataUtils {
    private static Configuration configuration;
    private static SessionFactory factory;

    private static Session getSession() {
        // lazy load configuration, session factory
        if (configuration == null) {
            configuration = new Configuration();
            configuration.configure(); // load file hibernate.cfg.xml for default
            factory = configuration.buildSessionFactory();
        }
        return factory.openSession();
    }

    public static <T> List<T> getObjects(Class<T> T) {
        Session session = getSession();
        Criteria criteria = session.createCriteria(T);
        List<T> result = criteria.list();
        endSession(session);
        return result;
    }

    private static void endSession(Session session) {
        session.close();
    }
}
