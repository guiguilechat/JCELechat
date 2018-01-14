
package fr.guiguilechat.eveonline.model.sde.compiled.items.module;

import fr.guiguilechat.eveonline.model.sde.compiled.items.Module;

public class ShieldDisruptor
    extends Module
{


    @Override
    public int getGroupId() {
        return  321;
    }

    @Override
    public Class<?> getGroup() {
        return ShieldDisruptor.class;
    }

}
