package br.com.itarocha.hospedagem.repository;

import io.micronaut.configuration.hibernate.jpa.scope.CurrentSession;
import io.micronaut.runtime.ApplicationConfiguration;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class AbstractRepository {

    @PersistenceContext
    private EntityManager em;

    private final ApplicationConfiguration cfg;

    AbstractRepository(@CurrentSession EntityManager em, ApplicationConfiguration cfg){
        this.em = em;
        this.cfg = cfg;
    }

    public EntityManager getEm() {
        return em;
    }

    /*
    public void setEm(EntityManager em) {
        this.em = em;
    }
     */

    public ApplicationConfiguration getCfg(){
        return cfg;
    }
}
