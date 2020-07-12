package fr.guiguilechat.jcelechat.model.sde.types.charge;

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
import fr.guiguilechat.jcelechat.model.sde.annotations.DefaultDoubleValue;
import fr.guiguilechat.jcelechat.model.sde.annotations.DefaultIntValue;
import fr.guiguilechat.jcelechat.model.sde.annotations.HighIsGood;
import fr.guiguilechat.jcelechat.model.sde.annotations.Stackable;
import fr.guiguilechat.jcelechat.model.sde.attributes.CapNeedBonus;
import fr.guiguilechat.jcelechat.model.sde.attributes.Capacity;
import fr.guiguilechat.jcelechat.model.sde.attributes.ChargeSize;
import fr.guiguilechat.jcelechat.model.sde.attributes.CrystalVolatilityChance;
import fr.guiguilechat.jcelechat.model.sde.attributes.CrystalVolatilityDamage;
import fr.guiguilechat.jcelechat.model.sde.attributes.CrystalsGetDamaged;
import fr.guiguilechat.jcelechat.model.sde.attributes.Damage;
import fr.guiguilechat.jcelechat.model.sde.attributes.Hp;
import fr.guiguilechat.jcelechat.model.sde.attributes.LauncherGroup;
import fr.guiguilechat.jcelechat.model.sde.attributes.MainColor;
import fr.guiguilechat.jcelechat.model.sde.attributes.Mass;
import fr.guiguilechat.jcelechat.model.sde.attributes.MetaLevel;
import fr.guiguilechat.jcelechat.model.sde.attributes.Radius;
import fr.guiguilechat.jcelechat.model.sde.attributes.RequiredSkill1;
import fr.guiguilechat.jcelechat.model.sde.attributes.RequiredSkill1Level;
import fr.guiguilechat.jcelechat.model.sde.attributes.RequiredSkill2;
import fr.guiguilechat.jcelechat.model.sde.attributes.RequiredSkill2Level;
import fr.guiguilechat.jcelechat.model.sde.attributes.SpecialisationAsteroidGroup;
import fr.guiguilechat.jcelechat.model.sde.attributes.SpecialisationAsteroidYieldMultiplier;
import fr.guiguilechat.jcelechat.model.sde.attributes.TechLevel;
import fr.guiguilechat.jcelechat.model.sde.attributes.UnfitCapCost;
import fr.guiguilechat.jcelechat.model.sde.types.Charge;
import org.yaml.snakeyaml.Yaml;

public class MiningCrystal
    extends Charge
{
    /**
     * Autogenerated skill attribute, capNeedBonus
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int capneedbonus;
    /**
     * The size of the charges that can fit in the turret/whatever.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int chargesize;
    /**
     * The chance of damage to the crystal each time it is used.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double crystalvolatilitychance;
    /**
     * The amount of damage done if the crystal is damaged in the process of using it.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double crystalvolatilitydamage;
    /**
     * Whether this tool causes damage to crystals with each use of them.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int crystalsgetdamaged;
    /**
     * current structure damage dealt to module
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultIntValue(0)
    public int damage;
    /**
     * The maximum hitpoints of an object.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double hp;
    /**
     * One of the groups of launcher this charge can be loaded into.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int launchergroup;
    /**
     * The main color of a ship type.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int maincolor;
    /**
     * Authoring has been moved to FSD
     * The ranking of the module within its tech level
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int metalevel;
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
     * The type ID of the skill that is required.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int requiredskill2;
    /**
     * Required skill level for skill 2
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int requiredskill2level;
    /**
     * The group at which the mining crystal is tuned to mine.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int specialisationasteroidgroup;
    /**
     * The amount the yield is modified when mining the asteroid group this crystal is tuned for.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double specialisationasteroidyieldmultiplier;
    /**
     * Authoring has been moved to FSD
     * Tech level of an item
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(1)
    public int techlevel;
    /**
     * The capacitor charge required to disengage this crystal from the unit it is installed in.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int unfitcapcost;
    public static final Set<Attribute> ATTRIBUTES = Collections.unmodifiableSet(new LinkedHashSet<>(Arrays.asList(new Attribute[] {ChargeSize.INSTANCE, Radius.INSTANCE, Damage.INSTANCE, Mass.INSTANCE, TechLevel.INSTANCE, Capacity.INSTANCE, Hp.INSTANCE, LauncherGroup.INSTANCE, SpecialisationAsteroidGroup.INSTANCE, SpecialisationAsteroidYieldMultiplier.INSTANCE, CrystalVolatilityChance.INSTANCE, CrystalVolatilityDamage.INSTANCE, UnfitCapCost.INSTANCE, CrystalsGetDamaged.INSTANCE, RequiredSkill1Level.INSTANCE, RequiredSkill2Level.INSTANCE, RequiredSkill1 .INSTANCE, RequiredSkill2 .INSTANCE, MetaLevel.INSTANCE, MainColor.INSTANCE, CapNeedBonus.INSTANCE })));
    public static final MiningCrystal.MetaGroup METAGROUP = new MiningCrystal.MetaGroup();

    @Override
    public Number attribute(Attribute attribute) {
        switch (attribute.getId()) {
            case  317 :
            {
                return capneedbonus;
            }
            case  128 :
            {
                return chargesize;
            }
            case  783 :
            {
                return crystalvolatilitychance;
            }
            case  784 :
            {
                return crystalvolatilitydamage;
            }
            case  786 :
            {
                return crystalsgetdamaged;
            }
            case  3 :
            {
                return damage;
            }
            case  9 :
            {
                return hp;
            }
            case  137 :
            {
                return launchergroup;
            }
            case  124 :
            {
                return maincolor;
            }
            case  633 :
            {
                return metalevel;
            }
            case  182 :
            {
                return requiredskill1;
            }
            case  277 :
            {
                return requiredskill1level;
            }
            case  183 :
            {
                return requiredskill2;
            }
            case  278 :
            {
                return requiredskill2level;
            }
            case  781 :
            {
                return specialisationasteroidgroup;
            }
            case  782 :
            {
                return specialisationasteroidyieldmultiplier;
            }
            case  422 :
            {
                return techlevel;
            }
            case  785 :
            {
                return unfitcapcost;
            }
            default:
            {
                return super.attribute((attribute));
            }
        }
    }

    @Override
    public Set<Attribute> getAttributes() {
        return ATTRIBUTES;
    }

    @Override
    public IMetaGroup<MiningCrystal> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<MiningCrystal>
    {
        public static final String RESOURCE_PATH = "SDE/types/charge/MiningCrystal.yaml";
        private Map<String, MiningCrystal> cache = (null);

        @Override
        public IMetaCategory<? super MiningCrystal> category() {
            return Charge.METACAT;
        }

        @Override
        public int getGroupId() {
            return  482;
        }

        @Override
        public String getName() {
            return "MiningCrystal";
        }

        @Override
        public synchronized Map<String, MiningCrystal> load() {
            if (cache == null) {
                try(final InputStreamReader reader = new InputStreamReader(MiningCrystal.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
                    cache = new Yaml().loadAs(reader, (Container.class)).types;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, MiningCrystal> types;
        }
    }
}