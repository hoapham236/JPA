package vn.iostar.controllers;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import vn.iostar.entity.Category;
import vn.iostar.entity.Video;
import vn.iostar.services.ICategoryService;
import vn.iostar.services.IVideoService;
import vn.iostar.services.impl.CategoryService;
import vn.iostar.services.impl.VideoService;
import vn.iostar.utils.Constant;


@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 1024 * 1024 * 5, maxRequestSize = 1024 * 1024 * 5 * 5)
@WebServlet(urlPatterns ={"/admin/video/add","/admin/video/edit","/admin/video/delete"})
public class VideoController extends HttpServlet{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public IVideoService videoservice=new VideoService();
	public ICategoryService cateService = new CategoryService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String url = req.getRequestURI();
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		if(url.contains("/admin/video/add")) {
			int id = Integer.parseInt(req.getParameter("id"));
			req.setAttribute("id", id);
			req.getRequestDispatcher("/views/admin/video-add.jsp").forward(req, resp);
		}
		else if(url.contains("/admin/video/edit"))
		{
			String id=req.getParameter("id");
			Video video=new Video();
			video=videoservice.findById(id);
			req.setAttribute("video", video);
			req.getRequestDispatcher("/views/admin/video-edit.jsp").forward(req, resp);
		}
		else if(url.contains("/admin/video/delete")) {
			String videoid = req.getParameter("id");
			int cateid=Integer.parseInt(req.getParameter("categoryid"));
			try {
				videoservice.delete(videoid);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			resp.sendRedirect(req.getContextPath() + "/admin/category/list-video?id=" + cateid);
		}
		
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String url = req.getRequestURI();
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		if(url.contains("/admin/video/add"))
		{
			String images = req.getParameter("poster");
			int id = Integer.parseInt(req.getParameter("id"));
			String title=req.getParameter("title");
			String videoid=req.getParameter("videoid");
			String description=req.getParameter("description");
			int views=Integer.parseInt(req.getParameter("views"));
			int active=Integer.parseInt(req.getParameter("active"));
			Category category = cateService.findById(id);
			Video video = new Video();
			video.setActive(active);
			video.setCategory(category);
			video.setDescription(description);
			video.setTitle(title);
			video.setViews(views);
			video.setVideoId(videoid);
			
			// Upload Images
			String fname = "";
			String uploadPath = Constant.DIR;
			File uploadDir = new File(uploadPath);
			if (!uploadDir.exists()) {
				uploadDir.mkdir();
			}
			try {
				Part part=req.getPart("poster");
				System.out.println(part);
				if (part.getSize() > 0) {
					String filename = Paths.get(part.getSubmittedFileName()).getFileName().toString();
					// rename file
					int index = filename.lastIndexOf(".");
					String ext = filename.substring(index+1);
					fname = System.currentTimeMillis() + "." + ext;
					// upload file
					part.write(uploadPath + "/" + fname);
					// ghi ten file vao data
					video.setPoster(fname);
				} 
				else if(images != null) {
					video.setPoster(images);
				}
				else {
					video.setPoster("avatar.png");
				}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			
			videoservice.insert(video);
			resp.sendRedirect(req.getContextPath() + "/admin/categories");
		}
		else if(url.contains("/admin/video/edit"))
		{
			int categoryid = Integer.parseInt(req.getParameter("categoryid"));
			Category category=new Category();
			category=cateService.findById(categoryid);
			String title=req.getParameter("title");
			String videoid=req.getParameter("videoid");
			String description=req.getParameter("description");
			int views=Integer.parseInt(req.getParameter("views"));
			int active=Integer.parseInt(req.getParameter("active"));
		
			
			Video video = new Video();
			video.setVideoId(videoid);
			video.setActive(active);
			video.setDescription(description);
			video.setTitle(title);
			video.setViews(views);
			video.setCategory(category);
			video.setActive(active);
			
			
			// Giu hinh cu neu khong update
			Video videoId = videoservice.findById(videoid);
			String fileold = videoId.getPoster();
			// Upload Images
			String fname = "";
			String uploadPath = Constant.DIR;
			File uploadDir = new File(uploadPath);
			if (!uploadDir.exists()) {
				uploadDir.mkdir();
			}
			try {
				Part part = req.getPart("poster");
				if (part.getSize() > 0) {
					String filename = Paths.get(part.getSubmittedFileName()).getFileName().toString();
					// rename file
					int index = filename.lastIndexOf(".");
					String ext = filename.substring(index+1);
					fname = System.currentTimeMillis() + "." + ext;
					// upload file
					part.write(uploadPath + "/" + fname);
					// ghi ten file vao data
					video.setPoster(fname);
				}
				
				else {
					video.setPoster(fileold);
				}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			
			videoservice.update(video);
			resp.sendRedirect(req.getContextPath() + "/admin/category/list-video?id="+categoryid);
		}
//			
	}
		
		
}