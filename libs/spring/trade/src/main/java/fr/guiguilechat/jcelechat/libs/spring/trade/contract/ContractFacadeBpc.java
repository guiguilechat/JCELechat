package fr.guiguilechat.jcelechat.libs.spring.trade.contract;

import java.util.List;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * facade to propose BPC contract history and offers methods. For BPC, the unit
 * is not the number of items sold, but the total runs
 */
@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class ContractFacadeBpc {

	@Lazy
	private final ContractInfoRepository contractInfoRepository;

	/**
	 * find contracts that are open and offer a given type and ME/TE copy
	 * 
	 * @param typeId
	 * @return list of open contracts that provide only given type, with
	 *           iscopy=true and given me and te
	 */
	public List<ContractInfo> selling(int typeId, int me, int te) {
		return contractInfoRepository
		    .findByCompletedTrueAndOffersOneTypeForIskTrueAndOfferedTypeIdInAndOfferedCopyAndOfferedMeAndOfferedTe(
		        List.of(typeId),
		        true, me, te);
	}

	public List<ContractOrder> sos(int typeId, int me, int te) {
		return selling(typeId, me, te).stream().map(ContractOrder::new).toList();
	}

	/**
	 * find contracts that sold a given type, with given ME/TE and iscopy=true
	 * 
	 * @param typeId
	 * @return list of completed contracts that provide only given type, with
	 *           iscopy=true and given me and te
	 */
	public List<ContractInfo> sold(int typeId, int me, int te) {
		return contractInfoRepository
		    .findByCompletedTrueAndOffersOneTypeForIskTrueAndOfferedTypeIdInAndOfferedCopyAndOfferedMeAndOfferedTe(
		        List.of(typeId),
		        true, me, te);
	}

}
