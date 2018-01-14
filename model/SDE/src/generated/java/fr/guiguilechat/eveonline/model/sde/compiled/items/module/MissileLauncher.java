
package fr.guiguilechat.eveonline.model.sde.compiled.items.module;

import fr.guiguilechat.eveonline.model.sde.compiled.items.Module;

public class MissileLauncher
    extends Module
{


    @Override
    public int getGroupId() {
        return  56;
    }

    @Override
    public Class<?> getGroup() {
        return MissileLauncher.class;
    }

}
