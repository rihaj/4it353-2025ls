package cz.vse.java.funwithjpa;

import cz.vse.java.funwithjpa.model.User;
import cz.vse.java.funwithjpa.model.UserType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.transaction.Transaction;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Scanner;

public class App {
    private static final Logger LOG = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) {
        EntityManagerFactory EMF = Persistence.createEntityManagerFactory("punit");
        EntityManager em = EMF.createEntityManager();

        User u = em.find(User.class, 1L);
        LOG.info("Zaznam z DB: {}", u);

        var q = em.createQuery("select u from User u where u.id > :xxx", User.class);
        q.setParameter("xxx", 2L);
        LOG.info("Kolekce zaznamu z DB: {}", q.getResultList());

        EntityTransaction t = em.getTransaction();
        t.begin();

        u.setName("XXX_" + u.getName());
        // em.flush();

        t.commit();

        EntityTransaction t2 = em.getTransaction();
        t2.begin();

        User newUser = new User();
        newUser.setType(UserType.USER);
        newUser.setLogin("New User Login");
        newUser.setName("New User Name");
        newUser.setBalance(BigDecimal.ZERO);
        newUser.setDateOfBirth(LocalDate.of(2010, 5, 25));

        em.persist(newUser);

        t2.commit();

        System.out.println("Zadej nove jmeno pro uzivatele s ID 3:");
        Scanner s = new Scanner(System.in);
        String newName = s.nextLine();

        em.getTransaction().begin();
        User u3 = em.find(User.class, 3L);
        u3.setName(newName);
        em.getTransaction().commit();
    }
}
