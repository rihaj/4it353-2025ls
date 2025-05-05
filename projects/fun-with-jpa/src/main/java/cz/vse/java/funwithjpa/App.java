package cz.vse.java.funwithjpa;

import cz.vse.java.funwithjpa.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class App {
    private static final Logger LOG = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) {
        EntityManagerFactory EMF = Persistence.createEntityManagerFactory("punit");
        EntityManager em = EMF.createEntityManager();



        em.close();
        EMF.close();
    }
}
