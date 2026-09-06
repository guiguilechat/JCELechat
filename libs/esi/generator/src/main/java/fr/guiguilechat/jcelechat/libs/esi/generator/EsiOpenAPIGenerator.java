package fr.guiguilechat.jcelechat.libs.esi.generator;

import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

import com.helger.jcodemodel.JCodeModel;
import com.helger.jcodemodel.exceptions.JCodeModelException;
import com.helger.jcodemodel.plugin.maven.ICodeModelBuilder;
import com.helger.jcodemodel.plugin.maven.ISourcedInputStream;
import com.helger.jcodemodel.plugin.maven.generators.JCMGen;

@JCMGen
public class EsiOpenAPIGenerator implements ICodeModelBuilder {

	private String rootPackage = "";


	@Override
	public void build(JCodeModel model, @NonNull ISourcedInputStream source) throws JCodeModelException {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException();
	}

	@Override
	public @Nullable String getRootPackage() {
		return rootPackage;
	}

	@Override
	public void setRootPackage(@Nullable String rootPackage) {
		this.rootPackage = rootPackage;
	}

}
