package com.bnp.lafabrique.epita.ciqual.dao;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DaoFactory {

    private static SessionFactory sessionFactory;

    public static AlimentDao getProduitDao() {
        return new AlimentDaoImpl();
    }

    public static SessionFactory getSessionFactory() {
        if (sessionFactory==null) {
            sessionFactory = new Configuration().configure("hibernate-cfg.xml").buildSessionFactory();
        }
        return sessionFactory;
    }

}
