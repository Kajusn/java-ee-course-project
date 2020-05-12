package org.persistence;

import org.entities.Manufacturer;
import org.entities.Processor;

import javax.ejb.Lock;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@ApplicationScoped
public class ProcessorsDAO {
    @Inject
    private EntityManager em;

    public List<Processor> loadAll() {
        return em.createNamedQuery("Processor.findAll", Processor.class).getResultList();
    }

    public void persist(Processor processor){
        this.em.persist(processor);
    }

    public Processor findOne(Integer id) { return em.find(Processor.class, id); }

    public void update(Processor processor){
        this.em.merge(processor);
    }
}
