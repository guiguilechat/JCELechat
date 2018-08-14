package fr.guiguilechat.jcelechat.model.sde.items.types;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
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
        @SuppressWarnings("unchecked")
        private final static IMetaGroup<? extends Reaction> [] groups = new IMetaGroup[] {FreedomPrograms.METAGROUP };

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
            return Arrays.asList(groups);
        }

        @Override
        public Map<String, Reaction> load() {
            HashMap<String, Reaction> ret = new HashMap<>();
            groups().stream().flatMap(img -> img.load().entrySet().stream()).forEach(e -> ret.put(e.getKey(), e.getValue()));
            return ret;
        }
    }
}
