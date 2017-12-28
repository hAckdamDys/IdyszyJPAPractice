import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Adm on 2017-12-13.
 */
@Entity
public class Supplier {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int sID;
    String companyName;

    @Embedded
    @AttributeOverrides(value = {
            @AttributeOverride(name = "zipCode", column = @Column(length = 10)),
            @AttributeOverride(name = "city", column = @Column(nullable = false))
    })
    private Address address;

    @Override
    public String toString(){
        return "Supplier ID:"+sID+",company:"+companyName+",address:{"+address+"}";
    }

    @OneToMany(mappedBy="supplier")
    //@JoinColumn(name="SUPPLIER_SID")
    private Set<Product> products = new HashSet<>();

    public Set<Product> getProducts(){
        return products;
    }

    public void addProduct(Product product){
        this.addProduct(product,true);
    }

    public void addProduct(Product product, boolean withSetSupplier){
        this.products.add(product);
        if(withSetSupplier)
            product.setSupplier(this,false);
    }


    public Supplier(String companyName){
        this.companyName = companyName;
    }
    public Supplier(String cName,Address address){
        this.address=address;
        this.companyName=cName;
    }
    public Supplier() {
    }
}
