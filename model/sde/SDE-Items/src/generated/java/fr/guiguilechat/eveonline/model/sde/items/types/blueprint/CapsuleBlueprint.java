
package fr.guiguilechat.eveonline.model.sde.items.types.blueprint;

import fr.guiguilechat.eveonline.model.sde.items.types.Blueprint;

public class CapsuleBlueprint
    extends Blueprint
{


    @Override
    public int getGroupId() {
        return  109;
    }

    @Override
    public Class<?> getGroup() {
        return CapsuleBlueprint.class;
    }

}
