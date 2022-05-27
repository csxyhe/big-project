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
	// 添加商品
	public void addProduct(Product p, int op_id, String role, String op_name, String ip) throws AddProductException{
		try {
			od.addOperation(op_id, role, op_name, p.getP_id(), ip);
			pd.addProduct(p);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new AddProductException("添加商品失败");
		}
	}
	// 查找所有商品信息（销售人员版本）
		public List<Product> listAll_B(int b_id) throws ListProductException{
			try {
				return pd.listAll_B(b_id);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				throw new ListProductException("查询商品信息失败");
			}
		}
	// 查找所有商品信息
	public List<Product> listAll() throws ListProductException{
		try {
			return pd.listAll();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new ListProductException("查询商品信息失败");
		}
	}
	// 多条件查询
	public List<Product> findProductByManyCondition(String id, String name,String category,String minprice,String maxprice)
		throws ListProductException{
		List<Product> ps = null;
		try {
			ps =  pd.findProductByManyCondition(id, name, category, minprice, maxprice);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new ListProductException("查询商品信息失败");
		}
		return ps;
	}
	// 多条件查询的销售人员版本
	public List<Product> findProductByManyCondition_B(String p_id,int b_id, String name,String category,String minprice,String maxprice, String role, String op_name, String ip) 
			throws ListProductException{
		List<Product> ps = null;
		try {
			String op_content="";
			if(p_id.length()!=0) {
				op_content +="编号"+p_id+",";
			}
			if(name.length()!=0) {
				op_content +="名字含"+name+"',";
			}
			if(category.length()!=0) {
				op_content += "类别为"+category+",";
			}
			if(minprice.length()!=0) {
				op_content +="单价不低于"+minprice+",";
			}
			if(maxprice.length()!=0) {
				op_content +="单价不高于"+maxprice;
			}
			od.addOperation(b_id, role, op_name, op_content, ip);
			ps = pd.findProductByManyCondition_B(p_id, b_id, name, category, minprice, maxprice);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ps;
	}
	
	
	// 根据id查询
	public Product findProductById(String id) throws SQLException{
		return pd.findProductById(id);
	}
	// 修改商品信息
	public void editProduct(Product p, int op_id, String role, String op_name, String ip) {
		try {
			od.addOperation(op_id, role, op_name, p.getP_id(), ip);
			pd.editProduct(p);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	// 后台系统专用，用于根据id删除商品信息
	public void deleteProduct(String id, int op_id, String role, String op_name, String ip) {
		try {
			od.addOperation(op_id, role, op_name, id, ip);
			pd.deleteProduct(id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("后台系统根据id删除商品信息失败！");
		}
	}
	// 通过商品名称进行模糊查询，用于客户端输入信息进行检索
	// 客户端实现按页查找并显示商品信息
	public PageBean findProductByPage(int currentPage,int currentNum,String category) {
		PageBean bean = new PageBean();
		bean.setCurrentNum(currentNum);
		bean.setCurrentPage(currentPage);
		bean.setCategory(category);
		try {
			// 获取该类别的所有商品数
			int totalCount = pd.findAllCount(category);
			bean.setTotalNum(totalCount);
			// 获取总页数
			int totalPage = (int) Math.ceil(totalCount * 1.0 / currentNum);
			bean.setTotalPage(totalPage);
			// 获取当前页数据
			List<Product> ps = pd.findByPage(totalCount,currentPage, currentNum,category);
			bean.setPs(ps);
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return bean;
	}
	// 根据关键词进行商品的模糊查询
	public PageBean findProductByKeyword(int currentPage,int currentNum,String textfield) {
		PageBean bean = new PageBean();
		bean.setCurrentNum(currentNum);
		bean.setCurrentPage(currentPage);
		bean.setKeyword(textfield);
		// 获取具有该关键词的商品总数
		try {
			int totalCount = pd.findAllCountKeyword(textfield);
			bean.setTotalNum(totalCount);
			// 获取总页数
			int totalPage = (int) Math.ceil(totalCount * 1.0 / currentNum);
			bean.setTotalPage(totalPage);
			// 获取当前页数据
			List<Product> ps = pd.findByKeyword(totalCount,currentPage, currentNum,textfield);
			bean.setPs(ps);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bean;
	}

}
