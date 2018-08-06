package fr.guiguilechat.jcelechat.model.sde.items.types.orbitals;

import fr.guiguilechat.jcelechat.model.sde.items.types.Orbitals;

public class TestOrbitals
    extends Orbitals
{

    @Override
    public int getGroupId() {
        return  1073;
    }

    @Override
    public Class<?> getGroup() {
        return TestOrbitals.class;
    }
}
