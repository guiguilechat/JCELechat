package fr.guiguilechat.jcelechat.model.sde.items.types.celestial;

import fr.guiguilechat.jcelechat.model.sde.items.types.Celestial;

public class MoonChunk
    extends Celestial
{

    @Override
    public int getGroupId() {
        return  1940;
    }

    @Override
    public Class<?> getGroup() {
        return MoonChunk.class;
    }
}
