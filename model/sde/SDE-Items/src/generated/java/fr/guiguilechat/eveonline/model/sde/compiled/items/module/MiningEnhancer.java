
package fr.guiguilechat.eveonline.model.sde.compiled.items.module;

import fr.guiguilechat.eveonline.model.sde.compiled.items.Module;

public class MiningEnhancer
    extends Module
{


    @Override
    public int getGroupId() {
        return  901;
    }

    @Override
    public Class<?> getGroup() {
        return MiningEnhancer.class;
    }

}
