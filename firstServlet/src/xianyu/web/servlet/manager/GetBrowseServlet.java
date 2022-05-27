package xianyu.web.servlet.manager;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import xianyu.service.BrowseService;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import xianyu.domain.User;
import xianyu.utils.GetRealIPUtils;

public class GetBrowseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String operationType = request.getParameter("operation");
		String year = request.getParameter("year");
		String month = request.getParameter("month");
		if (year.length()==0 || month.length()==0) {
			// 如果输入的日期范围不合法，或者没有输入日期范围的，默认输出本月的数据
			Calendar now = Calendar.getInstance();
			year = now.get(Calendar.YEAR) + "";
			int mon = now.get(Calendar.MONTH) + 1;
			if (mon < 10) {
				month = "0" + mon;
			} else {
				month = "" + mon;
			}
		}
		User user = (User) request.getSession().getAttribute("user");
		int userid = user.getId();
		String role = user.getRole();
		String ip = GetRealIPUtils.getIP(request);
		BrowseService bs = new BrowseService();
		List<Object[]> month_browse = new ArrayList<Object[]>();
		try {
			String op_name;
			if(operationType.equals("search")) {
				op_name = "查询日志";
			}else {
				op_name="下载日志";
			}
			month_browse = bs.returnmonth_all(year, month, userid, role, op_name,year+"年"+month+"月浏览日志", ip);
			if (operationType.equals("search")) {
				// 将数据展示在页面上
				request.setAttribute("month_browse", month_browse);
				request.getRequestDispatcher("/admin/log/browse_list.jsp").forward(request, response);
			} else {
				// 下载数据
				String filename = year + "年" + month + "月浏览日志.csv";
				response.setContentType(this.getServletContext().getMimeType(filename));
				response.setHeader("Content-Disposition",
						"attachement;filename=" + new String(filename.getBytes("GBK"), "iso8859-1"));
				response.setCharacterEncoding("gbk");
				PrintWriter out = response.getWriter();
				out.println("用户ID,销售ID,商品ID,开始时间,结束时间,持续时长（秒）");// 写入抬头
				
				 for(int i=0;i<month_browse.size();i++) {//按行写入数据 
					 Object[] arr = month_browse.get(i);//读出List中的一行
					 out.println(arr[0]+","+arr[1]+","+arr[2]+","+arr[3]+","+ arr[4]+"," +arr[5]); 
				 }
				out.flush();// （一般来说，要等到缓冲区满才输出）强制把数据输出，清空缓存区
				out.close();// 关闭文件

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
}
