package com.ces.controller;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.FileUploadBase.FileSizeLimitExceededException;
import org.apache.tomcat.util.http.fileupload.FileUploadBase.SizeLimitExceededException;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.apache.tomcat.util.http.fileupload.servlet.ServletRequestContext;
import org.lnson.util.FileExtUtils;

import com.ces.model.User;
import com.ces.service.UserService;
import com.ces.service.impl.UserServiceImpl;

public class InsertServlet extends HttpServlet {

	private static final long serialVersionUID = -7961620176113790222L;

	public InsertServlet() {
		System.out.println("InsertServlet构造函数");
	}

	@Override
	public void init() throws ServletException {
		System.out.println("InsertServlet对象被创建");
	}

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String username = null;
		String password = null;
		String gender = null;
		String birthday = null;
		String address = null;
		String balance = null;
		String picture = null;

		// 获得参数
		String errMsg = "";
		List<FileItem> fileItems = getParameters(request, response, errMsg);
		if (fileItems == null || fileItems.size() <= 0) {
			return;
		}
		// 上传文件
		List<FileItem> uploadfiles = fileItems.stream().filter(item -> !item.isFormField())
				.collect(Collectors.toList());
		if (uploadfiles == null || uploadfiles.size() <= 0)
			return;
		for (FileItem item : uploadfiles) {
			picture = uploadfile(item, errMsg);
			break;
		}
		// 获取表单字段
		List<FileItem> formfields = fileItems.stream().filter(item -> item.isFormField()).collect(Collectors.toList());
		if (formfields == null || formfields.size() <= 0)
			return;
		for (FileItem item : formfields) {
			String fieldName = item.getFieldName();
			String fieldValue = item.getString();
			fieldValue = new String(fieldValue.getBytes("ISO-8859-1"), "UTF-8");
			if ("username".equalsIgnoreCase(fieldName)) username=fieldValue;
			if ("password".equalsIgnoreCase(fieldName)) password=fieldValue;
			if ("gender".equalsIgnoreCase(fieldName)) gender=fieldValue;
			if ("birthday".equalsIgnoreCase(fieldName)) birthday=fieldValue;			
			if ("address".equalsIgnoreCase(fieldName)) address=fieldValue;
			if ("balance".equalsIgnoreCase(fieldName)) balance=fieldValue;
		}
		
		//保存到数据库
		User user = new User();
		user.setUserName(username);
		user.setPassWord(password);
		user.setGender(Integer.valueOf(gender));
		try {
			user.setBirthday(new SimpleDateFormat("yyyy-MM-dd").parse(birthday));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		user.setAddress(address);
		user.setBalance(new BigDecimal(balance));
		user.setPicture(picture);

		UserService _userService = new UserServiceImpl();
		int row = _userService.saveUser(user);

		String rootPath = request.getContextPath();
		if (row >= 1) {
			response.sendRedirect(rootPath + "/main.html");
		}
	}

	@Override
	public void destroy() {
		super.destroy();
		System.out.println("InsertServlet对象被销毁");
	}

	public List<FileItem> getParameters(HttpServletRequest req, HttpServletResponse resp, String errMsg) {
		// 创建接收文件的工厂类
		DiskFileItemFactory factory = new DiskFileItemFactory();

		// 创建文件解析对象
		ServletFileUpload upload = new ServletFileUpload(factory);

		// 文件名中文乱码解决：(以下两种方式不能解决form表单普通中文文本的乱码问题)
		// 方式1
		// req.setCharacterEncoding("UTF-8");// 设置解析request请求的编码格式【在过滤器中已经设置过】
		// 方式2
		// upload.setHeaderEncoding("UTF-8");// 设置解析request请求的编码方式

		// 设置单个文件的最大值，以字节为单位
		upload.setFileSizeMax(1 * 1024 * 1024);

		// 上传文件总大小限制
		upload.setSizeMax(1 * 1024 * 1024 * 3);

		// 解析HttpServletRequest对象，获得表单中的每一文件项（包含普通文本域）
		List<FileItem> srcFileList = null;
		try {
			srcFileList = upload.parseRequest(new ServletRequestContext(req));
		} catch (Exception e) {
			StringBuffer sb = new StringBuffer();
			sb.append("文件上传失败，原因：");

			if (e instanceof FileSizeLimitExceededException) {
				sb.append("上传单个文件的大小超过限制");
			} else if (e instanceof SizeLimitExceededException) {
				sb.append("上传所有文件的总大小超过限制");
			} else {
				e.printStackTrace();
			}
			errMsg = sb.toString();
			System.out.println(errMsg);

			return new ArrayList<FileItem>();
		}
		return srcFileList;
	}
	
	public String uploadfile(
			/* HttpServletRequest req, HttpServletResponse resp, */ FileItem fileItem, String errMsg) {
/*
		// 创建接收文件的工厂类
		DiskFileItemFactory factory = new DiskFileItemFactory();

		// 创建文件解析对象
		ServletFileUpload upload = new ServletFileUpload(factory);

		// 文件名中文乱码解决：
		// 方式1
		// req.setCharacterEncoding("UTF-8");// 设置解析request请求的编码格式【在过滤器中已经设置过】
		// 方式2
		// upload.setHeaderEncoding("UTF-8");// 设置解析request请求的编码方式

		// 解析HttpServletRequest对象，获得表单中的每一文件项（包含普通文本域）
		List<FileItem> fileList = null;
		try {
			fileList = upload.parseRequest(new ServletRequestContext(req));
		} catch (Exception e) {
			e.printStackTrace();

			tellExplorerResult(resp, "文件上传失败");
			return;
		}

		// 直接获取处理上传文件的对象
		FileItem fileItem = fileList.stream().filter(item -> !item.isFormField()).findFirst().get();
*/
		// 获得文本域的名字
		String fieldname = fileItem.getFieldName();
		if (null == fieldname || "".equals(fieldname.trim())) {
			return "";
		}

		// 获取原始文件名(因浏览器不同不确定是否包含路径)
		String srcfilename = fileItem.getName();
		if (null == srcfilename || "".equals(srcfilename.trim())) {
			return "";
		}

		System.out.println("文件大小：" + fileItem.getSize());
		System.out.println("文本域内容类型：" + fileItem.getContentType());
		System.out.println("上传文件的名称：" + srcfilename);

		// 获取文件拓展名(包含.)
		String extension = FileExtUtils.getExtension(srcfilename);

		// 获得原始文件名(不包含路径和扩展名)
		String filename = FileExtUtils.getFileName(srcfilename);

		// 重命名要上传的文件
		String newfilename = java.text.MessageFormat.format("{0}_{1}{2}", filename,
				String.join("", UUID.randomUUID().toString().split("-")), extension);

		// 指定要上传的目录
		String filepath = getServletContext().getRealPath("/upload");

		// 创建文件对象
		File file = new File(filepath, newfilename);

		try {
			// 把文件写入硬盘
			fileItem.write(file);
		} catch (Exception e) {
			e.printStackTrace();
			errMsg = "文件上传失败：" + e.getMessage();
		}
		return "/upload/" + newfilename;
	}
}
