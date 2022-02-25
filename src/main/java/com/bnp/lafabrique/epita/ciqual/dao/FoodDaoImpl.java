package com.bnp.lafabrique.epita.ciqual.dao;

import com.bnp.lafabrique.epita.ciqual.domaine.Food;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.Query;
import java.util.List;


public class FoodDaoImpl implements FoodDao {

    @Override
    public Food create(Food food) {
        SessionFactory sessionFactory=DaoFactory.getSessionFactory();
        Session session=sessionFactory.openSession();
        try {
            session.beginTransaction();
            session.saveOrUpdate(food);
            session.getTransaction().commit();
            return food;
        } finally {
            session.close();
        }
    }

    @Override
    public Food findFoodByCode(String foodCode) {
        SessionFactory sessionFactory=DaoFactory.getSessionFactory();
        Session session=sessionFactory.openSession();

        Food food;
        try {
            session.beginTransaction();
            food = session.bySimpleNaturalId(Food.class).load(foodCode);
            session.getTransaction().commit();
            return food;
        } finally {
            session.close();
        }
    }

    @Override
    public List<Food> findFoodByName(String name) {
        SessionFactory sessionFactory=DaoFactory.getSessionFactory();
        Session session=sessionFactory.openSession();

        List<Food> foodList;
        try {
            Query query = session.createQuery("select f from Food f where f.name like :name");
            query.setParameter("name",name+"%");
            foodList=query.getResultList();

            return foodList;
        } finally {
            session.close();
        }
    }
}
