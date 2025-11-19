package fr.guiguilechat.jcelechat.model.sde.hierarchy;

import java.math.BigDecimal;
import java.util.HashMap;

public class TypeDetails extends CommonDetails {

	public BigDecimal basePrice;

	public HashMap<Integer, BigDecimal> definition = new HashMap<>();

	public int groupID;

	public int marketGroupID;

	public BigDecimal mass;

	public BigDecimal packagedVolume;

	public int portionSize;

	public BigDecimal radius;

	public BigDecimal volume;

}
