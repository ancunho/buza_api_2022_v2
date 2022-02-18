package com.buza.server.dao;

import com.buza.server.dto.TbPostDto;
import com.buza.server.entity.TbPost;

import java.util.List;

public interface TbPostMapper {
    int deleteByPrimaryKey(Integer postId);

    int insert(TbPost record);

    int insertSelective(TbPost record);

    TbPost selectByPrimaryKey(Integer postId);

    int updateByPrimaryKeySelective(TbPost record);

    int updateByPrimaryKey(TbPost record);

    List<TbPostDto> getAllTbPostListByTbPost(TbPostDto tbPostDto);

    TbPostDto getTbPostByPostId(Integer postId);
}