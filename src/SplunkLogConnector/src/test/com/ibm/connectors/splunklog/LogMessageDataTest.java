/*

Copyright 2015 IBM Corporation
Author: Carsten Börnert

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.

*/

package com.ibm.connectors.splunklog;

import static org.junit.Assert.assertEquals;

import java.util.Properties;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.ibm.connectors.ConnectorException;
import com.ibm.connectors.splunklog.SplunkLogMessageData;

public class LogMessageDataTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testLogMessageData() {
		byte[] message = "".getBytes();
		Properties properties = new Properties();
		properties.put("test", "testing");
		properties.put("splunk_test", "testing2");
		
		SplunkLogMessageData ldata = null;
		try {
			ldata = new SplunkLogMessageData(message, properties);
		} catch (ConnectorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals("Expected parameter list size of 1.", 1, ldata.getParams().size());
		
		assertEquals("Expected property name test.", "test", ldata.getParams().get(0).getName());
	}
	
	@Test
	public void testGetLogMessage_EmptyPayload() {
		byte[] message = "".getBytes();
		Properties properties = new Properties();
		SplunkLogMessageData ldata = null;
		try {
			ldata = new SplunkLogMessageData(message, properties);
		} catch (ConnectorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals("Expected msg saying the payload is empty.","Payload: Empty!", new String(ldata.getLogMessage()));
	}
	
	@Test
	public void testGetLogMessage_WithPayload() {
		byte[] message = "X".getBytes();
		Properties properties = new Properties();
		SplunkLogMessageData ldata = null;
		try {
			ldata = new SplunkLogMessageData(message, properties);
		} catch (ConnectorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals("Expected msg with payload.","Payload: X", new String(ldata.getLogMessage()));
	}
	
	@Test
	public void testGetLogMessage_withProps() {
		byte[] message = "".getBytes();
		Properties properties = new Properties();
		properties.put("splunk_UT", "true");
		properties.put("splunk_UT2", "false");
		
		SplunkLogMessageData ldata = null;
		try {
			ldata = new SplunkLogMessageData(message, properties);
		} catch (ConnectorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals("Expected msg with 2 props and empty payload.","UT2=false,UT=true, Payload: Empty!", new String(ldata.getLogMessage()));
	}
	
	

}
