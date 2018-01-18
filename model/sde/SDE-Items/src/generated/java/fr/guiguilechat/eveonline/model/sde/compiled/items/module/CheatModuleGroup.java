
package fr.guiguilechat.eveonline.model.sde.compiled.items.module;

import fr.guiguilechat.eveonline.model.sde.compiled.items.Module;

public class CheatModuleGroup
    extends Module
{


    @Override
    public int getGroupId() {
        return  225;
    }

    @Override
    public Class<?> getGroup() {
        return CheatModuleGroup.class;
    }

}
