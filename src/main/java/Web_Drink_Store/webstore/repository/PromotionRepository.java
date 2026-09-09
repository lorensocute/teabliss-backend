package Web_Drink_Store.webstore.repository;
import Web_Drink_Store.webstore.entity.Promotion; import org.springframework.data.jpa.repository.JpaRepository; import java.util.Optional;
public interface PromotionRepository extends JpaRepository<Promotion,Long>{ Optional<Promotion> findByCode(String code); boolean existsByCode(String code); }
