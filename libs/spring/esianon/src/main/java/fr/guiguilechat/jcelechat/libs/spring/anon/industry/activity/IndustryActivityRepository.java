package fr.guiguilechat.jcelechat.libs.spring.anon.industry.activity;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IndustryActivityRepository extends JpaRepository<IndustryActivity, Integer> {

	List<IndustryActivity> findByAliasesContainsIgnoreCase(String match);

}
