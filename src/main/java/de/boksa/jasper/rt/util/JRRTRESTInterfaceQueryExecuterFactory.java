/*
 * RT-Jasper - RT-Jasper is a toolkit to integrate RT: Request Tracker as a queryable data source in JasperServer.
 * Copyright (C) 2012  Benjamin Boksa (http://www.boksa.de/)
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
package de.boksa.jasper.rt.util;

import java.util.Map;

import net.sf.jasperreports.engine.JRDataset;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.query.JRQueryExecuter;
import net.sf.jasperreports.engine.query.JRQueryExecuterFactory;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import de.boksa.jasper.rt.query.JRRTRESTInterfaceQueryExecuter;
import de.boksa.rt.dao.RTTicketDAO;

public class JRRTRESTInterfaceQueryExecuterFactory implements JRQueryExecuterFactory {

	private static final Log LOG = LogFactory.getLog(JRRTRESTInterfaceQueryExecuterFactory.class);

	public static final String PARAMETER_RT_TICKET_DAO = JRRTRESTInterfaceQueryExecuterFactory.class.getName() + "/" + RTTicketDAO.class.getName();
	
	private static final Object[] BUILTIN_PARAMETERS = {
		PARAMETER_RT_TICKET_DAO, RTTicketDAO.class
	};
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public JRQueryExecuter createQueryExecuter(JRDataset dataset, Map parameters) throws JRException {
		LOG.debug("createQueryExecuter called");
		return new JRRTRESTInterfaceQueryExecuter(dataset, parameters);
	}

	@Override
	public Object[] getBuiltinParameters() {
		return BUILTIN_PARAMETERS;
	}

	@Override
	public boolean supportsQueryParameterType(String className) {
		return true;
	}

}
