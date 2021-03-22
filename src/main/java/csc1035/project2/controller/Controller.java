package csc1035.project2.controller;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

/**
 * A CRUD Controller that implements the IContoller interface
 * @param <E> the class parameter for the controller
 */
public class Controller<E> implements IController<E>{

    private final SessionFactory sessionFactory;

    /**
     * Injects a sessionFactory object for the controller to use
     * @param sessionFactory will allows the controller to open sessions
     */
    public Controller(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    /**
     * Saves an object to a database table
     * @param o the object to be saved
     */
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

    /**
     * Updates the object's fields
     * @param s entity that will be updated
     */
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

    /**
     * Update multiple objects from the list e
     * @param e list of entities that will be updated or saved
     */
    @Override
    public void bulkListUpdate(List<E> e) {
        try (Session session = this.sessionFactory.openSession()) {
            session.beginTransaction();
            for (E entry: e) {
                session.saveOrUpdate(entry);
            }
            session.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    /**
     * Read from the database table using class c and int id
     * @param c dictates what class the entity is
     * @param id the integer id that the read will be based on
     * @return the object that was read
     */
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

    /**
     * Read from the database table using class c and string id
     * @param c dictates what class the entity is
     * @param id the String id that the read will be based on
     * @return the object that was read
     */
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

    /**
     * Read all records from a table
     * @param c the class to be fetched
     * @return the list of objects that was read
     */
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

    /**
     * Delete a record based on its integer id
     * @param c the class of the entity to be deleted
     * @param id the integer id of the entity to be deleted
     */
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

    /**
     * Delete a record based on its string id
     * @param c the class of the entity to be deleted
     * @param id the string id of the entity to be deleted
     */
    @Override
    public void delete(Class<E> c, String id) {
        try (Session session = this.sessionFactory.openSession()) {
            session.beginTransaction();
            E entry = session.get(c, id);
            session.delete(entry);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Bulks saves entries from a list
     * @param e the list of entities to be saved
     */
    @Override
    public void bulkListSave(List<E> e) {
        try (Session session = this.sessionFactory.openSession()) {
            session.beginTransaction();
            for (E entry: e){
                session.save(entry);
            }
            session.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
        }


    }
}
