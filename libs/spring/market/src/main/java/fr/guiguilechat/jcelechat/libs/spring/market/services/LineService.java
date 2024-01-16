package fr.guiguilechat.jcelechat.libs.spring.market.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import fr.guiguilechat.jcelechat.libs.spring.market.model.RegionLine;
import fr.guiguilechat.jcelechat.libs.spring.market.model.ObservedRegion;
import fr.guiguilechat.jcelechat.libs.spring.market.repositories.LineRepository;

@Service
public class LineService {

	@Autowired
	private LineRepository repo;

	public RegionLine save(RegionLine entity) {
		entity.affectFields();
		return repo.save(entity);
	}

	public List<RegionLine> saveAll(Iterable<RegionLine> entities) {
		for (RegionLine entity : entities) {
			entity.affectFields();
		}
		return repo.saveAll(entities);
	}

	public void clearRegion(ObservedRegion region) {
		repo.deleteByRegion(region);
	}

	/** common value to get to get Jita specific orders */
	public static final long JITAIV_ID = 60003760;


	/**
	 * {@link Transactional} to avoid an update altering the lines
	 * returned mid-query
	 *
	 * @return existing lines with given order.locationId , order.type_id , and order.isbuyorder , ordered by price asc
	 */
	@Transactional(isolation = Isolation.SERIALIZABLE, propagation = Propagation.NESTED)
	public List<RegionLine> forLocation(long locationId, int type_id, boolean isBuyOrder) {
		return repo.findByLocationIdAndTypeIdAndIsBuyOrder(locationId, type_id, isBuyOrder);
	}

	/**
	 * {@link Transactional} to avoid an update altering the lines
	 * returned mid-query
	 *
	 * @return existing lines with given region.regionId and order.type_id , and order.isbuyorder , ordered by price asc
	 */
	@Transactional(isolation = Isolation.SERIALIZABLE, propagation = Propagation.NESTED)
	public List<RegionLine> forRegion(int regionId, int type_id, boolean isBuyOrder) {
		return repo.findByRegionIdAndTypeIdAndIsBuyOrder(regionId, type_id, isBuyOrder);
	}

	record OfferLocation(int regionId, long locationID, int typeId, double bestPrice) {

	}

	public List<OfferLocation> sellLocations(int typeId) {
		return repo.findSellOfferLocations(typeId).stream()
				.map(arr -> new OfferLocation((int) arr[0], (long) arr[1], typeId, (double) arr[2])).toList();
	}

	public List<OfferLocation> buyLocations(int typeId) {
		return repo.findBuyOfferLocations(typeId).stream()
				.map(arr -> new OfferLocation((int) arr[0], (long) arr[1], typeId, (double) arr[2])).toList();
	}

}
