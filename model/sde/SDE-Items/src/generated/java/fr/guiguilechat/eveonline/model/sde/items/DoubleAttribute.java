package fr.guiguilechat.eveonline.model.sde.items;

public abstract class DoubleAttribute
    extends Attribute
{

    @Override
    public Double value(Item item) {
        return super.value(item).doubleValue();
    }
}
