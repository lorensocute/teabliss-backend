package Web_Drink_Store.webstore.repository;
import Web_Drink_Store.webstore.entity.Address; import org.springframework.data.jpa.repository.JpaRepository; import java.util.List;
public interface AddressRepository extends JpaRepository<Address,Long>{ List<Address> findByUserId(Long userId); }
