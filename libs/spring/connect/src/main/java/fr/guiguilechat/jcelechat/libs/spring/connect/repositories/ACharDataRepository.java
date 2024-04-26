package fr.guiguilechat.jcelechat.libs.spring.connect.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import fr.guiguilechat.jcelechat.libs.spring.connect.model.ACharData;

@NoRepositoryBean
public interface ACharDataRepository<E extends ACharData<?>> extends JpaRepository<E, Integer> {


}
