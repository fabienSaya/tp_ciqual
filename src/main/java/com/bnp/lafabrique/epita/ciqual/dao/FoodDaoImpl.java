package com.bnp.lafabrique.epita.ciqual.dao;

import com.bnp.lafabrique.epita.ciqual.domaine.Food;
import org.hibernate.Session;
import org.hibernate.SessionFactory;


public class FoodDaoImpl implements FoodDao {

    @Override
    public Long create(Food food) {
        SessionFactory sessionFactory=DaoFactory.getSessionFactory();

        Session session=sessionFactory.openSession();
        session.beginTransaction();
        session.saveOrUpdate(food);

        session.getTransaction().commit();
        session.close();

        return food.getId();
    }
}
