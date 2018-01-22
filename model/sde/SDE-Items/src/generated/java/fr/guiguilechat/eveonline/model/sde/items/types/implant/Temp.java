package fr.guiguilechat.eveonline.model.sde.items.types.implant;

import fr.guiguilechat.eveonline.model.sde.items.types.Implant;

public class Temp
    extends Implant
{

    @Override
    public int getGroupId() {
        return  721;
    }

    @Override
    public Class<?> getGroup() {
        return Temp.class;
    }
}
