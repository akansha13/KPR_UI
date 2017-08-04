package stepdefinition.khelplay.mobile.api.txns;

import java.util.ArrayList;

import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.khelplay.mobile.api.txns.TransactionApiResponse;
import com.khelplay.utils.DateAndTimeFormatter;

import cucumber.api.java.en.When;

public class TransactionApiStepDefinitions {

	TransactionApiResponse transactionApiResponse = new TransactionApiResponse();

	ArrayList<Object> apiDataList = new ArrayList<Object>();
	private static Logger LOGGER = LoggerFactory.getLogger(TransactionApiStepDefinitions.class);

	@When("^player data of (.*) transactionDetails between (.*) to (.*)$")
	public void verifyFetchTransactionDetailsApi(String txnType, String FromDate, String ToDate) throws Throwable {
		String toDate = DateAndTimeFormatter.getCurrentDateInExpectedFormat(ToDate, "dd/MM/yyyy");
		String fromDate = DateAndTimeFormatter.getCurrentDateInExpectedFormat(FromDate, "dd/MM/yyyy");
		apiDataList = (ArrayList<Object>) transactionApiResponse.callTransactionDetails(txnType, fromDate, toDate);
		if (transactionApiResponse.flag) {
			LOGGER.info("Let's verify with DB");
		} else {
			Assert.fail("Response not Generated");
		}
	}

}
