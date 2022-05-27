package xianyu.service;
import java.sql.SQLException;
import java.util.List;
import xianyu.domain.PageBean;
import xianyu.Dao.ProductDao;
import xianyu.Dao.operationDao;
import xianyu.domain.Product;
import xianyu.Exception.*;
public class ProductService {
	private ProductDao pd = new ProductDao();
	private operationDao od = new operationDao();
	// �����Ʒ
	public void addProduct(Product p, int op_id, String role, String op_name, String ip) throws AddProductException{
		try {
			od.addOperation(op_id, role, op_name, p.getP_id(), ip);
			pd.addProduct(p);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new AddProductException("�����Ʒʧ��");
		}
	}
	// ����������Ʒ��Ϣ��������Ա�汾��
		public List<Product> listAll_B(int b_id) throws ListProductException{
			try {
				return pd.listAll_B(b_id);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				throw new ListProductException("��ѯ��Ʒ��Ϣʧ��");
			}
		}
	// ����������Ʒ��Ϣ
	public List<Product> listAll() throws ListProductException{
		try {
			return pd.listAll();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new ListProductException("��ѯ��Ʒ��Ϣʧ��");
		}
	}
	// ��������ѯ
	public List<Product> findProductByManyCondition(String id, String name,String category,String minprice,String maxprice)
		throws ListProductException{
		List<Product> ps = null;
		try {
			ps =  pd.findProductByManyCondition(id, name, category, minprice, maxprice);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new ListProductException("��ѯ��Ʒ��Ϣʧ��");
		}
		return ps;
	}
	// ��������ѯ��������Ա�汾
	public List<Product> findProductByManyCondition_B(String p_id,int b_id, String name,String category,String minprice,String maxprice, String role, String op_name, String ip) 
			throws ListProductException{
		List<Product> ps = null;
		try {
			String op_content="";
			if(p_id.length()!=0) {
				op_content +="���"+p_id+",";
			}
			if(name.length()!=0) {
				op_content +="���ֺ�"+name+"',";
			}
			if(category.length()!=0) {
				op_content += "���Ϊ"+category+",";
			}
			if(minprice.length()!=0) {
				op_content +="���۲�����"+minprice+",";
			}
			if(maxprice.length()!=0) {
				op_content +="���۲�����"+maxprice;
			}
			od.addOperation(b_id, role, op_name, op_content, ip);
			ps = pd.findProductByManyCondition_B(p_id, b_id, name, category, minprice, maxprice);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ps;
	}
	
	
	// ����id��ѯ
	public Product findProductById(String id) throws SQLException{
		return pd.findProductById(id);
	}
	// �޸���Ʒ��Ϣ
	public void editProduct(Product p, int op_id, String role, String op_name, String ip) {
		try {
			od.addOperation(op_id, role, op_name, p.getP_id(), ip);
			pd.editProduct(p);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	// ��̨ϵͳר�ã����ڸ���idɾ����Ʒ��Ϣ
	public void deleteProduct(String id, int op_id, String role, String op_name, String ip) {
		try {
			od.addOperation(op_id, role, op_name, id, ip);
			pd.deleteProduct(id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("��̨ϵͳ����idɾ����Ʒ��Ϣʧ�ܣ�");
		}
	}
	// ͨ����Ʒ���ƽ���ģ����ѯ�����ڿͻ���������Ϣ���м���
	// �ͻ���ʵ�ְ�ҳ���Ҳ���ʾ��Ʒ��Ϣ
	public PageBean findProductByPage(int currentPage,int currentNum,String category) {
		PageBean bean = new PageBean();
		bean.setCurrentNum(currentNum);
		bean.setCurrentPage(currentPage);
		bean.setCategory(category);
		try {
			// ��ȡ������������Ʒ��
			int totalCount = pd.findAllCount(category);
			bean.setTotalNum(totalCount);
			// ��ȡ��ҳ��
			int totalPage = (int) Math.ceil(totalCount * 1.0 / currentNum);
			bean.setTotalPage(totalPage);
			// ��ȡ��ǰҳ����
			List<Product> ps = pd.findByPage(totalCount,currentPage, currentNum,category);
			bean.setPs(ps);
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return bean;
	}
	// ���ݹؼ��ʽ�����Ʒ��ģ����ѯ
	public PageBean findProductByKeyword(int currentPage,int currentNum,String textfield) {
		PageBean bean = new PageBean();
		bean.setCurrentNum(currentNum);
		bean.setCurrentPage(currentPage);
		bean.setKeyword(textfield);
		// ��ȡ���иùؼ��ʵ���Ʒ����
		try {
			int totalCount = pd.findAllCountKeyword(textfield);
			bean.setTotalNum(totalCount);
			// ��ȡ��ҳ��
			int totalPage = (int) Math.ceil(totalCount * 1.0 / currentNum);
			bean.setTotalPage(totalPage);
			// ��ȡ��ǰҳ����
			List<Product> ps = pd.findByKeyword(totalCount,currentPage, currentNum,textfield);
			bean.setPs(ps);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bean;
	}

}
