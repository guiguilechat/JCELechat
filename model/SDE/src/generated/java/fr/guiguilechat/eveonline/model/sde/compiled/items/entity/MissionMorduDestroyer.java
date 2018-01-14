
package fr.guiguilechat.eveonline.model.sde.compiled.items.entity;

import fr.guiguilechat.eveonline.model.sde.compiled.items.Entity;

public class MissionMorduDestroyer
    extends Entity
{


    @Override
    public int getGroupId() {
        return  700;
    }

    @Override
    public Class<?> getGroup() {
        return MissionMorduDestroyer.class;
    }

}
