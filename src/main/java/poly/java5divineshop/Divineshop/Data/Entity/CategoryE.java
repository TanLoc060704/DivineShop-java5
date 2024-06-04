package poly.java5divineshop.Divineshop.Data.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "Category")
public class CategoryE {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Sys_id_category")
    private int id;

    @Column(name = "ten_the_loai", columnDefinition = "nvarchar(max)")
    private String tenTheLoai;

//    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH}, fetch = FetchType.LAZY)
//    @JoinTable(name = "CategoryDetail",
//            joinColumns = @JoinColumn(name = "Sys_id_category"),
//            inverseJoinColumns = @JoinColumn(name = "Sys_id_product"))
//    private List<ProductE> products;
//
//
//    public void addProductList(ProductE productE) {
//        if (products == null) {
//            products = new ArrayList<>();
//        }
//        products.add(productE);
//    }


    public CategoryE(String tenTheLoai) {
        this.tenTheLoai = tenTheLoai;
    }
}
