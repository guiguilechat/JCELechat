package fr.guiguilechat.jcelechat.libs.spring.trade.contract;

import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.libs.spring.trade.tools.MarketOrder;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * facade to propose non-bp contract history and offers methods.
 * Actually also handles BPO 0/0 (unresearched)
 */
@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class ContractFacadeNonBp {

	@Lazy
	private final ContractInfoRepository contractInfoRepository;

	/**
	 * find contracts that are open and offer a given type, with 0 ME/TE/runs
	 * 
	 * @param typeId
	 * @return list of open contracts that provide only given type with (0,0,false)
	 *           for (me, te, iscopy) value
	 */
	public List<ContractInfo> selling(Collection<Integer> typeIds) {
		return contractInfoRepository
		    .findByRemovedFalseAndOffersOneTypeForIskTrueAndOfferedTypeIdInAndOfferedCopyAndOfferedMeAndOfferedTe(
		        (typeIds),
		        false, 0, 0);
	}

	public List<ContractOrder> sos(int typeId) {
		return selling(List.of(typeId)).stream().map(ContractOrder::new).toList();
	}

	@Transactional
	public Stream<MarketOrder> streamSOs(Collection<Integer> typeIds) {
		return selling(typeIds).stream().map(MarketOrder::of);
	}

	/**
	 * find contracts that sold a given type, with 0 ME/TE/runs
	 * 
	 * @param typeId
	 * @return list of completed contracts that provide only given type with
	 *           (0,0,false) for (me, te, iscopy) value
	 */
	public List<ContractInfo> sold(Collection<Integer> typeIds) {
		return contractInfoRepository
		    .findByCompletedTrueAndOffersOneTypeForIskTrueAndOfferedTypeIdInAndOfferedCopyAndOfferedMeAndOfferedTe(
		        (typeIds),
		        false, 0, 0);
	}

	/**
	 * find contracts that are open and request a given type for isk
	 * 
	 * @param typeIds allowed types requested
	 * @return list of open contracts that request only one type among those given
	 *           and provide no item.
	 */
	public List<ContractInfo> buying(Collection<Integer> typeIds) {
		return contractInfoRepository
		    .findByRemovedFalseAndAsksOneTypeForIskTrueAndAskedTypeIdIn(typeIds);
	}

	@Transactional
	public Stream<MarketOrder> streamBOs(Collection<Integer> typeIds) {
		return buying(typeIds).stream().map(MarketOrder::of);
	}

}
