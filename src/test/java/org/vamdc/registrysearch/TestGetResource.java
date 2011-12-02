package org.vamdc.registrysearch;

import net.ivoa.wsdl.registrysearch.ErrorResp;
import net.ivoa.wsdl.registrysearch.NotFoundResp;
import net.ivoa.wsdl.registrysearch.OpUnsupportedResp;
import net.ivoa.wsdl.registrysearch.RegistrySearchPortType;
import net.ivoa.xml.voresource.v1.Resource;
import junit.framework.TestCase;

public class TestGetResource extends TestCase {

	public void testGetResource() {
		RegistrySearchPortType search = RegistryFactory.getSearchPort();
		Resource result = null;
		try {
			result = search.getResource("ivo://vamdc/basecol/tap-xsams");
		} catch (ErrorResp e) {
			fail (e.getMessage());
		} catch (NotFoundResp e) {
			fail (e.getMessage());
		} catch (Exception e){
			e.printStackTrace();
			fail (e.getMessage());
		}
		
		assertNotNull(result);
		System.out.println(result);
	}

}
