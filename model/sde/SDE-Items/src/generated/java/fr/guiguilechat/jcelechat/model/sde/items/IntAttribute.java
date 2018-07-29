package fr.guiguilechat.jcelechat.model.sde.items;

public abstract class IntAttribute
    extends Attribute
{

    @Override
    public Integer value(Item item) {
        return super.value(item).intValue();
    }
}
