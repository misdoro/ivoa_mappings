package org.vamdc.registrysearch;

import java.util.List;

import javax.xml.bind.JAXBElement;

import net.ivoa.wsdl.registrysearch.ErrorResp;
import net.ivoa.wsdl.registrysearch.OpUnsupportedResp;
import net.ivoa.wsdl.registrysearch.RegistrySearchPortType;
import net.ivoa.wsdl.registrysearch.v1.XQuerySearch;
import net.ivoa.wsdl.registrysearch.v1.XQuerySearchResponse;
import net.ivoa.xml.voresource.v1.Capability;
import net.ivoa.xml.voresource.v1.Service;
import junit.framework.TestCase;

import com.sun.org.apache.xerces.internal.dom.ElementNSImpl;

public class TestRegistryXQuerySearch extends TestCase {


	private static String xquery  = "declare namespace ri='http://www.ivoa.net/xml/RegistryInterface/v1.0'; " + 
			"for $x in //ri:Resource " + 
			"where $x/capability[@standardID='ivo://vamdc/std/VAMDC-TAP']" +
			"and $x/@status='active' " +
			"return $x";

	private static String xquery2  = "declare namespace ri='http://www.ivoa.net/xml/RegistryInterface/v1.0'; " + 
			"for $x in //ri:Resource " + 
			"where $x/capability[@standardID='ivo://vamdc/std/VAMDC-TAP']" +
			"and $x/@status='active' " +
			"return $x/capability";


	public void testXQuery() {

		XQuerySearch xQuerySearch = new XQuerySearch();
		xQuerySearch.setXquery(xquery);

		RegistrySearchPortType search = RegistryFactory.getSearchPort();

		try {
			XQuerySearchResponse xqResp = search.xQuerySearch(xQuerySearch);
			List<Object> searchResult = xqResp.getAny();
			assertNotNull(searchResult);
			assertTrue(searchResult.size()>0);
			for (Object element:searchResult){
				System.out.println();
				JAXBElement obj = (JAXBElement)element;
				System.out.println(obj.getName());
				Service srv = (Service) obj.getValue();
				System.out.println(srv.getIdentifier());
				for (Capability cap:srv.getCapability()){
					System.out.println(cap);
				}

				System.out.println(obj.getValue().getClass());
				System.out.println("SearchResult:"+element.toString());
			}

		} catch (ErrorResp e) {
			fail (e.getMessage());
		} catch (OpUnsupportedResp e) {
			fail (e.getMessage());
		} catch (Exception e){
			e.printStackTrace();
			fail (e.getMessage());
		}
	}

	public void testXQueryCapability() {

		XQuerySearch xQuerySearch = new XQuerySearch();
		xQuerySearch.setXquery(xquery2);

		RegistrySearchPortType search = RegistryFactory.getSearchPort();

		try {
			XQuerySearchResponse xqResp = search.xQuerySearch(xQuerySearch);
			List<Object> searchResult = xqResp.getAny();
			assertNotNull(searchResult);
			assertTrue(searchResult.size()>0);
			for (Object element:searchResult){
				System.out.println();
				com.sun.org.apache.xerces.internal.dom.ElementNSImpl el = (ElementNSImpl) element;
				
				//System.out.println(el.getElementsByTagName(""));
				System.out.println(el.getChildNodes().getLength());
				
				System.out.println("SearchResult:"+element.toString());
			}

		} catch (ErrorResp e) {
			fail (e.getMessage());
		} catch (OpUnsupportedResp e) {
			fail (e.getMessage());
		} catch (Exception e){
			e.printStackTrace();
			fail (e.getMessage());
		}
	}

}
