package fr.guiguilechat.jcelechat.model.sde.compile;

import java.util.HashMap;
import java.util.Set;

import com.helger.jcodemodel.JCodeModel;
import com.helger.jcodemodel.JDefinedClass;

/** data from compiling the types into java classes */
public class CompilationData {
	public JCodeModel model = new JCodeModel();

	/** typeindex class, that allows to search for types */
	public JDefinedClass typeIndexClass;

	// for each category, the set of groups that inherit it.
	public HashMap<JDefinedClass, Set<JDefinedClass>> cat2Groups = new HashMap<>();
	public HashMap<Integer, String> groupID2ClassName = new HashMap<>();

	public HashMap<Integer, String> attID2FieldName = new HashMap<>();
}