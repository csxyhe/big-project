// 用于处理添加商品请求
package xianyu.web.servlet.business;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.lang.reflect.InvocationTargetException;
import xianyu.service.ProductService;
import xianyu.Exception.AddProductException;
import xianyu.domain.Product;
import xianyu.domain.User;
import xianyu.utils.IdUtils;
import xianyu.utils.FileUploadUtils;
import xianyu.utils.GetRealIPUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.fileupload.*;
import org.apache.commons.fileupload.disk.*;
import org.apache.commons.fileupload.servlet.*;
import org.apache.commons.io.IOUtils;
import org.apache.commons.beanutils.BeanUtils;
public class AddProductServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Product p = new Product();
		// 商品id是要另外生成的
		String p_id = IdUtils.getUUID();
		User user = (User) request.getSession().getAttribute("user");
		int user_id = user.getId();
		String role = user.getRole();
		String ip = GetRealIPUtils.getIP(request);
		String b_id = Integer.toString(user.getId());
		Map<String,String> map = new HashMap<String,String>();
		map.put("p_id", p_id);
		map.put("b_id", b_id);
		// 1.创建DiskFileItemFactory工厂对象
		DiskFileItemFactory factory = new DiskFileItemFactory();
		// 2.设置文件缓存目录及缓存数据大小阈值为10M
		factory.setRepository(new File(this.getServletContext().getRealPath("/temp")));// temp是要建立的真实文件夹
		factory.setSizeThreshold(1024*1024*10);
		// 3.创建ServletFileUpload对象，防止上传过程中出现中文乱码
		ServletFileUpload fileupload = new ServletFileUpload(factory);
		fileupload.setHeaderEncoding("utf-8");
		
		try {
			// 4.解析request得到所有的FileItem，再分普通字段和上传文件两种情况来处理
			List<FileItem> fileitems = fileupload.parseRequest(request);
			// 遍历列表
			for (FileItem fileitem : fileitems) {
				if(fileitem.isFormField()) {
					// 普通字段，直接获取键值，并封装进集合map中
					String name = fileitem.getFieldName();
					String value = fileitem.getString("utf-8");
					map.put(name, value);
				}else {
					// 上传字段
					// 获得上传文件的真实名称
					String fileName = fileitem.getName();
					fileName = FileUploadUtils.subFileName(fileName);
					// 获取一个用于临时存储的随机文件名（唯一）
					String randomName = FileUploadUtils.generateRandomFileName(fileName);
					// 得到随机目录
					String randomDir = FileUploadUtils.generateRandomDir(randomName);
					//拼接成该图片存储的父目录
					String imgurl_parent ="/productImg" + randomDir;
					File parentDir = new File(this.getServletContext().getRealPath(imgurl_parent));
					//如果父目录还没有，就新建
					if(!parentDir.exists()) {
						parentDir.mkdirs();
					}
					// 拼接图片存放的地址
					String imgurl = imgurl_parent + '/' + randomName;
					System.out.println(parentDir.getPath());
					map.put("imgurl", imgurl);
					// 将图片从本机某位置复制到temp文件夹中
					IOUtils.copy(fileitem.getInputStream(), new FileOutputStream(new File(parentDir, randomName)));
					fileitem.delete();// 删除存放在temp文件夹中的临时文件
				}
			}
		} catch (FileUploadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			// 将数据封装到javabean : p中
			BeanUtils.populate(p, map);
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ProductService ps = new ProductService();
		try {
			ps.addProduct(p,user_id,role,"添加商品",ip);
			// 转发请求
			response.sendRedirect(request.getContextPath() + "/listProduct");
			return;
		} catch (AddProductException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			response.getWriter().write(e.getMessage());
			return;
		}
		
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request,response);
	}
	
}
