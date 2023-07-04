package fr.guiguilechat.jcelechat.model.sde;

public abstract class RealAttribute
extends Attribute {

	@Override
	public Double value(EveType Type) {
		Number num = super.value(Type);
		return num == null ? null : num.doubleValue();
	}
}
