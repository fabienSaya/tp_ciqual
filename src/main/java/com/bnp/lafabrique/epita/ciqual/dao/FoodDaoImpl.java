package com.bnp.lafabrique.epita.ciqual.dao;

import com.bnp.lafabrique.epita.ciqual.domaine.Food;
import org.hibernate.Hibernate;
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
    public Long foodExist(String foodCode) {
        SessionFactory sessionFactory=DaoFactory.getSessionFactory();
        Session session=sessionFactory.openSession();

        Food food;
        try {
            session.beginTransaction();
            food = session.bySimpleNaturalId(Food.class).load(foodCode);
            //return the id if the food is in DB otherwise, return 0
            return food!=null ? food.getId() : 0;
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
            if (food==null) return null;
            //otherwise we finish to load all its values
            Hibernate.initialize(food.getComponentList());
            Hibernate.initialize(food.getScientificName());
            Hibernate.initialize(food.getSubSubGroup());
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
            Query query = session.createQuery("select f from Food f " +
                    //"fetch all properties " +
                    "join fetch f.alimentScientificName " +
                    "join fetch f.alimentSubSubGroup " +
                    //"join fetch f.componentList " +
                    "where f.name like :name");
            query.setParameter("name",name+"%");
            foodList=query.getResultList();

            //verrue pour charger component list sans generer des objets food en pagaille.
            for (Food food:foodList) {
                Hibernate.initialize(food.getComponentList());
            }

            return foodList;
        } finally {
            session.close();
        }
    }

    @Override
    public List<Food> getAllFood() {
        SessionFactory sessionFactory=DaoFactory.getSessionFactory();
        Session session=sessionFactory.openSession();
        try {
            return session.createQuery("select f from Food f").getResultList();
        } finally {
            session.close();
        }

    }
}
