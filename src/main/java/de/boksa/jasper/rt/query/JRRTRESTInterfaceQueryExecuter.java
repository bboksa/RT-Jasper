package de.boksa.jasper.rt.query;

import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRDataset;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRValueParameter;
import net.sf.jasperreports.engine.query.JRAbstractQueryExecuter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import de.boksa.jasper.rt.data.JRRTRESTInterfaceDataSource;
import de.boksa.jasper.rt.util.JRRTRESTInterfaceQueryExecuterFactory;
import de.boksa.rt.dao.RTTicketDAO;
import de.boksa.rt.model.RTTicket;

public class JRRTRESTInterfaceQueryExecuter extends JRAbstractQueryExecuter {

	private static final Log LOG = LogFactory.getLog(JRRTRESTInterfaceQueryExecuter.class);

	private RTTicketDAO dao;
	
	public JRRTRESTInterfaceQueryExecuter(JRDataset dataset, Map<String, ? extends JRValueParameter> parametersMap) {
		super(dataset, parametersMap);
		dao = (RTTicketDAO) this.getParameterValue(JRRTRESTInterfaceQueryExecuterFactory.PARAMETER_RT_TICKET_DAO);
		
		if (dao == null) {
			LOG.warn("The supplied RTTicketDAO is null.");
		}
		
		this.parseQuery();
	}

	@Override
	public boolean cancelQuery() throws JRException {
		LOG.debug("cancelQuery called");
		return false;
	}

	@Override
	public void close() {
		LOG.debug("close called");		
	}

	@Override
	public JRDataSource createDatasource() throws JRException {
		JRDataSource dataSource = null;
		String queryString = this.getQueryString().trim();
		
		if ((dao != null) && (queryString != null) && (queryString.length() > 0)) {
			
			List<RTTicket> result;
			try {				
				String[] queryParts = queryString.split(" ORDER BY ");
								
				if (queryParts.length > 1) {
					result = dao.findByQuery(queryParts[0], queryParts[1]);
				} else {					
					result = dao.findByQuery(queryParts[0]);
				}
			} catch (Exception ex) {
				throw new JRException(ex);
			}
						
			dataSource = new JRRTRESTInterfaceDataSource(result);  
		}
		
		return dataSource;
	}

	@Override
	protected String getParameterReplacement(String parameterName) {
		LOG.debug("getParameterReplacement called: parameterName = " + parameterName);		
		return null;
	}
	
}
