package fr.guiguilechat.jcelechat.model.sde.items.types.orbitals;

import fr.guiguilechat.jcelechat.model.sde.items.types.Orbitals;

public class OrbitalInfrastructure
    extends Orbitals
{

    @Override
    public int getGroupId() {
        return  1025;
    }

    @Override
    public Class<?> getGroup() {
        return OrbitalInfrastructure.class;
    }
}
