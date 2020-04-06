package org.persistence;

import org.entities.Computer;
import org.entities.Manufacturer;

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

    public void persist(Computer computer){
        this.em.persist(computer);
    }

    public Computer findOne(Integer id) { return em.find(Computer.class, id); }
}
