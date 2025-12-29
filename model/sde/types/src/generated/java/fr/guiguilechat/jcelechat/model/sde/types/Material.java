package fr.guiguilechat.jcelechat.model.sde.types;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Set;

import fr.guiguilechat.jcelechat.model.sde.Attribute;
import fr.guiguilechat.jcelechat.model.sde.EveType;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.types.material.*;

public abstract class Material
    extends EveType
{
    public static final Set<Attribute> ATTRIBUTES = Collections.emptySet();
    public static final Material.MetaCat METACAT = new Material.MetaCat();

    @Override
    public Set<Attribute> getAttributes() {
        return ATTRIBUTES;
    }

    @Override
    public IMetaCategory<Material> getCategory() {
        return METACAT;
    }

    public static class MetaCat
        implements IMetaCategory<Material>
    {

        @Override
        public int getCategoryId() {
            return  4;
        }

        @Override
        public String getName() {
            return "Material";
        }

        @Override
        public Collection<IMetaGroup<? extends Material>> groups() {
            return Arrays.asList(MolecularForgedMaterials.METAGROUP, Mineral.METAGROUP, IceProduct.METAGROUP, MoonMaterials.METAGROUP, IntermediateMaterials.METAGROUP, Composite.METAGROUP, BiochemicalMaterial.METAGROUP, SalvagedMaterials.METAGROUP, Prismaticite.METAGROUP, UnrefinedMineral.METAGROUP, RogueDroneComponents.METAGROUP, AncientSalvage.METAGROUP, HybridPolymers.METAGROUP, FuelBlock.METAGROUP, NamedComponents.METAGROUP, AbyssalMaterials.METAGROUP);
        }
    }
}
