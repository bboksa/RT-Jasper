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
package de.boksa.jasper.rt.data;

import java.math.BigDecimal;
import java.util.Collection;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;
import net.sf.jasperreports.engine.data.JRAbstractBeanDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import de.boksa.rt.model.RTTicket;

public class JRRTRESTInterfaceDataSource extends JRBeanCollectionDataSource {

	private static final Log LOG = LogFactory.getLog(JRRTRESTInterfaceDataSource.class);

	public JRRTRESTInterfaceDataSource(Collection<RTTicket> beanCollection) {
		super(beanCollection, true);
	}

	@Override
	public Object getFieldValue(Object currentBean, JRField jrField) throws JRException {
		Object value = JRAbstractBeanDataSource.getBeanProperty(currentBean, this.getPropertyName(jrField));
		
		return (value instanceof String) ? this.parseValue(jrField.getValueClass(), value.toString()) : value;
	}
	
	private Object parseValue(Class<?> type, String value) {
		try {
			if (Long.class.equals(type)) {
				return Long.valueOf(value);
			} else if (Double.class.equals(type)) {
				return Double.valueOf(value.replace(',', '.')); // TODO check if this can be handled using the user's locale
			} else if (BigDecimal.class.equals(type)) {
				return new BigDecimal(value.replace(',', '.')); // TODO check if this can be handled using the user's locale
			} else {
				return value;
			}
		} catch (Exception ex) {
			LOG.warn(String.format("Could not parse '%s' from value '%s', returning value.", type.getName(), value));
			return value;
		}
	}
	
}
