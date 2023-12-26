package fr.guiguilechat.jcelechat.libs.spring.evehistory.services.industry;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.libs.spring.evehistory.model.industry.IndustrySystemCostIndex;
import fr.guiguilechat.jcelechat.libs.spring.evehistory.repositories.industry.IndustrySystemCostIndexRepository;

@Service
public class IndustrySystemCostIndexService {

	@Autowired
	private IndustrySystemCostIndexRepository repo;

	public void saveAll(List<IndustrySystemCostIndex> list) {
		repo.saveAll(list);
	}

}
