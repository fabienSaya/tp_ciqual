package com.bnp.lafabrique.epita.ciqual.dao;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DaoFactory {

    private static SessionFactory sessionFactory;

    public static FoodDao getProduitDao() {
        return new FoodDaoImpl();
    }

    public static FoodComponentTypeDao getComponentTypeDao() {
        return new FoodComponentTypeDaoImpl();
    }

    public static SessionFactory getSessionFactory() {
        if (sessionFactory==null) {
            sessionFactory = new Configuration().configure("hibernate-cfg.xml").buildSessionFactory();
        }
        return sessionFactory;
    }

}
