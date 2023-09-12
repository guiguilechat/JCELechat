package fr.guiguilechat.jcelechat.model.sde;

public abstract class RealAttribute
    extends Attribute
{

    @Override
    public Double value(EveType Type) {
        return super.value(Type).doubleValue();
    }
}
