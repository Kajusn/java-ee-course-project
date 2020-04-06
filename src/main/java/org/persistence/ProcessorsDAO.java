package org.persistence;

import org.entities.Processor;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@ApplicationScoped
public class ProcessorsDAO {
    @PersistenceContext
    private EntityManager em;

    public void persist(Processor processor){
        this.em.persist(processor);
    }
}
