package org.persistence;

import org.entities.Manufacturer;
import org.entities.Processor;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@ApplicationScoped
public class ProcessorsDAO {
    @PersistenceContext
    private EntityManager em;

    public List<Processor> loadAll() {
        return em.createNamedQuery("Processor.findAll", Processor.class).getResultList();
    }

    public void persist(Processor processor){
        this.em.persist(processor);
    }

    public void update(Processor processor){
        this.em.merge(processor);
    }
}
