package jp.co.internous.ecsite.model.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import jp.co.internous.ecsite.model.entity.User;

public interface UserRepository extends JpaRepository<User,Long> {
	
	List<User> findByUserNameAndPassword(String userName,String password);
	
	@Query(value="SELECT count(user_name) from user u" +
			"where u.user_name = userName",
			nativeQuery=true)
	List<User> countDup(@Param("userName") String userName);
	@Transactional
	@Modifying
	void persist(@Param("userName") String userName);
}
