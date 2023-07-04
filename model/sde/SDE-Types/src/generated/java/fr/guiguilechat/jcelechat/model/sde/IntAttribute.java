package fr.guiguilechat.jcelechat.model.sde;

public abstract class IntAttribute
extends Attribute {

	@Override
	public Integer value(EveType Type) {
		Number num = super.value(Type);
		return num == null ? null : num.intValue();
	}
}
