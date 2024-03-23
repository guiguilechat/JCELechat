package fr.guiguilechat.jcelechat.libs.spring.evehistory.industry.services;

import java.util.List;

import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.libs.spring.evehistory.industry.model.IndustrySystemCostIndex;
import fr.guiguilechat.jcelechat.libs.spring.evehistory.industry.repositories.IndustrySystemCostIndexRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class IndustrySystemCostIndexService {

	private final IndustrySystemCostIndexRepository repo;

	public void saveAll(List<IndustrySystemCostIndex> list) {
		repo.saveAll(list);
	}

}
