package thymeleaf.service.impl;

import java.util.List;

import thymeleaf.dao.VideoDao;
import thymeleaf.dao.impl.VideoDaoImpl;
import thymeleaf.entity.Video;
import thymeleaf.service.VideoService;

public class VideoServiceImpl implements VideoService {

    private final VideoDao dao = new VideoDaoImpl();

    @Override
    public Video create(Video v) {
        return dao.create(v);
    }

    @Override
    public Video update(Video v) {
        return dao.update(v);
    }

    @Override
    public void delete(Long id) {
        dao.delete(id);
    }

    @Override
    public Video findById(Long id) {
        return dao.findById(id);
    }

    @Override
    public List<Video> findAll() {
        return dao.findAll();
    }

    @Override
    public List<Video> search(String keyword) {
        return dao.searchByTitle(keyword);
    }
}
