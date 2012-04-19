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
