package fr.guiguilechat.jcelechat.model.sde.types.structuremodule;

import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import fr.guiguilechat.jcelechat.model.sde.Attribute;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.annotations.DefaultIntValue;
import fr.guiguilechat.jcelechat.model.sde.annotations.DefaultRealValue;
import fr.guiguilechat.jcelechat.model.sde.annotations.HighIsGood;
import fr.guiguilechat.jcelechat.model.sde.annotations.Stackable;
import fr.guiguilechat.jcelechat.model.sde.attributes.CanFitShipGroup03;
import fr.guiguilechat.jcelechat.model.sde.attributes.Capacity;
import fr.guiguilechat.jcelechat.model.sde.attributes.Hp;
import fr.guiguilechat.jcelechat.model.sde.attributes.Mass;
import fr.guiguilechat.jcelechat.model.sde.attributes.MaxGroupFitted;
import fr.guiguilechat.jcelechat.model.sde.attributes.MoonRigAsteroidDecayBonus;
import fr.guiguilechat.jcelechat.model.sde.attributes.MoonRigFractureDelayBonus;
import fr.guiguilechat.jcelechat.model.sde.attributes.MoonRigSpewRadiusBonus;
import fr.guiguilechat.jcelechat.model.sde.attributes.MoonRigSpewVolumeBonus;
import fr.guiguilechat.jcelechat.model.sde.attributes.Radius;
import fr.guiguilechat.jcelechat.model.sde.attributes.RequiredSkill1;
import fr.guiguilechat.jcelechat.model.sde.attributes.RequiredSkill1Level;
import fr.guiguilechat.jcelechat.model.sde.attributes.RigSize;
import fr.guiguilechat.jcelechat.model.sde.attributes.StructureItemVisualFlag;
import fr.guiguilechat.jcelechat.model.sde.attributes.TechLevel;
import fr.guiguilechat.jcelechat.model.sde.attributes.UpgradeCost;
import fr.guiguilechat.jcelechat.model.sde.types.StructureModule;
import org.yaml.snakeyaml.Yaml;

public class StructureDrillingRigLProficiency
    extends StructureModule
{
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int canfitshipgroup03;
    /**
     * The maximum hitpoints of an object.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double hp;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int maxgroupfitted;
    /**
     * Moon rig bonus that delays spew asteroid decay
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int moonrigasteroiddecaybonus;
    /**
     * Rig Bonus that affects fracture delay for moon chunk
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int moonrigfracturedelaybonus;
    /**
     * Rig Bonus that affects spew asteroid belt radius from moon extraction
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int moonrigspewradiusbonus;
    /**
     * Moon rig bonus that increases volume of moon spew in relation to extraction time
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double moonrigspewvolumebonus;
    /**
     * The type ID of the skill that is required.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int requiredskill1;
    /**
     * Required skill level for skill 1
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int requiredskill1level;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int rigsize;
    /**
     * Authoring has been moved to FSD
     * Tech level of an item
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(1)
    public int techlevel;
    /**
     * How much of the upgrade capacity is used when this is fitted to a ship.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int upgradecost;
    public static final Set<Attribute> ATTRIBUTES = Collections.unmodifiableSet(new LinkedHashSet<>(Arrays.asList(new Attribute[] {UpgradeCost.INSTANCE, Radius.INSTANCE, Mass.INSTANCE, TechLevel.INSTANCE, Capacity.INSTANCE, MaxGroupFitted.INSTANCE, Hp.INSTANCE, RigSize.INSTANCE, MoonRigFractureDelayBonus.INSTANCE, CanFitShipGroup03 .INSTANCE, MoonRigAsteroidDecayBonus.INSTANCE, RequiredSkill1Level.INSTANCE, MoonRigSpewRadiusBonus.INSTANCE, MoonRigSpewVolumeBonus.INSTANCE, RequiredSkill1 .INSTANCE, StructureItemVisualFlag.INSTANCE })));
    public static final StructureDrillingRigLProficiency.MetaGroup METAGROUP = new StructureDrillingRigLProficiency.MetaGroup();

    @Override
    public Number valueSet(Attribute attribute) {
        switch (attribute.getId()) {
            case  1300 :
            {
                return canfitshipgroup03;
            }
            case  9 :
            {
                return hp;
            }
            case  1544 :
            {
                return maxgroupfitted;
            }
            case  2708 :
            {
                return moonrigasteroiddecaybonus;
            }
            case  2707 :
            {
                return moonrigfracturedelaybonus;
            }
            case  2709 :
            {
                return moonrigspewradiusbonus;
            }
            case  2710 :
            {
                return moonrigspewvolumebonus;
            }
            case  182 :
            {
                return requiredskill1;
            }
            case  277 :
            {
                return requiredskill1level;
            }
            case  1547 :
            {
                return rigsize;
            }
            case  422 :
            {
                return techlevel;
            }
            case  1153 :
            {
                return upgradecost;
            }
            default:
            {
                return super.valueSet((attribute));
            }
        }
    }

    @Override
    public Set<Attribute> getAttributes() {
        return ATTRIBUTES;
    }

    @Override
    public IMetaGroup<StructureDrillingRigLProficiency> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<StructureDrillingRigLProficiency>
    {
        public static final String RESOURCE_PATH = "SDE/types/structuremodule/StructureDrillingRigLProficiency.yaml";
        private Map<String, StructureDrillingRigLProficiency> cache = (null);

        @Override
        public IMetaCategory<? super StructureDrillingRigLProficiency> category() {
            return StructureModule.METACAT;
        }

        @Override
        public int getGroupId() {
            return  1914;
        }

        @Override
        public String getName() {
            return "StructureDrillingRigLProficiency";
        }

        @Override
        public synchronized Map<String, StructureDrillingRigLProficiency> load() {
            if (cache == null) {
                try(final InputStreamReader reader = new InputStreamReader(StructureDrillingRigLProficiency.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
                    cache = new Yaml().loadAs(reader, (Container.class)).types;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, StructureDrillingRigLProficiency> types;
        }
    }
}
