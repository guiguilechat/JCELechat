package fr.guiguilechat.jcelechat.model.sde.items.types.reaction;

import fr.guiguilechat.jcelechat.model.sde.items.types.Reaction;

public class ComplexBiochemicalReactions
    extends Reaction
{

    @Override
    public int getGroupId() {
        return  662;
    }

    @Override
    public Class<?> getGroup() {
        return ComplexBiochemicalReactions.class;
    }
}
