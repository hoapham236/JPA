package vn.iostar.services.impl;

import java.util.List;

import vn.iostar.dao.IVideoDao;
import vn.iostar.dao.impl.VideoDao;
import vn.iostar.entity.Video;
import vn.iostar.services.IVideoService;


public class VideoService implements IVideoService {
	
	IVideoDao videodao = new VideoDao();

	@Override

	public int count() {

		return videodao.count();

	}
	@Override
	public List <Video> findByCategoryId(int cateid)
	{
		return videodao.findByCategoryId(cateid);
	}
	@Override
	public List<Video> findAll(int page, int pagesize) {
		// TODO Auto-generated method stub
		return videodao.findAll(page,pagesize);
	}

	@Override
	public List<Video> findByVideoname(String vidname) {
		// TODO Auto-generated method stub
		return videodao.findByVideoname(vidname);
	}

	@Override
	public List<Video> findAll() {
		// TODO Auto-generated method stub
		return videodao.findAll();
	}

	@Override
	public Video findById(String vidid) {
		return videodao.findById(vidid);
	}

	@Override
	public void delete(String vidid) throws Exception {
		videodao.delete(vidid);
		
	}

	@Override
	public void update(Video video) {
		videodao.update(video);
		
	}

	@Override
	public void insert(Video video) {
		videodao.insert(video);
		
	}

}