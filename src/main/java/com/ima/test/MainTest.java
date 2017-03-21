package com.ima.test;


import com.ima.repository.UserRepository;
import org.junit.*;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Created by ${符柱成} on 2017/3/8.
 */
public class MainTest {
    private static EntityManagerFactory entityManagerFactory = null;
    private EntityManager entityManager = null;
    @Autowired
    UserRepository userRepository;
    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        entityManagerFactory = Persistence.createEntityManagerFactory("com.ima");
    }

    @Before
    public void setUp() throws Exception {
        entityManager = entityManagerFactory.createEntityManager();



        entityManager.getTransaction().begin();
//        entityManager.persist(user);
        entityManager.getTransaction().commit();
    }

    @Test
    public void find() {
        System.out.print("userRepository : "+userRepository);

    }

    @After
    public void tearDown() throws Exception {
        if (entityManager != null && entityManager.isOpen())
            entityManager.close();
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
        if (entityManagerFactory != null && entityManagerFactory.isOpen())
            entityManagerFactory.close();
    }
}
