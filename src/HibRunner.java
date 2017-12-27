import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class HibRunner {
    private static SessionFactory sessionFactory = null;
    public static void main(String[] args) {
        sessionFactory = getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();

        Supplier supplier1 = new Supplier("Company1");
        Supplier supplier2 = new Supplier("Company2");
        Supplier supplier3 = new Supplier("Company3");

        Category category1 = new Category("Cat1");
        Category category2 = new Category("Cat2");
        Category category3 = new Category("Cat3");

        Product product1 = new Product("Prod1",101);
        Product product2 = new Product("Prod2",102);
        Product product3 = new Product("Prod3",103);
        Product product4 = new Product("Prod4",104);

        session.save(product1);
        session.save(product2);
        session.save(product3);
        session.save(product4);

        supplier1.addProduct(product1);
        supplier1.addProduct(product4);
        supplier2.addProduct(product2);
        supplier3.addProduct(product3);

        category1.addProduct(product1);
        category2.addProduct(product2);
        category3.addProduct(product3);
        category3.addProduct(product4);


        session.save(supplier1);
        session.save(supplier2);
        session.save(supplier3);

        session.save(category1);
        session.save(category2);
        session.save(category3);

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

        session.save(productTransaction1);
        session.save(productTransaction2);
        session.save(productTransaction3);
        session.save(productTransaction4);
        session.save(productTransaction5);

        tx.commit();
        session.close();
    }
    private static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            Configuration configuration = new Configuration();
            sessionFactory =
                    configuration.configure().buildSessionFactory();
        }
        return sessionFactory;
    }
}
