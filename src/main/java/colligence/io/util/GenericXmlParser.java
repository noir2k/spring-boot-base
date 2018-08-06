package colligence.io.util;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;

public class GenericXmlParser<T> {
	private final PrefixedLogger logger = PrefixedLogger.getLogger(getClass());

	private final Class<T> clazz;
	private Unmarshaller unmarshaller;

	public GenericXmlParser(Class<T> clazz) throws JAXBException {
		this.clazz = clazz;
		try {
			JAXBContext jc = JAXBContext.newInstance(clazz);
			this.unmarshaller = jc.createUnmarshaller();
		} catch(JAXBException e) {
			logger.error("Cannot create xml parser for {}", clazz.getSimpleName());
			throw e;
		}
	}

	public T parse(String xml) throws JAXBException {
		return parseInternal(this.clazz, xml);
	}

	private T parseInternal(Class<T> t, String xml) throws JAXBException {
		try {
			return t.cast(unmarshaller.unmarshal(new StringReader(xml)));
		} catch(JAXBException e) {
			logger.error("Cannot parse xml to {}", clazz.getSimpleName());
			throw e;
		}
	}
}