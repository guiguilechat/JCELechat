package fr.guiguilechat.jcelechat.libs.spring.trade.contract;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.libs.spring.trade.tools.MarketOrder;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * facade to propose BPO contract history and offers methods.
 */
@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class ContractFacadeBpo {

	@Lazy
	private final ContractInfoRepository contractInfoRepository;

	/**
	 * find contracts that are open and offer a given type and ME/TE
	 * 
	 * @param typeId
	 * @return list of open contracts that provide only given type, with
	 *           iscopy=false and given me and te
	 */
	public List<ContractInfo> selling(int typeId, int me, int te) {
		return contractInfoRepository
		    .findByRemovedFalseAndOffersItemTrueAndRequestsItemFalseAndOfferedTypeIdInAndOfferedCopyAndOfferedMeAndOfferedTe(
		        List.of(typeId),
		        false, me, te);
	}

	public List<ContractOrder> sos(int typeId, int me, int te) {
		return selling(typeId, me, te).stream().map(ContractOrder::new).toList();
	}

	@Transactional
	public Stream<MarketOrder> streamSOs(int typeId, int me, int te) {
		return selling(typeId, me, te).stream().map(MarketOrder::of)
		    .sorted(Comparator.comparing(MarketOrder::getPrice));
	}

	/**
	 * find contracts that sold a given type, with given ME/TE and 0 runs
	 * 
	 * @param typeId
	 * @return list of completed contracts that provide only given type, with
	 *           iscopy=false and given me and te
	 */
	public List<ContractInfo> sold(int typeId, int me, int te) {
		return contractInfoRepository
		    .findByCompletedTrueAndOffersItemTrueAndRequestsItemFalseAndOfferedTypeIdInAndOfferedCopyAndOfferedMeAndOfferedTe(
		        List.of(typeId),
		        false, me, te);
	}

}
