package de.boksa.jasper.rt.util;

import java.util.Map;

import net.sf.jasperreports.engine.JRDataset;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRValueParameter;
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
	
	@Override
	public JRQueryExecuter createQueryExecuter(JRDataset dataset, Map<java.lang.String,? extends JRValueParameter> parameters) throws JRException {
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
