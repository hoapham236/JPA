package vn.iostar.configs;

import jakarta.persistence.*;
import vn.iostar.entity.Category;
import vn.iostar.entity.Video;

public class Test {
	public static void main(String[] args) {
		EntityManager enma = JPAConfig.getEntityManager();
		EntityTransaction trans = enma.getTransaction();
//		  Category cate = new Category(); cate.setCategoryname("Oppo A21");
//		  cate.setImages("abcd.jpg"); cate.setStatus(0);
		
//		Video video = new Video();
//		video.setVideoId("v001");
//		video.setTitle("test");
//		video.setCategory (cate);
		try {	
			trans.begin();
			//enma.persist(cate);
			//enma.persist(video);
			trans.commit();
		} catch (Exception e) {
			e.printStackTrace();
			trans.rollback();
			throw e;
		} finally {
			enma.close();
		}
	}
}