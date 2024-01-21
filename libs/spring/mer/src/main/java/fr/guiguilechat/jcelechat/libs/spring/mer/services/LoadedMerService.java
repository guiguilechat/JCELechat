package fr.guiguilechat.jcelechat.libs.spring.mer.services;

import java.time.LocalDate;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.libs.spring.mer.model.LoadedMer;
import fr.guiguilechat.jcelechat.libs.spring.mer.repositories.LoadedMerRepository;

@Service
public class LoadedMerService {

	@Autowired
	private LoadedMerRepository repo;

	public void saveAll(Iterable<LoadedMer> entities) {
		repo.saveAll(entities);
	}

	public LoadedMer save(LoadedMer entity) {
		return repo.save(entity);
	}

	public Set<LocalDate> loadedDates() {
		return repo.findAll().stream().map(LoadedMer::getPeriodMonth).collect(Collectors.toSet());
	}


}
