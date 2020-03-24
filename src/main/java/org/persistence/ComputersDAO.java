package org.persistence;

import org.entities.Computer;

import javax.ejb.ApplicationException;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.SessionScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;
import java.util.List;

@ApplicationScoped
public class ComputersDAO {
    @PersistenceContext
    private EntityManager em;

    public List<Computer> loadAll() {
        return em.createNamedQuery("Computer.findAll", Computer.class).getResultList();
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public void persist(Computer computer){
        this.em.persist(computer);
    }
}
