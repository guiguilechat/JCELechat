package fr.guiguilechat.jcelechat.libs.spring.connect.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.guiguilechat.jcelechat.libs.spring.connect.model.UserConnection;



public interface UserConnectionRepository extends JpaRepository<UserConnection, Long> {

}
