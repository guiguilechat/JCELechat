package fr.guiguilechat.jcelechat.model.sde.items.types;

import java.util.Arrays;
import java.util.Collection;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.items.Item;
import fr.guiguilechat.jcelechat.model.sde.items.types.reaction.FreedomPrograms;

public abstract class Reaction
    extends Item
{
    public final static Reaction.MetaCat METACAT = new Reaction.MetaCat();

    @Override
    public IMetaCategory<Reaction> getCategory() {
        return METACAT;
    }

    public static class MetaCat
        implements IMetaCategory<Reaction>
    {

        @Override
        public int getCategoryId() {
            return  24;
        }

        @Override
        public String getName() {
            return "Reaction";
        }

        @Override
        public Collection<IMetaGroup<? extends Reaction>> groups() {
            return Arrays.asList(FreedomPrograms.METAGROUP);
        }
    }
}
