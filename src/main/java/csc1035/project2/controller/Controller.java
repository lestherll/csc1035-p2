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
        try (Session session = this.sessionFactory.openSession()) {
            session.beginTransaction();
            session.saveOrUpdate(s);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public E getById(Class<E> c, int id) {
        E entry = null;
        try (Session session = this.sessionFactory.openSession()) {
            session.beginTransaction();
            entry = session.get(c, id);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return entry;
    }

    @Override
    public E getById(Class<E> c, String id) {
        E entry = null;
        try (Session session = this.sessionFactory.openSession()) {
            session.beginTransaction();
            entry = session.get(c, id);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return entry;
    }

    @Override
    public List<E> getAll(Class<E> c) {
        List<E> entries = null;
        try (Session session = this.sessionFactory.openSession()) {
            session.beginTransaction();
            entries = session.createQuery("from "+c.getSimpleName(), c).list();
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return entries;
    }

    @Override
    public void delete(Class<E> c, int id) {
        try (Session session = this.sessionFactory.openSession()) {
            session.beginTransaction();
            E entry = session.get(c, id);
            session.delete(entry);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    @Override
    public void bulkListSave(List<E> entity) {
        try (Session session = this.sessionFactory.openSession()) {
            session.beginTransaction();
            for (E entry: entity){
                session.save(entry);
            }
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
