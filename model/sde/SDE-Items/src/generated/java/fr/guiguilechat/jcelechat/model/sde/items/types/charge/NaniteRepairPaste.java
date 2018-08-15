package fr.guiguilechat.jcelechat.model.sde.items.types.charge;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.Attribute;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.items.annotations.DefaultIntValue;
import fr.guiguilechat.jcelechat.model.sde.items.annotations.HighIsGood;
import fr.guiguilechat.jcelechat.model.sde.items.annotations.Stackable;
import fr.guiguilechat.jcelechat.model.sde.items.types.Charge;
import org.yaml.snakeyaml.Yaml;

public class NaniteRepairPaste
    extends Charge
{
    /**
     * The maximum hitpoints of an object.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int Hp;
    public final static NaniteRepairPaste.MetaGroup METAGROUP = new NaniteRepairPaste.MetaGroup();

    @Override
    public Number attribute(Attribute attribute) {
        switch (attribute.getId()) {
            case  9 :
            {
                return Hp;
            }
            default:
            {
                return super.attribute((attribute));
            }
        }
    }

    @Override
    public IMetaGroup<NaniteRepairPaste> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<NaniteRepairPaste>
    {
        public final static String RESOURCE_PATH = "SDE/items/charge/NaniteRepairPaste.yaml";
        private Map<String, NaniteRepairPaste> cache = (null);

        @Override
        public IMetaCategory<? super NaniteRepairPaste> category() {
            return Charge.METACAT;
        }

        @Override
        public int getGroupId() {
            return  916;
        }

        @Override
        public String getName() {
            return "NaniteRepairPaste";
        }

        @Override
        public synchronized Map<String, NaniteRepairPaste> load() {
            if (cache == null) {
                try {
                    cache = new Yaml().loadAs(new InputStreamReader(NaniteRepairPaste.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, NaniteRepairPaste> items;
        }
    }
}