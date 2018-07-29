package fr.guiguilechat.jcelechat.model.sde.items.types.commodity;

import fr.guiguilechat.jcelechat.model.sde.items.types.Commodity;

public class TestCompressedOre
    extends Commodity
{

    @Override
    public int getGroupId() {
        return  884;
    }

    @Override
    public Class<?> getGroup() {
        return TestCompressedOre.class;
    }
}
