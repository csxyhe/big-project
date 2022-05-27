package xianyu.web.servlet.manager;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import xianyu.domain.User;
import xianyu.service.OrderService;
import xianyu.utils.GetRealIPUtils;

public class GetUserFigureServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String operationType = request.getParameter("operation");
		User user = (User) request.getSession().getAttribute("user");
		int userid = user.getId();
		String role = user.getRole();
		String ip = GetRealIPUtils.getIP(request);
		OrderService os = new OrderService();
		List<Object[]> figuredata = new ArrayList<Object[]>();
		try {
			String op_name;
			if(operationType.equals("search")) {
				op_name = "查询用户画像";
			}else {
				op_name="下载用户画像";
			}
			figuredata = os.getUserFigureData(userid,role,op_name,ip);
			if (operationType.equals("search")) {
				// 将数据展示在页面上
				request.setAttribute("figuredata", figuredata);
				request.setAttribute("isSearch", true);
				request.getRequestDispatcher("/admin/user/userFigure_list.jsp").forward(request, response);
			} else {
				// 下载数据
				String filename = "用户画像.csv";
				response.setContentType(this.getServletContext().getMimeType(filename));
				response.setHeader("Content-Disposition",
						"attachement;filename=" + new String(filename.getBytes("GBK"), "iso8859-1"));
				response.setCharacterEncoding("gbk");
				PrintWriter out = response.getWriter();
				out.println("用户ID,性别,地域,消费偏好类别,最近一次消费间隔,近期消费次数,近期消费总额");// 写入抬头
				
				 for(int i=0;i<figuredata.size();i++) {//按行写入数据 
					 Object[] arr = figuredata.get(i);//读出List中的一行
					 out.println(arr[0]+","+arr[1]+","+arr[2]+","+arr[3]+","+ arr[4]+"," +arr[5]+","+arr[6]); 
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
