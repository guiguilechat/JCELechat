package fr.guiguilechat.jcelechat.model.sde.items.types.structuremodule;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.Attribute;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.items.annotations.DefaultDoubleValue;
import fr.guiguilechat.jcelechat.model.sde.items.annotations.DefaultIntValue;
import fr.guiguilechat.jcelechat.model.sde.items.annotations.HighIsGood;
import fr.guiguilechat.jcelechat.model.sde.items.annotations.Stackable;
import fr.guiguilechat.jcelechat.model.sde.items.types.StructureModule;
import org.yaml.snakeyaml.Yaml;

public class StructureArmorReinforcer
    extends StructureModule
{
    /**
     * Multiplier to the HP of a ships armor module.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(1.0)
    public double ArmorHPMultiplier;
    /**
     * CPU need of module
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double Cpu;
    /**
     * The maximum hitpoints of an object.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int Hp;
    /**
     * Authoring has been moved to FSD
     * The ranking of the module within its tech level
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int MetaLevel;
    /**
     * current power need
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultIntValue(0)
    public int Power;
    /**
     * Dogma attribute that specifies if the item should have the structure icon or not.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int StructureItemVisualFlag;
    /**
     * Authoring has been moved to FSD
     * Tech level of an item
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(1)
    public int TechLevel;
    public final static StructureArmorReinforcer.MetaGroup METAGROUP = new StructureArmorReinforcer.MetaGroup();

    @Override
    public Number attribute(Attribute attribute) {
        switch (attribute.getId()) {
            case  148 :
            {
                return ArmorHPMultiplier;
            }
            case  50 :
            {
                return Cpu;
            }
            case  9 :
            {
                return Hp;
            }
            case  633 :
            {
                return MetaLevel;
            }
            case  30 :
            {
                return Power;
            }
            case  2334 :
            {
                return StructureItemVisualFlag;
            }
            case  422 :
            {
                return TechLevel;
            }
            default:
            {
                return super.attribute((attribute));
            }
        }
    }

    @Override
    public IMetaGroup<StructureArmorReinforcer> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<StructureArmorReinforcer>
    {
        public final static String RESOURCE_PATH = "SDE/items/structuremodule/StructureArmorReinforcer.yaml";
        private Map<String, StructureArmorReinforcer> cache = (null);

        @Override
        public IMetaCategory<? super StructureArmorReinforcer> category() {
            return StructureModule.METACAT;
        }

        @Override
        public int getGroupId() {
            return  1968;
        }

        @Override
        public String getName() {
            return "StructureArmorReinforcer";
        }

        @Override
        public synchronized Map<String, StructureArmorReinforcer> load() {
            if (cache == null) {
                try {
                    cache = new Yaml().loadAs(new InputStreamReader(StructureArmorReinforcer.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, StructureArmorReinforcer> items;
        }
    }
}