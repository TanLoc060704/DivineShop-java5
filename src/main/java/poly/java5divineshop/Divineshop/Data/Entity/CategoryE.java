package poly.java5divineshop.Divineshop.Data.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "category")
public class CategoryE {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Sys_id_category")
    private int id;

    @Column(name = "ten_the_loai", length = 255)  // Adjust length based on your schema
    private String tenTheLoai;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH}, fetch = FetchType.LAZY)
    @JoinTable(
            name = "category_detail",
            joinColumns = @JoinColumn(name = "Sys_id_category"),
            inverseJoinColumns = @JoinColumn(name = "Sys_id_product")
    )
    private List<ProductE> products = new ArrayList<>();

    public CategoryE(String tenTheLoai) {
        this.tenTheLoai = tenTheLoai;
    }

    public void addProduct(ProductE productE) {
        if (products == null) {
            products = new ArrayList<>();
        }
        products.add(productE);
    }

    public void removeProduct(ProductE productE) {
        if (products != null) {
            products.remove(productE);
        }
    }
}
