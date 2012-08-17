package net.ivoa.test;

import static org.junit.Assert.*;

import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import net.ivoa.xml.voresource.v1.Capability;
import net.ivoa.xml.vosiavailability.v1.Availability;
import net.ivoa.xml.vosicapabilities.v1.Capabilities;

import org.junit.Test;

/**
 * Test that we can marshal capabilities and availability elements
 * @author doronin
 *
 */
public class MarshalTest {
	
	@Test
	public void MarshalCapabilities(){
		Capabilities caps = new Capabilities();
		
		Capability cap = new Capability();
		caps.getCapability().add(cap);
		cap.setStandardID("testStandardID");
		
		String result = marshal(caps);
		assertTrue(result.contains("testStandardID"));
	}
	
	@Test
	public void MarshalAvailability(){
		Availability avail = new Availability();
		avail.setAvailable(true);
		avail.getNote().add("AvailableNote");
		
		String result = marshal(avail);
		assertTrue(result.contains("AvailableNote"));
		
	}
	
	private String marshal(Object jaxbElement){
		try {
			JAXBContext context = JAXBContext.newInstance(jaxbElement.getClass());
			Marshaller marshaller = context.createMarshaller();
			StringWriter result = new StringWriter();
			marshaller.marshal(jaxbElement, result);
			String resultString = result.toString();
			assertFalse(resultString.isEmpty());
			return resultString;
		} catch (JAXBException e) {
			fail(e.getMessage()+e.getCause().getMessage());
			e.printStackTrace();
		}
		return "";
	}

}
