package fr.guiguilechat.jcelechat.model.sde.items.types;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.Item;
import fr.guiguilechat.jcelechat.model.sde.items.MetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.MetaGroup;

public abstract class Placeables
    extends Item
{
    public final static Placeables.MetaCat METACAT = new Placeables.MetaCat();

    @Override
    public int getCategoryId() {
        return  49;
    }

    @Override
    public MetaCategory<Placeables> getCategory() {
        return METACAT;
    }

    public static Map<String, ? extends Placeables> loadCategory() {
        return Collections.emptyMap();
    }

    public static class MetaCat
        implements MetaCategory<Placeables>
    {
        @SuppressWarnings("unchecked")
        private final static MetaGroup<? extends Placeables> [] groups = new MetaGroup[] { };

        @Override
        public String getName() {
            return "Placeables";
        }

        public Collection<MetaGroup<? extends Placeables>> groups() {
            return Arrays.asList(groups);
        }
    }
}
