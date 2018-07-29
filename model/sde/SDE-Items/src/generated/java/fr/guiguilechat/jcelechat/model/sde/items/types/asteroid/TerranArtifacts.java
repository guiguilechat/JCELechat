package fr.guiguilechat.jcelechat.model.sde.items.types.asteroid;

import fr.guiguilechat.jcelechat.model.sde.items.types.Asteroid;

public class TerranArtifacts
    extends Asteroid
{

    @Override
    public int getGroupId() {
        return  519;
    }

    @Override
    public Class<?> getGroup() {
        return TerranArtifacts.class;
    }
}
