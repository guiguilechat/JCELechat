package fr.guiguilechat.jcelechat.model.sde.items.types.starbase;

import fr.guiguilechat.jcelechat.model.sde.items.types.Starbase;

public class ForceFieldArray
    extends Starbase
{

    @Override
    public int getGroupId() {
        return  445;
    }

    @Override
    public Class<?> getGroup() {
        return ForceFieldArray.class;
    }
}
