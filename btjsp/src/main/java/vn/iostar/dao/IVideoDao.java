package vn.iostar.dao;

import java.util.List;

import vn.iostar.entity.Video;

public interface IVideoDao {

	List<Video> findByCategoryId(int catid);

	int count();

	List<Video> findAll(int page, int pagesize);

	List<Video> findByVideoname(String vidname);
	

	List<Video> findAll();

	Video findById(String videoid);

	void delete(String videoid) throws Exception;

	void update(Video video);

	void insert(Video video);

}