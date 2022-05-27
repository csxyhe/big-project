//����վ�����ѵĲ��֣��Ƽ�ϵͳ
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
		//��������array������topk��Ӧ����������arrayIndex[:k]�����ǵ�target
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
			array[j]=temp;//�������ֵ��0λ��ֵ
			index_temp=arrayIndex[target_index];
			arrayIndex[target_index]=arrayIndex[j];
			arrayIndex[j]=index_temp;
			//������������ֵ
		}
	}
	private static final long serialVersionUID = 1L;
	public void doGet(HttpServletRequest request,HttpServletResponse response)
		throws IOException, ServletException{
		//���ж��û���½û��
		User user = (User)request.getSession(false).getAttribute("user");
		if(user!=null) {
			int userid = user.getId();
			List<Product> pl = new ArrayList<Product>();
			ProductService ps = new ProductService();
			OrderService os = new OrderService();
			List<String> indexPidMap = new ArrayList<String>();//����һ����ƷID������������������ӳ��
			try {
				pl = ps.listAll();
				int num = pl.size();
				//��¼�¸��û�����ÿ����Ʒ����Ȥ�ȡ�keyΪpid��Ӧ������valueΪ�û��Ը���Ʒ����Ȥ��
				//��Ȥ��ÿ���һ��+1��ÿ����һ����2
				int userInterest[] = new int[num];
				int resIndex[] = new int[num];//������������Ҫѡ��k����Ʒ����������ʼΪ����
				int C[][] = new int[num][num];//���־���C,��ʼ��Ϊȫ0���Ǹ��Գƾ��� W����ķ��ӣ�Ԫ�ر�ʾϲ��i��j��������
				double M[][] = new double[num][num];// W����ķ�ĸ
				double W[][] = new double[num][num];//W����
				int interestPerProduct[] = new int[num];//����������¼��ÿ����Ʒ�ж����˸���Ȥ������
				double res[]=new double[num];//��ø��û�����ÿ����Ʒ�Ĵ��
				for (int i=0;i<num;i++) {
					indexPidMap.add(pl.get(i).getP_id());
				}
				List<Object[]> uidpidbn = new ArrayList<Object[]>();//userid+pid+buynum
				try {
					uidpidbn = os.returnUPB_recommend_need();
					for(int i=0;i<uidpidbn.size();i++) {
						//1.��þ���C
						//2.���interestPerProduct
						int userid_temp_i = (int) uidpidbn.get(i)[0];
						int index_temp_i = indexPidMap.indexOf(uidpidbn.get(i)[1]);
						if(userid_temp_i==userid) {//ͳ���û����˵�ϲ�öȣ������棩
							userInterest[index_temp_i]+=(int)uidpidbn.get(i)[2]*2;
						}
						interestPerProduct[index_temp_i]+=1;//�й�����Ϊ��+1
						for(int j=0;j<uidpidbn.size();j++) {
							int userid_temp_j = (int) uidpidbn.get(j)[0];
							int index_temp_j = indexPidMap.indexOf(uidpidbn.get(j)[1]);
							if(index_temp_i!=index_temp_j&&userid_temp_i==userid_temp_j) {//���û�ͬʱ����������Ʒ��������
								C[index_temp_i][index_temp_j]+=1;
							}
						}
					}
					//��þ���M��Ԫ�ر�ʾϲ��i����j�����������ţ�
					//���W���� num*num
					for(int i=0;i<num;i++){
						resIndex[i]=i;//��ʼ��������
						for(int j=0;j<=i;j++) {
							M[i][j]= Math.sqrt(interestPerProduct[i]+interestPerProduct[j]-C[i][j]);
							W[i][j]=C[i][j]/M[i][j];
							M[num-i-1][num-j-1]=M[i][j];
							W[num-i-1][num-j-1]=W[i][j];
						}
					}
					BrowseService bs = new BrowseService();
					List<Object[]> mbs = bs.returnMB_recommend(userid);//���һ�����ڸ��û����������pid+ͳ������
					for(int i=0;i<mbs.size();i++) {
						int index_temp = indexPidMap.indexOf(mbs.get(i)[0]);
						Number buynum_temp = (Number)mbs.get(i)[1];
						userInterest[index_temp]+=buynum_temp.intValue();
					}//���ˣ����û���֪��Ȥ�ȼ������
					for(int i=0;i<num;i++) {
						for(int j=0;j<num;j++) {
							res[i]+=W[i][j]*userInterest[j];
						}
					}//�������û���������Ʒ����Ȥ����
					//����ѡ��ǰ�ģ�ֱ���ų�һ�����ˣ�
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
