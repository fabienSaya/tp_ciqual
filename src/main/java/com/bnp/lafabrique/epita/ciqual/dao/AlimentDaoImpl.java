package com.bnp.lafabrique.epita.ciqual.dao;

import com.bnp.lafabrique.epita.ciqual.domaine.Aliment;
import org.hibernate.Session;
import org.hibernate.SessionFactory;


public class AlimentDaoImpl implements AlimentDao{

    @Override
    public Long create(Aliment aliment) {
        SessionFactory sessionFactory=DaoFactory.getSessionFactory();

        Session session=sessionFactory.openSession();
        session.beginTransaction();
        session.saveOrUpdate(aliment);

        session.getTransaction().commit();
        session.close();

        return aliment.getId();
    }
}
