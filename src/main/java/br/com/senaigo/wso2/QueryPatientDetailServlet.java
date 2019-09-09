package br.com.senaigo.wso2;

import br.com.senaigo.wso2.model.Patient;
import org.apache.axiom.om.OMAbstractFactory;
import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.OMFactory;
import org.apache.axiom.om.OMNamespace;
import org.apache.axis2.addressing.EndpointReference;
import org.apache.axis2.client.Options;
import org.apache.axis2.client.ServiceClient;
import org.apache.axis2.context.ConfigurationContext;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.namespace.QName;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class QueryPatientDetailServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private String ENDPOINT_SERVICES_WSO2DSS = "http://localhost:9763/services/wso2health/";
	private String nameSpaceURL = "http://ws.wso2.org/dataservice/samples/health";

	final static Logger LOGGER = Logger.getLogger(QueryPatientDetailServlet.class);

	private OMElement createPayloadPatientDetailsByNumber(String patientNumber) {

		LOGGER.info(String.format("Method createPayloadPatientDetailsByNumber -> %s", patientNumber));

		OMFactory fac = OMAbstractFactory.getOMFactory();
		OMNamespace omNs = fac.createOMNamespace(nameSpaceURL, "ns");
		OMElement patientDetailsByNumber = fac.createOMElement("patientDetailsByNumber", omNs);
		OMElement patientNo = fac.createOMElement("number", omNs);

		patientNo.setText(patientNumber);

		patientDetailsByNumber.addChild(patientNo);

		return patientDetailsByNumber;
	}

	private OMElement createPayloadPatientList() {

		LOGGER.info("Method createPayloadPatientList");

		OMFactory fac = OMAbstractFactory.getOMFactory();
		OMNamespace omNs = fac.createOMNamespace(nameSpaceURL, "ns");
		OMElement patientList = fac.createOMElement("patientListAll", omNs);

		return patientList;
	}

	private List<Patient> parseResultFromDSS2List(OMElement response) {
		List<Patient> patientList = new ArrayList<Patient>();
		Iterator<OMElement> patientItr = response.getChildrenWithName(new QName("patient"));

		while (patientItr.hasNext()) {
			OMElement patientEle = patientItr.next();
			String firstName = patientEle.getFirstChildWithName(new QName(nameSpaceURL, "patient-first-name")).getText();
			String lastName = patientEle.getFirstChildWithName(new QName(nameSpaceURL, "patient-last-name")).getText();
			String phone = patientEle.getFirstChildWithName(new QName(nameSpaceURL, "phone")).getText();
			String city = patientEle.getFirstChildWithName(new QName(nameSpaceURL, "city")).getText();
			String street = patientEle.getFirstChildWithName(new QName(nameSpaceURL, "street")).getText();
			String country = patientEle.getFirstChildWithName(new QName(nameSpaceURL, "country")).getText();

			Patient patient = Patient.builder()
					.firstName(firstName)
					.lastName(lastName)
					.phone(phone)
					.city(city)
					.street(street)
					.country(country)
					.build();

			patientList.add(patient);

		}

		return patientList;

	}

	private List<Patient> operation(OMElement payLoad, String operation,HttpServletRequest request, HttpServletResponse response){
		OMElement result = null;

		try{

			ConfigurationContext cc = null;
			ServiceClient serviceclient = new ServiceClient(null, null);
			Options opt = new Options();
			opt.setTo(new EndpointReference(ENDPOINT_SERVICES_WSO2DSS));

			opt.setAction(operation);
			serviceclient = new ServiceClient(cc, null);
			serviceclient.setOptions(opt);
			result = serviceclient.sendReceive(payLoad);
			List patients = parseResultFromDSS2List(result);
			request.setAttribute("patientList", patients);

			return patients;

		}catch (Exception e){
			LOGGER.error("Operation fail: "+e.getMessage());
		}
		return null;
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		OMElement patientDetails = null;
		List<Patient> patients = new ArrayList<>();
		response.setContentType("text/html");
		try {
			String patientNumber = request.getParameter("patientNumber");

			if (patientNumber != null && !patientNumber.isEmpty()) {

				OMElement payload = createPayloadPatientDetailsByNumber(patientNumber);
				patients = operation(payload, "urn:patientDetailsByNumber",request,response);
				request.setAttribute("patientList", patients);
				request.setAttribute("number", patientNumber);

			}else{

				OMElement payload = createPayloadPatientList();
				patients = operation(payload, "urn:patientListAll",request,response);
			}

			request.setAttribute("patientList", patients);
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/patientInfoPage.jsp");
			rd.forward(request, response);

		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		    RequestDispatcher rd = getServletContext().getRequestDispatcher("/getPatientDetails.jsp");
		    PrintWriter out= response.getWriter();
		    out.println("<font color=red>Error While Quering Records : "+e.getMessage()+"</font>");
		    rd.include(request, response);
		}
	}

}
