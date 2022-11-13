package fr.guiguilechat.jcelechat.model.sde.types;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Set;
import fr.guiguilechat.jcelechat.model.sde.Attribute;
import fr.guiguilechat.jcelechat.model.sde.EveType;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.types.personalization.ShipPersonalization;

public abstract class Personalization
    extends EveType
{
    public static final Set<Attribute> ATTRIBUTES = Collections.emptySet();
    public static final Personalization.MetaCat METACAT = new Personalization.MetaCat();

    @Override
    public Set<Attribute> getAttributes() {
        return ATTRIBUTES;
    }

    @Override
    public IMetaCategory<Personalization> getCategory() {
        return METACAT;
    }

    public static class MetaCat
        implements IMetaCategory<Personalization>
    {

        @Override
        public int getCategoryId() {
            return  2118;
        }

        @Override
        public String getName() {
            return "Personalization";
        }

        @Override
        public Collection<IMetaGroup<? extends Personalization>> groups() {
            return Arrays.asList(ShipPersonalization.METAGROUP);
        }
    }
}
