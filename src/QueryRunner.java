import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import javax.persistence.TypedQuery;

public class QueryRunner {
    private static SessionFactory sessionFactory = null;
    public static void main(String[] args) {
        sessionFactory = getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();



//        Wydobądź produkty dostarczane przez wybranego dostawce oraz pokaż dostawcę
//        wybranego produktu

        String requiredSupplierCompanyName = "company1";
        TypedQuery<Supplier> q = session.createQuery("from Supplier as s"
                + " where lower(s.companyName)=:name", Supplier.class);
        q.setParameter("name", requiredSupplierCompanyName);
        Supplier supplier = q.getSingleResult();
        System.out.println(supplier.getProducts());


        String requiredProductName = "prod1";
        TypedQuery<Product> q2 = session.createQuery("from Product as p"
                + " where lower(p.productName)=:name", Product.class);
        q2.setParameter("name", requiredProductName);
        Product product = q2.getSingleResult();
        System.out.println("Prod1 transactions: "+product.getProductTransactions());

        int requiredTransactionQuantity = 22;
        TypedQuery<ProductTransaction> q3 = session.createQuery("from ProductTransaction as t"
                + " where t.quantity=:tnum", ProductTransaction.class);
        q3.setParameter("tnum", requiredTransactionQuantity);
        ProductTransaction productTransaction= q3.getSingleResult();
        System.out.println("Tnum 22 products: "+productTransaction.getProducts());


//        TypedQuery<Student> q = em.createQuery("from Student as student"
//                + " where lower(student.name)=:studentName", Student.class);
//        q.setParameter("studentName", requiredStudentName);
//        List<Student> allStudents = q.getResultList();
//        for (Student stu: allStudents) {
//            System.out.println(stu);
//        }



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
