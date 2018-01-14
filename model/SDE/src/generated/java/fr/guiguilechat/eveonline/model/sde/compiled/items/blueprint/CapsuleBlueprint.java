
package fr.guiguilechat.eveonline.model.sde.compiled.items.blueprint;

import fr.guiguilechat.eveonline.model.sde.compiled.items.Blueprint;

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
