import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

//Required by Hibernate
@Entity
public class ProductTransaction {
    //Required by Hibernate
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int TransactionNumber;
    private int quantity;

    @Override
    public String toString(){
        return "TransactionNumber:"+TransactionNumber+
                ",quantity:"+quantity;
    }

    @ManyToMany(mappedBy="productTransactions",cascade = CascadeType.PERSIST)
    private Set<Product> products = new HashSet<>();

    public Set<Product> getProducts(){
        return products;
    }

    public void addProduct(Product product){
        this.addProduct(product,true);
    }

    public void addProduct(Product product, boolean withAddTrans){
        this.products.add(product);
        if(withAddTrans)
            product.addTransaction(this,false);
    }

    public ProductTransaction(int quantity) {
        this.quantity=quantity;
    }

    public ProductTransaction() {
    }

}