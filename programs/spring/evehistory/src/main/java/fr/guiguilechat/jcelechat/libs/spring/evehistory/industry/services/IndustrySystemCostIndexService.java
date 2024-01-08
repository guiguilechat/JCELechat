package fr.guiguilechat.jcelechat.libs.spring.evehistory.industry.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.libs.spring.evehistory.industry.model.IndustrySystemCostIndex;
import fr.guiguilechat.jcelechat.libs.spring.evehistory.industry.repositories.IndustrySystemCostIndexRepository;

@Service
public class IndustrySystemCostIndexService {

	@Autowired
	private IndustrySystemCostIndexRepository repo;

	public void saveAll(List<IndustrySystemCostIndex> list) {
		repo.saveAll(list);
	}

}
