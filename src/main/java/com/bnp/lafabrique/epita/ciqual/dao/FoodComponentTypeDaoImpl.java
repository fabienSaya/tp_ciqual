package com.bnp.lafabrique.epita.ciqual.dao;

import com.bnp.lafabrique.epita.ciqual.domaine.FoodComponentType;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.Query;
import java.util.List;

public class FoodComponentTypeDaoImpl implements FoodComponentTypeDao {

    @Override
    public FoodComponentType create(FoodComponentType componentType) {
        SessionFactory sessionFactory = DaoFactory.getSessionFactory();
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            session.saveOrUpdate(componentType);
            session.getTransaction().commit();
            return componentType;
        } finally {
            session.close();
        }
    }

    @Override
    public List<FoodComponentType> getAllComponentTypes() {
        SessionFactory sessionFactory = DaoFactory.getSessionFactory();
        Session session = sessionFactory.openSession();
        try {
            Query query = session.createQuery("select c from FoodComponentType c");
            return query.getResultList();
        } finally {
            session.close();
        }
    }

    @Override
    public FoodComponentType findComponentTypeByName(String name) {
        SessionFactory sessionFactory = DaoFactory.getSessionFactory();
        Session session = sessionFactory.openSession();

        FoodComponentType foodComponentType;
        try {
            session.beginTransaction();
            foodComponentType = session.bySimpleNaturalId(FoodComponentType.class).load(name);
            session.getTransaction().commit();
            return foodComponentType;
        } finally {
            session.close();
        }
    }
}
