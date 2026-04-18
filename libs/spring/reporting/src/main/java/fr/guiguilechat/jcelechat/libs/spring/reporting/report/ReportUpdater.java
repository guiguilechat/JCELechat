package fr.guiguilechat.jcelechat.libs.spring.reporting.report;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Limit;
import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.libs.spring.reporting.report.Report.Status;
import fr.guiguilechat.jcelechat.libs.spring.update.manager.EntityUpdater;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Service
@RequiredArgsConstructor
@Getter
@Setter
public class ReportUpdater implements EntityUpdater {

	private final ReportRepository reportRepository;

	private final Optional<List<ReportGenerator>> generators;

	private final UpdateConfig update = new UpdateConfig();

	private Instant nextPulse = null;

	public List<Report> selectNextProcess(int max) {
		return reportRepository.findByStatusAndTryAfterLowerThanOrderByPriorityDesc(Status.READY, Instant.now(),
				Limit.of(max));
	}

	@Override
	public boolean updatePulse() {
		if (generators == null || generators.isEmpty() || generators.get().isEmpty()) {
			return false;
		}
		List<Report> toBeProcessed = selectNextProcess(getUpdate().getMax());
		if (toBeProcessed.isEmpty()) {
			return false;
		}

		for (Report r : toBeProcessed) {
			boolean processed = false;
			for (ReportGenerator g : generators.get()) {
				if (g.apply(r)) {
					r.setLastTry(Instant.now());
					r.setStatus(Status.DONE);
					processed = true;
					break;
				}
			}
			if (!processed) {
				updateMissingGenerator(r);
			}
		}
		return !selectNextProcess(1).isEmpty();
	}

	protected void updateMissingGenerator(Report r) {
		r.addError("missing generator");
	}

}
