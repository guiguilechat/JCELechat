package fr.guiguilechat.jcelechat.model.sde.items.types;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.Item;
import fr.guiguilechat.jcelechat.model.sde.items.MetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.MetaGroup;

public abstract class Bonus
    extends Item
{
    public final static Bonus.MetaCat METACAT = new Bonus.MetaCat();

    @Override
    public int getCategoryId() {
        return  14;
    }

    @Override
    public MetaCategory<Bonus> getCategory() {
        return METACAT;
    }

    public static Map<String, ? extends Bonus> loadCategory() {
        return Collections.emptyMap();
    }

    public static class MetaCat
        implements MetaCategory<Bonus>
    {
        @SuppressWarnings("unchecked")
        private final static MetaGroup<? extends Bonus> [] groups = new MetaGroup[] { };

        @Override
        public String getName() {
            return "Bonus";
        }

        public Collection<MetaGroup<? extends Bonus>> groups() {
            return Arrays.asList(groups);
        }
    }
}
