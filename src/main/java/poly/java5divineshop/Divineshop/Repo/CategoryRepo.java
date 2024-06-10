package poly.java5divineshop.Divineshop.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import poly.java5divineshop.Divineshop.Data.Entity.CategoryE;

import java.util.Optional;

@Repository
public interface CategoryRepo extends JpaRepository<CategoryE, Integer> {
    Optional<CategoryE> findByTenTheLoai(String tenTheLoai);
}
