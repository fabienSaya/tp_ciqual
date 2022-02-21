package com.bnp.lafabrique.epita.ciqual.dao;

import com.bnp.lafabrique.epita.ciqual.domaine.FoodComponentType;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.Query;
import java.util.List;

public class FoodComponentTypeDaoImpl implements FoodComponentTypeDao {

    @Override
    public long create(FoodComponentType componentType) {
        //first we check component does not exist
        List <FoodComponentType> list= findComponentTypeByName(componentType.getName());
        if(!list.isEmpty()) {
            return list.get(0).getId();
        } else {
            SessionFactory sessionFactory = DaoFactory.getSessionFactory();
            Session session = sessionFactory.openSession();
            try {
                session.beginTransaction();
                session.saveOrUpdate(componentType);
                session.getTransaction().commit();
            } finally {
                session.close();
            }


            return componentType.getId();
        }
    }

    @Override
    public List <FoodComponentType> getAllComponentTypes() {
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
    public List<FoodComponentType> findComponentTypeByName(String name) {
        SessionFactory sessionFactory = DaoFactory.getSessionFactory();
        Session session = sessionFactory.openSession();
        List<FoodComponentType> list;
        try {
            Query query = session.createQuery("select c from FoodComponentType c where c.name like :name");
            query.setParameter("name",name+"%");
            list=query.getResultList();
        } finally {
            session.close();
        }
        return list;
    }
}
