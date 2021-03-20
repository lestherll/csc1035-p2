package csc1035.project2.controller;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;


public class Controller<E> implements IController<E>{

    private final SessionFactory sessionFactory;

    public Controller(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void save(Object o) {
        try (Session session = this.sessionFactory.openSession()) {
            session.beginTransaction();
            session.save(o);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void update(Object s) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.saveOrUpdate(s);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public E getById(Class<E> c, int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        E entry = session.get(c, id);
        session.getTransaction().commit();
        session.close();
        return entry;
    }

    @Override
    public E getById(Class<E> c, String id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        E entry = session.get(c, id);
        session.getTransaction().commit();
        session.close();
        return entry;
    }

    @Override
    public List<E> getAll(Class<E> c) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        List<E> entries = session.createQuery("from "+c.getSimpleName()).list();
        session.getTransaction().commit();
        session.close();
        return entries;
    }

    @Override
    public void delete(Class<E> c, int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        E entry = session.get(c, id);
        session.delete(entry);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void bulkListSave(List<E> e) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        for (E entry: e){
            session.save(entry);
        }
        session.getTransaction().commit();
        session.close();
    }
}
