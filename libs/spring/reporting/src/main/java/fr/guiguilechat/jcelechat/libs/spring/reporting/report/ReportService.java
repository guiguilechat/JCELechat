package fr.guiguilechat.jcelechat.libs.spring.reporting.report;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReportService {

	private final ReportRepository repo;

	public void save(Report entity) {
		repo.save(entity);
	}
}
