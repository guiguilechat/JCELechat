package fr.guiguilechat.jcelechat.model.sde.items.types.commodity;

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
import fr.guiguilechat.jcelechat.model.sde.items.types.Commodity;
import org.yaml.snakeyaml.Yaml;

public class DataInterfaces
    extends Commodity
{
    /**
     * Used to show usable decryptors when starting reverse engineering based on data interface
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int DecryptorID;
    /**
     * The maximum hitpoints of an object.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int Hp;
    /**
     * The type ID of the skill that is required.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int RequiredSkill1;
    /**
     * Required skill level for skill 1
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int RequiredSkill1Level;
    public static final DataInterfaces.MetaGroup METAGROUP = new DataInterfaces.MetaGroup();

    @Override
    public Number attribute(Attribute attribute) {
        switch (attribute.getId()) {
            case  1115 :
            {
                return DecryptorID;
            }
            case  9 :
            {
                return Hp;
            }
            case  182 :
            {
                return RequiredSkill1;
            }
            case  277 :
            {
                return RequiredSkill1Level;
            }
            default:
            {
                return super.attribute((attribute));
            }
        }
    }

    @Override
    public IMetaGroup<DataInterfaces> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<DataInterfaces>
    {
        public static final String RESOURCE_PATH = "SDE/items/commodity/DataInterfaces.yaml";
        private Map<String, DataInterfaces> cache = (null);

        @Override
        public IMetaCategory<? super DataInterfaces> category() {
            return Commodity.METACAT;
        }

        @Override
        public int getGroupId() {
            return  716;
        }

        @Override
        public String getName() {
            return "DataInterfaces";
        }

        @Override
        public synchronized Map<String, DataInterfaces> load() {
            if (cache == null) {
                try {
                    cache = new Yaml().loadAs(new InputStreamReader(DataInterfaces.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, DataInterfaces> items;
        }
    }
}
