package fr.guiguilechat.jcelechat.libs.spring.market.services;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import fr.guiguilechat.jcelechat.libs.spring.market.model.ObservedRegion;
import fr.guiguilechat.jcelechat.libs.spring.market.model.RegionLine;
import fr.guiguilechat.jcelechat.libs.spring.market.repositories.RegionLineRepository;

@Service
public class RegionLineService {

	@Autowired
	private RegionLineRepository repo;

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
		return repo.findByLocationIdAndTypeIdAndIsBuyOrderOrderByPriceAsc(locationId, type_id, isBuyOrder);
	}

	/**
	 * {@link Transactional} to avoid an update altering the lines
	 * returned mid-query
	 *
	 * @return existing lines with given region.regionId and order.type_id , and order.isbuyorder , ordered by price asc
	 */
	@Transactional(isolation = Isolation.SERIALIZABLE, propagation = Propagation.NESTED)
	public List<RegionLine> forRegion(int regionId, int type_id, boolean isBuyOrder) {
		return repo.findByRegionIdAndTypeIdAndIsBuyOrderOrderByPriceAsc(regionId, type_id, isBuyOrder);
	}

	/**
	 * {@link Transactional} to avoid an update altering the lines
	 * returned mid-query
	 *
	 * @return existing lines with given order.type_id , and order.isbuyorder ,
	 *           ordered by price asc
	 */
	@Transactional(isolation = Isolation.SERIALIZABLE, propagation = Propagation.NESTED)
	public List<RegionLine> forAll(int type_id, boolean isBuyOrder) {
		return repo.findByTypeIdAndIsBuyOrderOrderByPriceAsc(type_id, isBuyOrder);
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

	public static record SellStat(long qtty, double price, long cumulQtty, double cumulValue) implements Serializable {
	}

	@Value("${market.sellstats.clipmult:10}")
	private float priceGainClipover;

	List<SellStat> sellGain(List<RegionLine> sos, List<RegionLine> bos) {
		ArrayList<SellStat> ret = new ArrayList<>();
		long cumulQtty = 0;
		double cumulValue = 0.0;
		Double minSOPrice = null;
		for (RegionLine rl : sos) {
			if (minSOPrice == null) {
				minSOPrice = rl.getOrder().price;
			} else if (rl.getOrder().price > minSOPrice * priceGainClipover) {
				break;
			}
			cumulQtty += rl.getOrder().volume_remain;
			cumulValue += rl.getOrder().volume_remain * rl.getOrder().price;
			SellStat add = new SellStat(-rl.getOrder().volume_remain, -rl.getOrder().price, -cumulQtty, cumulValue);
			ret.add(add);
		}
		cumulQtty = 0;
		cumulValue = 0.0;
		Double maxBOPrice = null;
		for (RegionLine rl : bos) {
			if (maxBOPrice == null) {
				maxBOPrice = rl.getOrder().price;
			} else if (rl.getOrder().price < maxBOPrice / priceGainClipover) {
				break;
			}
			cumulQtty += rl.getOrder().volume_remain;
			cumulValue += rl.getOrder().volume_remain * rl.getOrder().price;
			SellStat add = new SellStat(rl.getOrder().volume_remain, rl.getOrder().price, cumulQtty, cumulValue);
			ret.add(add);
		}
		return ret;
	}

	public List<SellStat> sellLocationGain(long locationId, int typeId) {
		List<RegionLine> sos = repo.findByLocationIdAndTypeIdAndIsBuyOrderOrderByPriceAsc(locationId, typeId, false);
		List<RegionLine> bos = new ArrayList<>(
				repo.findByLocationIdAndTypeIdAndIsBuyOrderOrderByPriceAsc(locationId, typeId, true));
		Collections.reverse(bos);
		return sellGain(sos, bos);
	}

	public List<SellStat> sellRegionGain(int regionId, int typeId) {
		List<RegionLine> sos = repo.findByRegionIdAndTypeIdAndIsBuyOrderOrderByPriceAsc(regionId, typeId, false);
		List<RegionLine> bos = new ArrayList<>(
				repo.findByRegionIdAndTypeIdAndIsBuyOrderOrderByPriceAsc(regionId, typeId, true));
		Collections.reverse(bos);
		return sellGain(sos, bos);
	}

	public List<SellStat> sellAllGain(int typeId) {
		List<RegionLine> sos = repo.findByTypeIdAndIsBuyOrderOrderByPriceAsc(typeId, false);
		List<RegionLine> bos = new ArrayList<>(repo.findByTypeIdAndIsBuyOrderOrderByPriceAsc(typeId, true));
		Collections.reverse(bos);
		return sellGain(sos, bos);
	}

}
