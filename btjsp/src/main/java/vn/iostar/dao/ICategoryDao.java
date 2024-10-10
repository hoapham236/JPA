package vn.iostar.dao;

import java.util.List;

import vn.iostar.entity.Category;

public interface ICategoryDao {

	void delete(int cateid) throws Exception;

	List<Category> findAll();

	Category findById(int cateid);

	void update(Category category);

	void insert(Category category);

	int count();

	List<Category> findAll(int page, int pagesize);

	List<Category> findByCategoryname(String catname);

}