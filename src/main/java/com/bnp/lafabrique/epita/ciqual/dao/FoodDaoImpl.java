package com.bnp.lafabrique.epita.ciqual.dao;

import com.bnp.lafabrique.epita.ciqual.domaine.Food;
import org.hibernate.Session;
import org.hibernate.SessionFactory;


public class FoodDaoImpl implements FoodDao {

    @Override
    public Food create(Food food) {
        SessionFactory sessionFactory=DaoFactory.getSessionFactory();

        Session session=sessionFactory.openSession();
        session.beginTransaction();
        session.saveOrUpdate(food);

        session.getTransaction().commit();
        session.close();

        return food;
    }

    @Override
    public Food findFoodByCode(String foodCode) {
        SessionFactory sessionFactory=DaoFactory.getSessionFactory();

        Session session=sessionFactory.openSession();
        session.beginTransaction();
        Food food=session.bySimpleNaturalId(Food.class).load(foodCode);

        session.getTransaction().commit();
        session.close();

        return food;

    }
}
