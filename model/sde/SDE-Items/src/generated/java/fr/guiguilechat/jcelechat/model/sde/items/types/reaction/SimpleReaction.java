package fr.guiguilechat.jcelechat.model.sde.items.types.reaction;

import fr.guiguilechat.jcelechat.model.sde.items.types.Reaction;

public class SimpleReaction
    extends Reaction
{

    @Override
    public int getGroupId() {
        return  436;
    }

    @Override
    public Class<?> getGroup() {
        return SimpleReaction.class;
    }
}
