package org.persistence;

import org.entities.Manufacturer;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@ApplicationScoped
public class ManufacturersDAO {
    @PersistenceContext
    private EntityManager em;

    public List<Manufacturer> loadAll() {
        return em.createNamedQuery("Manufacturer.findAll", Manufacturer.class).getResultList();
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public void persist(Manufacturer manufacturer){
        this.em.persist(manufacturer);
    }

    public Manufacturer findOne(Integer id) { return em.find(Manufacturer.class, id); }
}
