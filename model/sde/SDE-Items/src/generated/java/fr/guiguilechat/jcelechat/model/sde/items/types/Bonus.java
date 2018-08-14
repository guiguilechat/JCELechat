package fr.guiguilechat.jcelechat.model.sde.items.types;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.items.Item;

public abstract class Bonus
    extends Item
{
    public final static Bonus.MetaCat METACAT = new Bonus.MetaCat();

    @Override
    public IMetaCategory<Bonus> getCategory() {
        return METACAT;
    }

    public static class MetaCat
        implements IMetaCategory<Bonus>
    {
        @SuppressWarnings("unchecked")
        private final static IMetaGroup<? extends Bonus> [] groups = new IMetaGroup[] { };

        @Override
        public int getCategoryId() {
            return  14;
        }

        @Override
        public String getName() {
            return "Bonus";
        }

        @Override
        public Collection<IMetaGroup<? extends Bonus>> groups() {
            return Arrays.asList(groups);
        }

        @Override
        public Map<String, Bonus> load() {
            HashMap<String, Bonus> ret = new HashMap<>();
            groups().stream().flatMap(img -> img.load().entrySet().stream()).forEach(e -> ret.put(e.getKey(), e.getValue()));
            return ret;
        }
    }
}
