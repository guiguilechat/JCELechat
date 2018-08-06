package fr.guiguilechat.jcelechat.model.sde.items.types.effects;

import fr.guiguilechat.jcelechat.model.sde.items.types.Effects;

public class AnimatedLights
    extends Effects
{

    @Override
    public int getGroupId() {
        return  1108;
    }

    @Override
    public Class<?> getGroup() {
        return AnimatedLights.class;
    }
}
