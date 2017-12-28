import javax.persistence.*;

@Embeddable
public class Address {
    private String street;
    private String city;
    private String zipCode;

    public Address(String street,String city,String zipCode){
        this.street=street;
        this.city=city;
        this.zipCode=zipCode;
    }

    public Address(){

    }

    @Override
    public String toString(){
        return "street:"+street+",city"+city+",zipCode"+zipCode;
    }

}
