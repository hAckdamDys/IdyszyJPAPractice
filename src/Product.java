import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

//Required by Hibernate
@Entity
public class Product {
    //Required by Hibernate
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int pID;
    private String productName;
    private Integer unitsOnStock;

    @ManyToMany
    private Set<ProductTransaction> productTransactions = new HashSet<>();

    public Set<ProductTransaction> getProductTransactions(){
        return productTransactions;
    }

    public void addTransaction(ProductTransaction productTransaction){
        this.addTransaction(productTransaction,true);
    }

    public void addTransaction(ProductTransaction productTransaction, boolean withAddProd){
        this.productTransactions.add(productTransaction);
        if(withAddProd)
            productTransaction.addProduct(this,false);
    }


    @Override
    public String toString(){
        return "Product ID:"+pID+",name:"+productName+",units:"+unitsOnStock+
                ",category:{"+category+"},supplier:{"+supplier+"}";
    }
    @ManyToOne
    @JoinColumn(name="SUPPLIER_SID")
    private Supplier supplier;

    public void setSupplier(Supplier supplier){
        setSupplier(supplier,true);
    }

    public void setSupplier(Supplier supplier, boolean withAddProduct){
        this.supplier = supplier;
        if(withAddProduct)
            supplier.addProduct(this,false);
    }


    public Product(String productName,int unitsOnStock) {
        this.productName = productName;
        this.unitsOnStock = unitsOnStock;
    }
    public Product(String productName) {
        this.productName = productName;
    }

    public Product() {
    }

//    @ManyToOne(optional = false)
//    private Supplier suppliers;
//
//    public Supplier getSuppliers() {
//        return suppliers;
//    }
//
//    public void setSuppliers(Supplier suppliers) {
//        this.suppliers = suppliers;
//    }
    @ManyToOne
    @JoinColumn(name="CATEGORY_ID")
    private Category category;

    public void setCategory(Category category){
        setCategory(category,true);
    }

    public void setCategory(Category category, boolean withAddProduct){
        this.category=category;
        if(withAddProduct)
            category.addProduct(this,false);
    }
}