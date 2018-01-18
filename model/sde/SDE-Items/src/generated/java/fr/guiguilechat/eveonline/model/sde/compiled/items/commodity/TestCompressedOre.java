
package fr.guiguilechat.eveonline.model.sde.compiled.items.commodity;

import fr.guiguilechat.eveonline.model.sde.compiled.items.Commodity;

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
