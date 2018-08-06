package fr.guiguilechat.jcelechat.model.sde.items.types.asteroid;

import fr.guiguilechat.jcelechat.model.sde.items.types.Asteroid;

public class EmpireAsteroids
    extends Asteroid
{

    @Override
    public int getGroupId() {
        return  1911;
    }

    @Override
    public Class<?> getGroup() {
        return EmpireAsteroids.class;
    }
}
