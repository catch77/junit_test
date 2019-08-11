package sales;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class SalesApp {

	SalesReportDao salesReportDao = new SalesReportDao();

	public void generateSalesActivityReport(String salesId, int maxRow, boolean isNatTrade, boolean isSupervisor) {

		SalesDao salesDao = new SalesDao();

		List<SalesReportData> filteredReportDataList = new ArrayList<SalesReportData>();
		
		if (salesId == null) {
			return;
		}

		Sales sales = getSalesBySalesId(salesId, salesDao);

		if (isSalesOutOfDate(sales)) return;

		List<SalesReportData> reportDataList = getSalesReportData(isSupervisor, filteredReportDataList, sales);

		getFilteredReportDateList(maxRow, reportDataList);

		List<String> headers = getHeaders(isNatTrade);

		SalesActivityReport report = this.generateReport(headers, reportDataList);
		
		EcmService ecmService = new EcmService();
		ecmService.uploadDocument(report.toXml());
		
	}

	protected Sales getSalesBySalesId(String salesId, SalesDao salesDao) {
		return salesDao.getSalesBySalesId(salesId);
	}

	protected boolean isSalesOutOfDate(Sales sales) {
		Date today = new Date();
		if (today.after(sales.getEffectiveTo())
				|| today.before(sales.getEffectiveFrom())){
			return true;
		}
		return false;
	}

	protected void getFilteredReportDateList(int maxRow, List<SalesReportData> reportDataList) {
		List<SalesReportData> filteredReportDataList;
		List<SalesReportData> tempList = new ArrayList<SalesReportData>();
		for (int i=0; i < reportDataList.size() || i < maxRow; i++) {
			tempList.add(reportDataList.get(i));
		}
		filteredReportDataList = tempList;
	}

	protected List<SalesReportData> getSalesReportData(boolean isSupervisor, List<SalesReportData> filteredReportDataList, Sales sales) {
		List<SalesReportData> reportDataList = salesReportDao.getReportData(sales);

		for (SalesReportData data : reportDataList) {
			if ("SalesActivity".equalsIgnoreCase(data.getType())) {
				if (data.isConfidential()) {
					if (isSupervisor) {
						filteredReportDataList.add(data);
					}
				}else {
					filteredReportDataList.add(data);
				}
			}
		}
		return reportDataList;
	}

	protected List<String> getHeaders(boolean isNatTrade) {
		List<String> headers = null;
		if (isNatTrade) {
			headers = Arrays.asList("Sales ID", "Sales Name", "Activity", "Time");
		} else {
			headers = Arrays.asList("Sales ID", "Sales Name", "Activity", "Local Time");
		}
		return headers;
	}

	protected SalesActivityReport generateReport(List<String> headers, List<SalesReportData> reportDataList) {
		// TODO Auto-generated method stub
		return null;
	}

}
