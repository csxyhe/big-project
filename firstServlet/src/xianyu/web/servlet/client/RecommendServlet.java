//本网站中最难的部分，推荐系统
package xianyu.web.servlet.client;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import xianyu.Exception.ListProductException;
import xianyu.domain.Product;
import xianyu.domain.User;
import xianyu.service.BrowseService;
import xianyu.service.OrderService;
import xianyu.service.ProductService;
import java.math.*;

public class RecommendServlet extends HttpServlet{
	public static void getTopk_index(double[] array,int[]arrayIndex,int k) {
		//输入数组array，返回topk对应的索引排序即arrayIndex[:k]是我们的target
		for(int j=0;j<k;j++) {
			double temp;
			int index_temp;
			int target_index=j;
			double max=array[j];
			for(int i=j;i<array.length;i++) {
				if(array[i]>max) {
					target_index=i;
					max=array[i];
				}
			}
			temp=array[target_index];
			array[target_index]=array[j];
			array[j]=temp;//交换最大值和0位的值
			index_temp=arrayIndex[target_index];
			arrayIndex[target_index]=arrayIndex[j];
			arrayIndex[j]=index_temp;
			//交换两个索引值
		}
	}
	private static final long serialVersionUID = 1L;
	public void doGet(HttpServletRequest request,HttpServletResponse response)
		throws IOException, ServletException{
		//先判断用户登陆没有
		User user = (User)request.getSession(false).getAttribute("user");
		if(user!=null) {
			int userid = user.getId();
			List<Product> pl = new ArrayList<Product>();
			ProductService ps = new ProductService();
			OrderService os = new OrderService();
			List<String> indexPidMap = new ArrayList<String>();//管理一个商品ID到矩阵中行列索引的映射
			try {
				pl = ps.listAll();
				int num = pl.size();
				//记录下该用户对于每件商品的兴趣度。key为pid对应索引，value为用户对该商品的兴趣度
				//兴趣度每浏览一次+1，每购买一件加2
				int userInterest[] = new int[num];
				int resIndex[] = new int[num];//用来储存我们要选的k个商品的索引，初始为正序
				int C[][] = new int[num][num];//共现矩阵C,初始化为全0（是个对称矩阵） W矩阵的分子（元素表示喜欢i且j的人数）
				double M[][] = new double[num][num];// W矩阵的分母
				double W[][] = new double[num][num];//W矩阵
				int interestPerProduct[] = new int[num];//按照索引记录下每件商品有多少人感兴趣（购买）
				double res[]=new double[num];//获得该用户对于每件商品的打分
				for (int i=0;i<num;i++) {
					indexPidMap.add(pl.get(i).getP_id());
				}
				List<Object[]> uidpidbn = new ArrayList<Object[]>();//userid+pid+buynum
				try {
					uidpidbn = os.returnUPB_recommend_need();
					for(int i=0;i<uidpidbn.size();i++) {
						//1.获得矩阵C
						//2.获得interestPerProduct
						int userid_temp_i = (int) uidpidbn.get(i)[0];
						int index_temp_i = indexPidMap.indexOf(uidpidbn.get(i)[1]);
						if(userid_temp_i==userid) {//统计用户个人的喜好度（购买方面）
							userInterest[index_temp_i]+=(int)uidpidbn.get(i)[2]*2;
						}
						interestPerProduct[index_temp_i]+=1;//有购买行为，+1
						for(int j=0;j<uidpidbn.size();j++) {
							int userid_temp_j = (int) uidpidbn.get(j)[0];
							int index_temp_j = indexPidMap.indexOf(uidpidbn.get(j)[1]);
							if(index_temp_i!=index_temp_j&&userid_temp_i==userid_temp_j) {//该用户同时购买两种商品（交集）
								C[index_temp_i][index_temp_j]+=1;
							}
						}
					}
					//获得矩阵M（元素表示喜欢i或者j的人数开根号）
					//获得W矩阵 num*num
					for(int i=0;i<num;i++){
						resIndex[i]=i;//初始化，正序
						for(int j=0;j<=i;j++) {
							M[i][j]= Math.sqrt(interestPerProduct[i]+interestPerProduct[j]-C[i][j]);
							W[i][j]=C[i][j]/M[i][j];
							M[num-i-1][num-j-1]=M[i][j];
							W[num-i-1][num-j-1]=W[i][j];
						}
					}
					BrowseService bs = new BrowseService();
					List<Object[]> mbs = bs.returnMB_recommend(userid);//获得一个月内该用户浏览的所有pid+统计数量
					for(int i=0;i<mbs.size();i++) {
						int index_temp = indexPidMap.indexOf(mbs.get(i)[0]);
						Number buynum_temp = (Number)mbs.get(i)[1];
						userInterest[index_temp]+=buynum_temp.intValue();
					}//至此，该用户已知兴趣度计算完毕
					for(int i=0;i<num;i++) {
						for(int j=0;j<num;j++) {
							res[i]+=W[i][j]*userInterest[j];
						}
					}//计算完用户对所有商品的兴趣度了
					//排序，选出前四（直接排成一排算了）
					int sort_k=4;
					getTopk_index(res,resIndex,sort_k);
					List<Product> recom_list = new ArrayList<Product>();
					for(int i=0;i<sort_k;i++) {
						recom_list.add(pl.get(resIndex[i]));
					}
					request.setAttribute("recom_list", recom_list);
					request.getRequestDispatcher("/client/index.jsp").forward(request, response);
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} catch (ListProductException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public void doPost(HttpServletRequest request,HttpServletResponse response)
			throws IOException, ServletException{
		doGet(request,response);
	}
}
