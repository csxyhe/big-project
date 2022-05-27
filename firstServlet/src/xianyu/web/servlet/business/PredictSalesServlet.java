package xianyu.web.servlet.business;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import xianyu.service.OrderService;
import xianyu.domain.User;
import xianyu.service.BrowseService;

public class PredictSalesServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String p_id = request.getParameter("p_id");
		OrderService os = new OrderService();
		try {
			List<Integer> sales_list = new ArrayList<Integer>();
			sales_list=os.predictSales(p_id);//长度为6，第六个数表示前一天该商品销量
			//分别取alpha=0.6,0.7,0.8,0.9
			double alpha[]= {0.6,0.7,0.8,0.9};
			double y_hat[][] = new double[4][6];//一行表示用一个alpha算出来的
			double var[] = new double[4];//记录方差，取方差最小的alpha作为我们的alpha
			double S_init = (sales_list.get(0)+sales_list.get(1)+sales_list.get(2))/3.0;
			for (int i=0;i<4;i++) {
				for(int j=0;j<6;j++) {
					if(j==0) {
						y_hat[i][j]=S_init;
					}else {
						y_hat[i][j]=alpha[i]*sales_list.get(j-1)+(1-alpha[i])*y_hat[i][j-1];
					}
					var[i] +=(y_hat[i][j]-sales_list.get(j))*(y_hat[i][j]-sales_list.get(j));
				}
			}
			int selected_index = 0;//所选alpha对应索引
			for(int i=1;i<4;i++) {
				if(var[i]<var[selected_index]) {
					selected_index = i;
				}
			}
			sales_list.add((int)(Math.round(alpha[selected_index]*sales_list.get(5)+(1-alpha[selected_index])*y_hat[selected_index][5])));//加个四舍五入
			request.setAttribute("sales_list", sales_list);
			request.getRequestDispatcher("/businessman/products/predict_figure.jsp").forward(request, response);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request,response);
	}
}
