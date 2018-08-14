package fr.guiguilechat.jcelechat.model.sde.items.types;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.items.Item;

public abstract class Placeables
    extends Item
{
    public final static Placeables.MetaCat METACAT = new Placeables.MetaCat();

    @Override
    public IMetaCategory<Placeables> getCategory() {
        return METACAT;
    }

    public static class MetaCat
        implements IMetaCategory<Placeables>
    {
        @SuppressWarnings("unchecked")
        private final static IMetaGroup<? extends Placeables> [] groups = new IMetaGroup[] { };

        @Override
        public int getCategoryId() {
            return  49;
        }

        @Override
        public String getName() {
            return "Placeables";
        }

        @Override
        public Collection<IMetaGroup<? extends Placeables>> groups() {
            return Arrays.asList(groups);
        }

        @Override
        public Map<String, Placeables> load() {
            HashMap<String, Placeables> ret = new HashMap<>();
            groups().stream().flatMap(img -> img.load().entrySet().stream()).forEach(e -> ret.put(e.getKey(), e.getValue()));
            return ret;
        }
    }
}
