package fr.guiguilechat.jcelechat.model.sde.items.types.material;

import fr.guiguilechat.jcelechat.model.sde.items.types.Material;

public class GasIsotopes
    extends Material
{

    @Override
    public int getGroupId() {
        return  422;
    }

    @Override
    public Class<?> getGroup() {
        return GasIsotopes.class;
    }
}
