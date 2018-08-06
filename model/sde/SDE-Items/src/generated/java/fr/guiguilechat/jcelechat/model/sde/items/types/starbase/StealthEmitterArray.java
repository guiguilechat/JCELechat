package fr.guiguilechat.jcelechat.model.sde.items.types.starbase;

import fr.guiguilechat.jcelechat.model.sde.items.types.Starbase;

public class StealthEmitterArray
    extends Starbase
{

    @Override
    public int getGroupId() {
        return  480;
    }

    @Override
    public Class<?> getGroup() {
        return StealthEmitterArray.class;
    }
}
