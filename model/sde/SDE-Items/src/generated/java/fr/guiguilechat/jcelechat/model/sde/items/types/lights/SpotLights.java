package fr.guiguilechat.jcelechat.model.sde.items.types.lights;

import fr.guiguilechat.jcelechat.model.sde.items.types.Lights;

public class SpotLights
    extends Lights
{

    @Override
    public int getGroupId() {
        return  1112;
    }

    @Override
    public Class<?> getGroup() {
        return SpotLights.class;
    }
}
