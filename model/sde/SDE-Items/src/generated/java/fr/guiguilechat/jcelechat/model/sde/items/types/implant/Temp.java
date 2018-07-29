package fr.guiguilechat.jcelechat.model.sde.items.types.implant;

import fr.guiguilechat.jcelechat.model.sde.items.types.Implant;

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
