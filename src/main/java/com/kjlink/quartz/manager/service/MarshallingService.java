package com.kjlink.quartz.manager.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.oxm.Marshaller;
import org.springframework.oxm.Unmarshaller;
import org.springframework.oxm.XmlMappingException;
import org.springframework.stereotype.Service;

@Service
public class MarshallingService {
	
	private Marshaller marshaller;
	private Unmarshaller unmarshaller;
	
	// castorMarshaller 
	@Autowired @Qualifier("jaxb2Marshaller")
	public void setMarshaller(Marshaller marshaller) {
		this.marshaller = marshaller;
	}

	@Autowired @Qualifier("jaxb2Marshaller")
	public void setUnmarshaller(Unmarshaller unmarshaller) {
		this.unmarshaller = unmarshaller;
	}

	public <T> void saveSettings(File settingsFile, T  jobSchedulingData) throws IOException {
		FileOutputStream os = null;
		try {
			os = new FileOutputStream(settingsFile);
			this.marshaller.marshal(jobSchedulingData, new StreamResult(os));
		} finally {
			if (os != null) {
				os.close();
			}
		}
	}

	@SuppressWarnings("unchecked")
	public <T> T loadSettings(File settingsFile, T jobSchedulingData) throws IOException, XmlMappingException {
		FileInputStream is = null;
		try {
			is = new FileInputStream(settingsFile);
			jobSchedulingData = (T) this.unmarshaller
					.unmarshal(new StreamSource(is));
			return jobSchedulingData;
		} finally {
			if (is != null) {
				is.close();
			}
		}
	}

}
