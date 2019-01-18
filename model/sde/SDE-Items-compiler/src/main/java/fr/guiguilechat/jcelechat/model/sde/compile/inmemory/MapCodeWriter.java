package fr.guiguilechat.jcelechat.model.sde.compile.inmemory;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import com.helger.jcodemodel.JPackage;
import com.helger.jcodemodel.writer.AbstractCodeWriter;

/**
 * received code and store it in maps of binary data.
 * <p>
 * This is used in a {@link JCodeModel.#build(CodeWriter)} to store the code
 * built. THEN use the {@link #getBinaries()} to retrieve a map of the binaries
 * created by the building process.
 * </p>
 *
 */
public class MapCodeWriter extends AbstractCodeWriter {

	protected MapCodeWriter() {
		super(Charset.defaultCharset(), System.lineSeparator());
	}

	private HashMap<String, ByteArrayOutputStream> binaries = new HashMap<>();

	@Override
	public OutputStream openBinary(JPackage pkg, String fileName) throws IOException {
		String fullname = (pkg.name() + "." + fileName).replace(".java", "");
		ByteArrayOutputStream ret = binaries.get(fullname);
		if (ret == null) {
			ret = new ByteArrayOutputStream();
			binaries.put(fullname, ret);
		}
		return ret;
	}

	@Override
	public void close() throws IOException {
		// nothing.
	}

	/**
	 *
	 * @return an unmodifiable map of the internal binaries.
	 */
	public Map<String, ByteArrayOutputStream> getBinaries() {
		return Collections.unmodifiableMap(binaries);
	}

}
