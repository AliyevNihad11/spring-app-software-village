package az.softwarevillage.book.repository;

import az.softwarevillage.book.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User,Long> {

    List <User> findAllByStatus(Integer status);
    User findByIdAndStatus(Long id,Integer status);

}
