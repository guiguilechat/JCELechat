package fr.guiguilechat.jcelechat.model.sde.items.types.lights;

import fr.guiguilechat.jcelechat.model.sde.items.types.Lights;

public class BoxLights
    extends Lights
{

    @Override
    public int getGroupId() {
        return  1111;
    }

    @Override
    public Class<?> getGroup() {
        return BoxLights.class;
    }
}
