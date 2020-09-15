package Controller;

import smartbi.catalogtree.ICatalogElement;
import smartbi.sdk.ClientConnector;
import smartbi.sdk.service.catalog.CatalogService;
import smartbi.sdk.service.combinedquery.CombinedReport;
import smartbi.sdk.service.combinedquery.CombinedReportService;
import smartbi.sdk.service.simplereport.ReportData;
import smartbi.sdk.service.simplereport.SimpleReportService;

import java.util.List;

public class Updater {

    public static void main(String[] args) {
        String url = "http://110.0.170.88:9083/smartbi";
        ClientConnector conn = new ClientConnector(url);

        try {
            // 第一次调用必须建立一个连接，后续调用则不必再建连接
            boolean ret = conn.open("32311", "aAbc123456!");
            if (ret) {
                CatalogService catalogService = new CatalogService(conn);
                List rootElements = catalogService.getRootElements();


                /**
                 * type - 资源类型，目前具有的类型为:
                 * 1.可视化查询:BUSINESS_VIEW
                 * 2.SQL查询:TEXT_BUSINESS_VIEW
                 * 3.存储过程查询:PROC_BUSINESS_VIEW
                 * 4.原生SQL查询：RAWSQL_BUSINESS_VIEW
                 * 5.灵活分析:SIMPLE_REPORT
                 * 6.组合分析：COMBINED_QUERY
                 * 7.透视分析：INSIGHT
                 * 8.仪表分析: Dashboard
                 * 9.地图分析: DashboardMap
                 * 10.电子表格：SPREADSHEET_REPORT
                 * 11.多维分析：OLAP_REPORT
                 * 12.portal页面: PAGE
                 * 13.WEB链接：URL
                 */
//                ICatalogElement public_analysis = catalogService.getChildElementsWithPurviewType("Iee801fbd227e43eb01583d989ca32e84");
//                System.out.println("public_analysis"+public_analysis);
////
                List<? extends ICatalogElement> simple_report = catalogService.getCatalogElementByType("COMBINED_QUERY");
//                List<? extends ICatalogElement> simple_report = catalogService.getCatalogElementByType("SIMPLE_REPORT");

                for (int n = 0; n < simple_report.size(); n++) {
                    System.out.println(simple_report.get(n).toString());
                }
                ICatalogElement settleRecord = catalogService.getCatalogElementById("Iee801fbd227e43eb01583d989ca32e84");
                System.out.println("settleRecord" +settleRecord.toString());

                //打开一个报表
                CombinedReportService combinedReportService = new CombinedReportService(conn);
                CombinedReport settleReport = combinedReportService.openReport("Iee801fbd227e43eb01583d989ca32e84");
                settleReport.execute(100);
                settleReport.open("Iee801fbd227e43eb01583d989ca32e84");
                ReportData page = settleReport.getPage(10);
                System.out.println(page);



            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
