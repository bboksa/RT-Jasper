/*
 * RT-Jasper - RT-Jasper is a toolkit to integrate RT: Request Tracker as a queryable data source in JasperServer.
 * Copyright (C) 2012  Benjamin Boksa
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package de.boksa.jasper.rt.service;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.jaspersoft.jasperserver.api.metadata.jasperreports.service.ReportDataSourceService;

import de.boksa.jasper.rt.util.JRRTRESTInterfaceQueryExecuterFactory;
import de.boksa.rt.dao.RESTRTDAOFactory;
import de.boksa.rt.dao.RTDAOFactory;
import de.boksa.rt.dao.RTTicketDAO;
import de.boksa.rt.dao.RTDAOFactory.RTDAOFactoryType;

public class JRRTRESTInterfaceDataSourceService implements ReportDataSourceService {

	private static final Log LOG = LogFactory.getLog(JRRTRESTInterfaceDataSourceService.class);

	private String restInterfaceBaseURL;
	private String username;
	private String password;
	
	private RTTicketDAO dao; 
	
	@Override
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void setReportParameterValues(Map parametersMap) {
		Map<String,Object> factoryParameters = new HashMap<String,Object>();
		factoryParameters.put(RESTRTDAOFactory.REST_INTERFACE_BASE_URL, this.restInterfaceBaseURL);
		factoryParameters.put(RESTRTDAOFactory.REST_INTERFACE_USERNAME, this.username);
		factoryParameters.put(RESTRTDAOFactory.REST_INTERFACE_PASSWORD, this.password);
		
		this.dao = RTDAOFactory.getRTDAOFactory(RTDAOFactoryType.REST).getRTTicketDAO(factoryParameters);
		parametersMap.put(JRRTRESTInterfaceQueryExecuterFactory.PARAMETER_RT_TICKET_DAO, dao);		
	}
	
	@Override
	public void closeConnection() {
		// TODO Auto-generated method stub
		
	}

	// getter and setter methods...
	public String getRestInterfaceBaseURL() {
		LOG.debug("getRestInterfaceBaseURL called");		
		return restInterfaceBaseURL;
	}
	public void setRestInterfaceBaseURL(String restInterfaceBaseURL) {
		LOG.debug("setRestInterfaceBaseURL called");		
		this.restInterfaceBaseURL = restInterfaceBaseURL;
	}
	public String getUsername() {
		LOG.debug("getUsername called");		
		return username;
	}
	public void setUsername(String username) {
		LOG.debug("setUsername called");		
		this.username = username;
	}
	public String getPassword() {
		LOG.debug("getPassword called");		
		return password;
	}
	public void setPassword(String password) {
		LOG.debug("setPassword called");		
		this.password = password;
	}







}
