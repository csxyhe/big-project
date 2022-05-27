package xianyu.web.servlet.business;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.lang.reflect.InvocationTargetException;
import xianyu.service.ProductService;
import xianyu.domain.Product;
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
import xianyu.domain.User;
public class EditProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Product p = new Product();
		
		
		Map<String,String> map = new HashMap<String,String>();
		User user = (User)request.getSession().getAttribute("user");
		int user_id = user.getId();
		String role = user.getRole();
		String ip = GetRealIPUtils.getIP(request);
		map.put("b_id", Integer.toString(user.getId()));
		// 1.����DiskFileItemFactory��������
		DiskFileItemFactory factory = new DiskFileItemFactory();
		// 2.�����ļ�����Ŀ¼���������ݴ�С��ֵΪ10M
		factory.setRepository(new File(this.getServletContext().getRealPath("/temp")));// temp��Ҫ��������ʵ�ļ���
		factory.setSizeThreshold(1024*1024*10);
		// 3.����ServletFileUpload���󣬷�ֹ�ϴ������г�����������
		ServletFileUpload fileupload = new ServletFileUpload(factory);
		fileupload.setHeaderEncoding("utf-8");
		
		try {
			// 4.����request�õ����е�FileItem���ٷ���ͨ�ֶκ��ϴ��ļ��������������
			List<FileItem> fileitems = fileupload.parseRequest(request);
			// �����б�
			for (FileItem fileitem : fileitems) {
				if(fileitem.isFormField()) {
					// ��ͨ�ֶΣ�ֱ�ӻ�ȡ��ֵ������װ������map��
					String name = fileitem.getFieldName();
					String value = fileitem.getString("utf-8");
					map.put(name, value);
				}else {
					// �ϴ��ֶ�
					// ����ϴ��ļ�����ʵ����
					String fileName = fileitem.getName();
					fileName = FileUploadUtils.subFileName(fileName);
					// ��ȡһ��������ʱ�洢������ļ�����Ψһ��
					String randomName = FileUploadUtils.generateRandomFileName(fileName);
					// �õ����Ŀ¼
					String randomDir = FileUploadUtils.generateRandomDir(randomName);
					//ƴ�ӳɸ�ͼƬ�洢�ĸ�Ŀ¼
					String imgurl_parent ="/productImg" + randomDir;
					File parentDir = new File(this.getServletContext().getRealPath(imgurl_parent));
					//�����Ŀ¼��û�У����½�
					if(!parentDir.exists()) {
						parentDir.mkdirs();
					}
					// ƴ��ͼƬ��ŵĵ�ַ
					String imgurl = imgurl_parent + '/' + randomName;
					map.put("imgurl", imgurl);
					// ��ͼƬ�ӱ���ĳλ�ø��Ƶ�temp�ļ�����
					IOUtils.copy(fileitem.getInputStream(), new FileOutputStream(new File(parentDir, randomName)));
					fileitem.delete();// ɾ�������temp�ļ����е���ʱ�ļ�
				}
			}
		} catch (FileUploadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			// �����ݷ�װ��javabean : p��
			BeanUtils.populate(p, map);
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// ����service����޸���Ʒ�Ĳ���
		ProductService service = new ProductService();
		service.editProduct(p,user_id,role,"�޸���Ʒ",ip);
		// ת������
		response.sendRedirect(request.getContextPath() + "/listProduct");
		return;
	}
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request,response);
	}
}
