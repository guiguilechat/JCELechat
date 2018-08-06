package fr.guiguilechat.jcelechat.model.sde.items.types.starbase;

import fr.guiguilechat.jcelechat.model.sde.items.types.Starbase;

public class TargetPaintingBattery
    extends Starbase
{

    @Override
    public int getGroupId() {
        return  877;
    }

    @Override
    public Class<?> getGroup() {
        return TargetPaintingBattery.class;
    }
}
