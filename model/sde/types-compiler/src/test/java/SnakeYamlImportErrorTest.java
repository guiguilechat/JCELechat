import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.nio.charset.Charset;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.yaml.snakeyaml.LoaderOptions;
import org.yaml.snakeyaml.Yaml;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.helger.jcodemodel.JCodeModel;
import com.helger.jcodemodel.JDefinedClass;
import com.helger.jcodemodel.JMod;
import com.helger.jcodemodel.exceptions.JCodeModelException;
import com.helger.jcodemodel.writer.JCMWriter;
import com.helger.jcodemodel.writer.OutputStreamCodeWriter;

import fr.lelouet.tools.compilation.inmemory.DynamicClassLoader;

public class SnakeYamlImportErrorTest {

	public void compileAndTest(Class<?> fieldClass) throws JCodeModelException, IOException {
		JCodeModel jcm = new JCodeModel();
		JDefinedClass cl = jcm._class("Test");
		cl.field(JMod.PUBLIC, fieldClass, "field");

		try {
			DynamicClassLoader dcl = new DynamicClassLoader(SnakeYamlImportErrorTest.class.getClassLoader()).withCode(jcm);
			Class<?> compiled = dcl.loadClass(cl.name());
			compiled.getConstructor().newInstance();
		} catch (Exception e) {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			OutputStreamCodeWriter acw = new OutputStreamCodeWriter(baos, Charset.defaultCharset());
			new JCMWriter(jcm).build(acw);
			throw new UnsupportedOperationException(baos.toString(), e);
		}
	}

	@Test
	public void testCompilingLoader() throws JCodeModelException, IOException {
		compileAndTest(LoaderOptions.class);
	}

	@Test
	public void testCompilingYaml() throws JCodeModelException, IOException {
		compileAndTest(Yaml.class);
	}

	@Test
	public void testCompilingString() throws JCodeModelException, IOException {
		compileAndTest(String.class);
	}

	@Test
	public void testCompilingJCM() throws JCodeModelException, IOException {
		compileAndTest(JCodeModel.class);
	}

	@Test
	public void testCompilingJackson() throws JCodeModelException, IOException {
		compileAndTest(ObjectMapper.class);
	}

	@Test
	public void directAccess()
			throws JCodeModelException, ClassNotFoundException, InstantiationException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, IOException {
		JCodeModel jcm = new JCodeModel();
		jcm._class("Test");

		DynamicClassLoader dcl = new DynamicClassLoader(SnakeYamlImportErrorTest.class.getClassLoader()).withCode(jcm);
		Class<?> lcl = dcl.loadClass(Yaml.class.getName());
		Assert.assertNotNull(lcl);
		Class<?> lcl2 = dcl.loadClass(LoaderOptions.class.getName());
		Assert.assertNotNull(lcl2);
	}

}
