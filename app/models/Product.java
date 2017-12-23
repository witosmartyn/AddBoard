package models;
import io.ebean.Finder;
import io.ebean.Model;

import play.data.validation.Constraints;
import play.data.validation.Constraints.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * User: vitali 24/10/17 21:21
 */
@Entity
public class Product extends Model{
    private static final long serialVersionUID = 1L;

    @Id
    public Long ean;

    @Constraints.Required
    public String name;

    @Constraints.Required
    public String description;

    public static Finder<Long,Product>find = new Finder<>(Product.class);
    public Product() {
    }

    public Product(Long ean, String name, String description) {
        this.ean = ean;
        this.name = name;
        this.description = description;
    }

    @Override
    public String toString() {
        return String.format("%s - %s ", ean, name);
    }
}
