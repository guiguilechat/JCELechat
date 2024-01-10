package fr.guiguilechat.jcelechat.libs.spring.market.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.libs.spring.market.model.Line;
import fr.guiguilechat.jcelechat.libs.spring.market.model.ObservedRegion;
import fr.guiguilechat.jcelechat.libs.spring.market.repositories.LineRepository;

@Service
public class LineService {

	@Autowired
	private LineRepository repo;

	public Line save(Line entity) {
		entity.affectFields();
		return repo.save(entity);
	}

	public List<Line> saveAll(Iterable<Line> entities) {
		for (Line entity : entities) {
			entity.affectFields();
		}
		return repo.saveAll(entities);
	}

	public void clearRegion(ObservedRegion region) {
		repo.deleteByRegion(region);
	}

	static final long JITAIV_ID = 60003760;

	/**
	 * @return existing lines for those parameters, ordered by price asc
	 */
	public List<Line> forJita(int type_id, boolean isBuyOrder) {
		return forLocation(JITAIV_ID, type_id, isBuyOrder);
	}

	/**
	 * @return existing lines for those parameters, ordered by price asc
	 */
	public List<Line> forLocation(long locationId, int type_id, boolean isBuyOrder) {
		return repo.findByLocationIdAndTypeIdAndIsBuyOrder(locationId, type_id, isBuyOrder);
	}

	/**
	 * @return existing lines for those parameters, ordered by price asc
	 */
	public List<Line> forRegion(int regionId, int type_id, boolean isBuyOrder) {
		return repo.findByRegionIdAndTypeIdAndIsBuyOrder(regionId, type_id, isBuyOrder);
	}

}
