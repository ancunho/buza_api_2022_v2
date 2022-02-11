package com.buza.server.service;

import com.buza.server.dto.TbPostDto;
import com.buza.server.entity.TbPost;

import java.util.List;

public interface PostService {

    public Boolean insertTbPost(TbPost tbPost);
    public Boolean updateTbPost(TbPost tbPost);
    public List<TbPostDto> getAllTbPostListByTbPost(TbPostDto tbPostDto);

}
