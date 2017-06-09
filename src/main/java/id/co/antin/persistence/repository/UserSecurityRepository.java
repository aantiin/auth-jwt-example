package id.co.antin.persistence.repository;

import id.co.antin.persistence.domain.UserSecurity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by antin on 4/14/17.
 */
public interface UserSecurityRepository extends JpaRepository<UserSecurity, Long> {
    public UserSecurity findByUsername(String username);
}
