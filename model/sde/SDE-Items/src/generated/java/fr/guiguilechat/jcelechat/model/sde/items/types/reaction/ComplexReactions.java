package fr.guiguilechat.jcelechat.model.sde.items.types.reaction;

import fr.guiguilechat.jcelechat.model.sde.items.types.Reaction;

public class ComplexReactions
    extends Reaction
{

    @Override
    public int getGroupId() {
        return  484;
    }

    @Override
    public Class<?> getGroup() {
        return ComplexReactions.class;
    }
}
