package fr.guiguilechat.jcelechat.model.sde;

public abstract class IntAttribute
    extends Attribute
{

    @Override
    public Integer value(EveType Type) {
        return super.value(Type).intValue();
    }
}
