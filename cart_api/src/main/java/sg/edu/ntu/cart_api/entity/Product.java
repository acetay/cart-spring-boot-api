package sg.edu.ntu.cart_api.entity;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

// Bean
// POJO - Plain Old Java Object
// This class defines a Product entity that is mapped to a database table named "product"
@Entity
@Table(name = "product")
public class Product {

    // Define the primary key for the Product entity and generate its value automatically
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    Integer id = null; 

    // Define a non-null column for the name of the product
    @Column(nullable = false)
    String name;

    // Define a nullable column for the description of the product
    String description;

    // Define a column for the price of the product with a default value of 0
    float price = 0f;

    // Define a column for the creation timestamp of the product that is set when the product is created
    @Column(name="created_at", updatable=false)
    Timestamp createdAt = new Timestamp(new Date().getTime());

    // Getter and Setter 
    public void setId(Integer id) {
        this.id = id;
    }
    public Integer getId(){
        return this.id;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }
    
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public float getPrice() {
        return price;
    }
    public void setPrice(float price) {
        this.price = price;
    }
}
