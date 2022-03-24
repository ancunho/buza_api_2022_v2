package com.buza.server.service;

import com.buza.server.dto.TbBlogCategoryDto;
import com.buza.server.entity.TbBlogCategory;

import java.util.List;

public interface BlogCategoryService {

    public Boolean insertTbBlogCategory(TbBlogCategory tbBlogCategory);
    public Boolean updateTbBlogCategory(TbBlogCategory tbBlogCategory);
    public List<TbBlogCategoryDto> getAllTbBlogCategoryByTbBlogCategory(TbBlogCategoryDto tbBlogCategoryDto);

}
