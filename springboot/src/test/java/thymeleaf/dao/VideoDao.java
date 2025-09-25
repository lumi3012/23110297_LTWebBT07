package thymeleaf.dao;

import java.util.List;

import thymeleaf.entity.Video;

public interface VideoDao {
	Video create(Video v);
    Video update(Video v);
    void delete(Long id);
    Video findById(Long id);
    List<Video> findAll();
    List<Video> searchByTitle(String keyword);
	List<Video> search(String keyword);
}
