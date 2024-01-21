package fr.guiguilechat.jcelechat.libs.spring.mer.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.guiguilechat.jcelechat.libs.spring.mer.model.LoadedMer;

public interface LoadedMerRepository extends JpaRepository<LoadedMer, Long> {

}
