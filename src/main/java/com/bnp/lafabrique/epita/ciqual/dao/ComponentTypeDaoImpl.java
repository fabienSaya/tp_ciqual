package com.bnp.lafabrique.epita.ciqual.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.Query;
import java.util.List;

public class ComponentTypeDaoImpl implements ComponentTypeDao {
/*
    @Override
    public List<ComponentType> findComponentTypeByNom(String nom) {
        SessionFactory sessionFactory = DaoFactory.getSessionFactory();
        Session session = sessionFactory.openSession();
        List<ComponentType> list;
        try {
            Query query = session.createQuery("select c from ComponentType p where p.nom like :name");
            query.setParameter("name",nom+"%");
            list=query.getResultList();
        } finally {
            session.close();
        }
        return list;
    }*/
}
