package ru.job4j.tracker;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.List;

public class HbmTracker implements Store, AutoCloseable {
    private final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
            .configure().build();
    private final SessionFactory sf = new MetadataSources(registry)
            .buildMetadata().buildSessionFactory();

    @Override
    public void close() throws Exception {
        StandardServiceRegistryBuilder.destroy(registry);
    }

    @Override
    public Item add(Item item) {
        Session session = sf.openSession();
        session.beginTransaction();
        session.save(item);
        session.getTransaction().commit();
        session.close();
        return item;
    }

    @Override
    public boolean replace(Integer id, Item item) {
        boolean rsl = true;
        Session session = sf.openSession();
        session.beginTransaction();
        try {
            session.createQuery(
                    "UPDATE Item SET name = :fName WHERE id = :fId")
                    .setParameter("fName", item.getName())
                    .setParameter("fId", id)
                    .executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            rsl = false;
        }
        return rsl;
    }

    @Override
    public boolean delete(Integer id) {
        boolean rsl = true;
        Session session = sf.openSession();
        session.beginTransaction();
        try {
            Item item = new Item();
            item.setId(id);
            session.delete(item);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            rsl = false;
        }
        return rsl;
    }

    @Override
    public List<Item> findAll() {
        Session session = sf.openSession();
        session.beginTransaction();
        List<Item> list = session.createQuery(
                "FROM Item", Item.class).list();
        session.close();
        return list;
    }

    @Override
    public List<Item> findByName(String key) {
        Session session = sf.openSession();
        session.beginTransaction();
        List<Item> list = session.createQuery(
                "FROM Item i WHERE i.name LIKE fName", Item.class)
                .setParameter("fName", key).list();
        session.close();
        return list;
    }

    @Override
    public Item findById(Integer id) {
        Session session = sf.openSession();
        session.beginTransaction();
        Item item = session.createQuery(
                "FROM Item i WHERE i.id = :fId", Item.class)
                .setParameter("fId", id).uniqueResult();
        session.close();
        return item;
    }
}
