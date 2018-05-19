package fr.guiguilechat.eveonline.model.sde.items;

public abstract class IntAttribute
    extends Attribute
{

    @Override
    public Integer value(Item item) {
        return super.value(item).intValue();
    }
}
