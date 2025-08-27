package org.openarcadia;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class App {
    private static SessionFactory factory;

    static {
        factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Pet.class)
                .buildSessionFactory();
    }

    public static void main(String[] args) {
        try {
            addPet("Buddy", "Dog", "Alice","kolkata",1);
            addPet("Whiskers", "Cat", "Bob","chennai",2);

            List<String> pets = getAllPetNames();
            System.out.println("Pets in DB: " + pets);
        } finally {
            factory.close();
        }
    }

    public static void addPet(String name, String type, String owner,String lname , int year) {
        try (Session session = factory.openSession()) {
            session.beginTransaction();
            Pet pet = new Pet();
            pet.setPetName(name);
            pet.setPetType(type);
            pet.setOwnerName(owner);
            location l = new location(lname,year);
            pet.setloc(l);
            session.save(pet);
            session.getTransaction().commit();
            System.out.println("Pet '" + name + "' saved successfully.");
        }
    }

    public static List<String> getAllPetNames() {
        try (Session session = factory.openSession()) {
            return session.createQuery(
                    "SELECT p.petName FROM Pet p", String.class
            ).getResultList();
        }
    }
}
