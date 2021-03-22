package csc1035.project2.controller;

import java.util.List;

/**
 * Interface for a database CRUD controller
 * @param <E> class parameter for the controller
 */
public interface IController<E> {

    /**
     * Signature for save method
     * @param e entity that will be saved
     */
    void save(E e); // Create

    /**
     * Signature for update method
     * @param s entity that will be updated
     */
    void update(E s); // Update

    /**
     * Signature for bulkListUpdate
     * @param e list of entities that will be updated or saved
     */
    void bulkListUpdate(List<E> e);

    /**
     * Signature for read method with integer id
     * @param c dictates what class the entity is
     * @param id the integer id that the read will be based on
     * @return entity of class c
     */
    E getById(Class<E> c, int id); // Read

    /**
     * Signature for read method with string id
     * @param c dictates what class the entity is
     * @param id the String id that the read will be based on
     * @return entity of class c
     */
    E getById(Class<E> c, String id); // Read String Id

    /**
     * Signature for bulk read
     * @param c the class to be fetched
     * @return the list of entities with class c
     */
    List<E> getAll(Class<E> c); // Supporting method for retrieving all entries in a database table

    /**
     * Signature for deletion by integer id
     * @param c the class of the entity to be deleted
     * @param id the integer id of the entity to be deleted
     */
    void delete(Class<E> c, int id); // Delete

    /**
     * Signature for deletion by string id
     * @param c the class of the entity to be deleted
     * @param id the string id of the entity to be deleted
     */
    void delete(Class<E> c, String id); // Delete

    /**
     * Signature for bulk list save method
     * @param e the list of entities to be saved
     */
    void bulkListSave(List<E> e); // Bulk save a list collection of entries
}
