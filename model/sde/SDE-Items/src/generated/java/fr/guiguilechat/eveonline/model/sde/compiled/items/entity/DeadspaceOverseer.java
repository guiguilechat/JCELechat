
package fr.guiguilechat.eveonline.model.sde.compiled.items.entity;

import fr.guiguilechat.eveonline.model.sde.compiled.items.Entity;

public class DeadspaceOverseer
    extends Entity
{


    @Override
    public int getGroupId() {
        return  435;
    }

    @Override
    public Class<?> getGroup() {
        return DeadspaceOverseer.class;
    }

}
