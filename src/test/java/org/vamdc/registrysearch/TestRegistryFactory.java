package org.vamdc.registrysearch;

import java.net.MalformedURLException;
import java.net.URL;

import net.ivoa.wsdl.registrysearch.RegistrySearchPortType;
import junit.framework.TestCase;

public class TestRegistryFactory extends TestCase {

	public final static String REGISTRY_URL="http://casx019-zone1.ast.cam.ac.uk:80/registry/services/RegistryQueryv1_0";
	
	public void testGetService() {
		RegistrySearchPortType port = RegistryFactory.getSearchPort();
		
		assertNotNull(port);
	}

	public void testGetServiceURL() {
		RegistrySearchPortType port=null;
		try {
			port = RegistryFactory.getSearchPort(new URL(REGISTRY_URL));
		} catch (MalformedURLException e) {
			fail("Malformed test URL "+REGISTRY_URL);
		}
		
		assertNotNull(port);
	}

	public void testGetServiceString() {
		RegistrySearchPortType port = RegistryFactory.getSearchPort(REGISTRY_URL);
		
		assertNotNull(port);
	}

}
