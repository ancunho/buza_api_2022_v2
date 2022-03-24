package com.buza.server.service;

import com.buza.server.dto.TbBlogCategoryDto;
import com.buza.server.entity.TbBlogCategory;

import java.util.List;
import java.util.Map;

public interface BlogCategoryService {

    public Boolean insertTbBlogCategory(TbBlogCategory tbBlogCategory);
    public Boolean updateTbBlogCategory(TbBlogCategory tbBlogCategory);
    public List<TbBlogCategoryDto> getAllTbBlogCategoryByTbBlogCategory(TbBlogCategoryDto tbBlogCategoryDto);
    public List<Map<String, Object>> lstDepthCategory(TbBlogCategoryDto tbBlogCategoryDto);

}
