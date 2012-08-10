package org.vamdc.registry.search;

import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.BindingProvider;
import net.ivoa.wsdl.registrysearch.RegistryInterfaceService;
import net.ivoa.wsdl.registrysearch.RegistrySearchPortType;

/**
 * Registry access library factory methods
 * @author doronin
 *
 */
public final class RegistryClientFactory {

	private final static URL WSDL_LOCATION;
	private final static QName SERVICE;

	static {
		WSDL_LOCATION = RegistryClientFactory.class.getResource("/wsdl/RegistryQueryv1_0.wsdl");
		SERVICE = new QName("http://www.ivoa.net/wsdl/RegistrySearch", "RegistryInterfaceService");
	}

	/**
	 * 
	 * @return registry search port with the default registry endpoint URL
	 */
	public static RegistrySearchPortType getSearchPort(){
		RegistryInterfaceService service = new RegistryInterfaceService(WSDL_LOCATION, SERVICE);
		RegistrySearchPortType port = service.getRegistrySearchPort();
		return port;
	}

	
	/**
	 * 
	 * @param registryURL RegistrySearch Endpoint URL
	 * @return registry search port with a custom registry endpoint URL
	 */
	public static RegistrySearchPortType getSearchPort(String registryURL){
		RegistrySearchPortType port = getSearchPort();
		setServiceURL(registryURL, port);
		return port;
	}

	
	/**
	 * 
	 * @param registryURL RegistrySearch Endpoint URL
	 * @return registry search port with a custom registry endpoint URL
	 */
	public static RegistrySearchPortType getSearchPort(URL registryURL){
		return getSearchPort(registryURL.toString());
	}

	private static void setServiceURL(String registryURL,
			RegistrySearchPortType port) {
		((BindingProvider)port).getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY,
				new String(registryURL));
	}
}
