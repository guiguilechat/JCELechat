package fr.guiguilechat.jcelechat.model.sde.types;

import java.util.Arrays;
import java.util.Collection;
import fr.guiguilechat.jcelechat.model.sde.EveType;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.types.abstrct.Audio;
import fr.guiguilechat.jcelechat.model.sde.types.abstrct.Decorations;
import fr.guiguilechat.jcelechat.model.sde.types.abstrct.Miscellaneous;

public abstract class Abstrct
    extends EveType
{
    public static final Abstrct.MetaCat METACAT = new Abstrct.MetaCat();

    @Override
    public IMetaCategory<Abstrct> getCategory() {
        return METACAT;
    }

    public static class MetaCat
        implements IMetaCategory<Abstrct>
    {

        @Override
        public int getCategoryId() {
            return  29;
        }

        @Override
        public String getName() {
            return "Abstrct";
        }

        @Override
        public Collection<IMetaGroup<? extends Abstrct>> groups() {
            return Arrays.asList(Decorations.METAGROUP, Audio.METAGROUP, Miscellaneous.METAGROUP);
        }
    }
}
