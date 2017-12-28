import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;


public class JpaRunner {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.
                createEntityManagerFactory("myDatabaseConfig");
        EntityManager em = emf.createEntityManager();
        EntityTransaction etx = em.getTransaction();
        etx.begin();
//do something
        Address address1= new Address("St1","Ct1","Z1");
        Address address2 = new Address("St2","Ct2","Z2");
        Address address3 = new Address("St3","Ct2","Z3");
        Supplier supplier1 = new Supplier("Company1",address1);
        Supplier supplier2 = new Supplier("Company2",address2);
        Supplier supplier3 = new Supplier("Company3",address3);

        Category category1 = new Category("Cat1");
        Category category2 = new Category("Cat2");
        Category category3 = new Category("Cat3");

        Product product1 = new Product("Prod1",101);
        Product product2 = new Product("Prod2",102);
        Product product3 = new Product("Prod3",103);
        Product product4 = new Product("Prod4",104);


        supplier1.addProduct(product1);
        supplier1.addProduct(product4);
        supplier2.addProduct(product2);
        supplier3.addProduct(product3);

        category1.addProduct(product1);
        category2.addProduct(product2);
        category3.addProduct(product3);
        category3.addProduct(product4);


        ProductTransaction productTransaction1 = new ProductTransaction(10);
        ProductTransaction productTransaction2 = new ProductTransaction(13);
        ProductTransaction productTransaction3 = new ProductTransaction(4);
        ProductTransaction productTransaction4 = new ProductTransaction(15);
        ProductTransaction productTransaction5 = new ProductTransaction(22);

        productTransaction1.addProduct(product1);
        productTransaction1.addProduct(product2);

        productTransaction2.addProduct(product3);
        productTransaction2.addProduct(product2);

        productTransaction3.addProduct(product4);
        productTransaction3.addProduct(product1);

        productTransaction4.addProduct(product1);

        productTransaction5.addProduct(product2);
        productTransaction5.addProduct(product3);
        productTransaction5.addProduct(product4);

        em.persist(product1);
        em.persist(product2);
        em.persist(product3);
        em.persist(product4);

        em.persist(supplier1);
        em.persist(supplier2);
        em.persist(supplier3);

        em.persist(category1);
        em.persist(category2);
        em.persist(category3);

        etx.commit();
        em.close();
    }
}
