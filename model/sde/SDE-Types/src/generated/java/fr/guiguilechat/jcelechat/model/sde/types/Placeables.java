package fr.guiguilechat.jcelechat.model.sde.types;

import java.util.Arrays;
import java.util.Collection;
import fr.guiguilechat.jcelechat.model.sde.EveType;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;

public abstract class Placeables
    extends EveType
{
    public static final Placeables.MetaCat METACAT = new Placeables.MetaCat();

    @Override
    public IMetaCategory<Placeables> getCategory() {
        return METACAT;
    }

    public static class MetaCat
        implements IMetaCategory<Placeables>
    {

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
            return Arrays.asList();
        }
    }
}
