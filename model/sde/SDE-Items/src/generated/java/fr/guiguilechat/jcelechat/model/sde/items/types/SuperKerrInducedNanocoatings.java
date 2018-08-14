package fr.guiguilechat.jcelechat.model.sde.items.types;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.items.Item;
import fr.guiguilechat.jcelechat.model.sde.items.types.superkerrinducednanocoatings.Max1YearSKIN;
import fr.guiguilechat.jcelechat.model.sde.items.types.superkerrinducednanocoatings.Max30DaySKIN;
import fr.guiguilechat.jcelechat.model.sde.items.types.superkerrinducednanocoatings.Max7DaySKIN;
import fr.guiguilechat.jcelechat.model.sde.items.types.superkerrinducednanocoatings.Max90DaySKIN;
import fr.guiguilechat.jcelechat.model.sde.items.types.superkerrinducednanocoatings.PermanentSKIN;

public abstract class SuperKerrInducedNanocoatings
    extends Item
{
    public final static SuperKerrInducedNanocoatings.MetaCat METACAT = new SuperKerrInducedNanocoatings.MetaCat();

    @Override
    public IMetaCategory<SuperKerrInducedNanocoatings> getCategory() {
        return METACAT;
    }

    public static class MetaCat
        implements IMetaCategory<SuperKerrInducedNanocoatings>
    {
        @SuppressWarnings("unchecked")
        private final static IMetaGroup<? extends SuperKerrInducedNanocoatings> [] groups = new IMetaGroup[] {PermanentSKIN.METAGROUP, Max7DaySKIN.METAGROUP, Max30DaySKIN.METAGROUP, Max90DaySKIN.METAGROUP, Max1YearSKIN.METAGROUP };

        @Override
        public int getCategoryId() {
            return  91;
        }

        @Override
        public String getName() {
            return "SuperKerrInducedNanocoatings";
        }

        @Override
        public Collection<IMetaGroup<? extends SuperKerrInducedNanocoatings>> groups() {
            return Arrays.asList(groups);
        }

        @Override
        public Map<String, SuperKerrInducedNanocoatings> load() {
            HashMap<String, SuperKerrInducedNanocoatings> ret = new HashMap<>();
            groups().stream().flatMap(img -> img.load().entrySet().stream()).forEach(e -> ret.put(e.getKey(), e.getValue()));
            return ret;
        }
    }
}
