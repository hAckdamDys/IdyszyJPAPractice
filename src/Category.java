import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

//Required by Hibernate
@Entity
public class Category {
    //Required by Hibernate
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int CategoryID;
    private String name;

    @Override
    public String toString(){
        return "Category ID:"+CategoryID+",name:"+name;
    }


    @OneToMany(mappedBy="category")
    //@JoinColumn(name="SUPPLIER_SID")
    private Set<Product> products = new HashSet<>();

    public Set<Product> getProducts(){
        return  products;
    }

    public void addProduct(Product product){
        this.addProduct(product,true);
    }

    public void addProduct(Product product, boolean withSetSupplier){
        this.products.add(product);
        if(withSetSupplier)
            product.setCategory(this,false);
    }

    public Category(String name) {
        this.name=name;
    }

    public Category() {
    }
}