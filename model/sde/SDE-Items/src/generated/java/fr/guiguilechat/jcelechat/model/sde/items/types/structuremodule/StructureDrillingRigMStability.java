package fr.guiguilechat.jcelechat.model.sde.items.types.structuremodule;

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
import fr.guiguilechat.jcelechat.model.sde.items.types.StructureModule;
import org.yaml.snakeyaml.Yaml;

public class StructureDrillingRigMStability
    extends StructureModule
{
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int CanFitShipGroup03;
    /**
     * The maximum hitpoints of an object.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int Hp;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int MaxGroupFitted;
    /**
     * Moon rig bonus that delays spew asteroid decay
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int MoonRigAsteroidDecayBonus;
    /**
     * Rig Bonus that affects fracture delay for moon chunk
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int MoonRigFractureDelayBonus;
    /**
     * Rig Bonus that affects spew asteroid belt radius from moon extraction
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int MoonRigSpewRadiusBonus;
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
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int RigSize;
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
    /**
     * How much of the upgrade capacity is used when this is fitted to a ship.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int UpgradeCost;
    public final static StructureDrillingRigMStability.MetaGroup METAGROUP = new StructureDrillingRigMStability.MetaGroup();

    @Override
    public Number attribute(Attribute attribute) {
        switch (attribute.getId()) {
            case  1300 :
            {
                return CanFitShipGroup03;
            }
            case  9 :
            {
                return Hp;
            }
            case  1544 :
            {
                return MaxGroupFitted;
            }
            case  2708 :
            {
                return MoonRigAsteroidDecayBonus;
            }
            case  2707 :
            {
                return MoonRigFractureDelayBonus;
            }
            case  2709 :
            {
                return MoonRigSpewRadiusBonus;
            }
            case  182 :
            {
                return RequiredSkill1;
            }
            case  277 :
            {
                return RequiredSkill1Level;
            }
            case  1547 :
            {
                return RigSize;
            }
            case  2334 :
            {
                return StructureItemVisualFlag;
            }
            case  422 :
            {
                return TechLevel;
            }
            case  1153 :
            {
                return UpgradeCost;
            }
            default:
            {
                return super.attribute((attribute));
            }
        }
    }

    @Override
    public IMetaGroup<StructureDrillingRigMStability> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<StructureDrillingRigMStability>
    {
        public final static String RESOURCE_PATH = "SDE/items/structuremodule/StructureDrillingRigMStability.yaml";
        private Map<String, StructureDrillingRigMStability> cache = (null);

        @Override
        public IMetaCategory<? super StructureDrillingRigMStability> category() {
            return StructureModule.METACAT;
        }

        @Override
        public int getGroupId() {
            return  1913;
        }

        @Override
        public String getName() {
            return "StructureDrillingRigMStability";
        }

        @Override
        public synchronized Map<String, StructureDrillingRigMStability> load() {
            if (cache == null) {
                try {
                    cache = new Yaml().loadAs(new InputStreamReader(StructureDrillingRigMStability.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, StructureDrillingRigMStability> items;
        }
    }
}