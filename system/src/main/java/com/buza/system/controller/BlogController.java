package com.buza.system.controller;

import com.buza.server.common.BaseResponse;
import com.buza.server.common.ResponseCode;
import com.buza.server.dto.BaseRequest;
import com.buza.server.dto.TbBlogCategoryDto;
import com.buza.server.dto.TbPostDto;
import com.buza.server.entity.TbBlogCategory;
import com.buza.server.entity.TbPost;
import com.buza.server.service.BlogCategoryService;
import com.buza.server.service.PostService;
import com.buza.server.util.JwtProperties;
import com.buza.server.util.JwtTokenUtil;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping(value = "/blog")
public class BlogController {

    @Resource
    private JwtTokenUtil jwtTokenUtil;

    @Resource
    private JwtProperties jwtProperties;

    @Autowired
    private PostService postService;

    @Autowired
    private BlogCategoryService blogCategoryService;

    /**
     * Blog List Action
     * @param baseRequest
     * @param tbPostDto
     * @return
     */
    @PostMapping(value = "/list")
    public BaseResponse getAllTbPostListByTbPost(BaseRequest baseRequest, @RequestBody TbPostDto tbPostDto) {
        PageHelper.startPage(baseRequest.getPage(), baseRequest.getLimit());
        List<TbPostDto> returnData = postService.getAllTbPostListByTbPost(tbPostDto);
        return BaseResponse.valueOfSuccessList(returnData);
    }

    /**
     * postId => 없으면 Insert
     * postId => 있으면 Update
     * @param request
     * @param tbPostDto
     * @return
     */
    @PostMapping(value = "/proc")
    public BaseResponse procTbPostByTbPostDto(HttpServletRequest request, @RequestBody TbPostDto tbPostDto) {
        if (tbPostDto == null
                || StringUtils.isEmpty(tbPostDto.getPostTitle())
                || StringUtils.isEmpty(tbPostDto.getPostType())
        ) {
            return BaseResponse.valueOfFailureCodeMessage(ResponseCode.ILLEGAL_ARGUMENT.getCode(), ResponseCode.ILLEGAL_ARGUMENT.getDesc());
        }

        String authToken = request.getHeader(jwtProperties.getHeader());
        String username = jwtTokenUtil.getUsernameFromToken(authToken);

        try {
            if (tbPostDto.getPostId() == null || "".equals(tbPostDto.getPostId())) {
                // insert new
                TbPost tbPost = new TbPost();
                tbPost.setPostTitle(tbPostDto.getPostTitle());
                tbPost.setPostType(tbPostDto.getPostType());
                tbPost.setPostCategoryId(tbPostDto.getPostCategoryId());
                tbPost.setPostContent(tbPostDto.getPostContent());
                tbPost.setEventStartTime(tbPostDto.getEventStartTime());
                tbPost.setEventEndTime(tbPostDto.getEventEndTime());
                tbPost.setPostThumbnailBig(tbPostDto.getPostThumbnailBig());
                tbPost.setPostThumbnailSmall(tbPostDto.getPostThumbnailSmall());
                tbPost.setPostAuthor(username);
                tbPost.setIsJoin(tbPostDto.getIsJoin());
                tbPost.setIsNeedPay(tbPostDto.getIsNeedPay());
                tbPost.setPostPrice(tbPostDto.getPostPrice());
                tbPost.setStatus(tbPostDto.getStatus());
                tbPost.setOption01(tbPostDto.getOption01());
                tbPost.setOption02(tbPostDto.getOption02());
                tbPost.setOption03(tbPostDto.getOption03());
                tbPost.setOption04(tbPostDto.getOption04());
                tbPost.setOption05(tbPostDto.getOption05());

                boolean isSuccessInsert = postService.insertTbPost(tbPost);
                if (isSuccessInsert) {
                    return BaseResponse.valueOfSuccessMessage(ResponseCode.INSERT_SUCCESS.getDesc());
                }
                return BaseResponse.valueOfFailureMessage(ResponseCode.INSERT_ERROR.getDesc());
            } else {
                // update
                TbPost tbPost = new TbPost();
                tbPost.setPostId(tbPostDto.getPostId());
                tbPost.setPostTitle(tbPostDto.getPostTitle());
                tbPost.setPostType(tbPostDto.getPostType());
                tbPost.setPostCategoryId(tbPostDto.getPostCategoryId());
                tbPost.setPostContent(tbPostDto.getPostContent());
                tbPost.setEventStartTime(tbPostDto.getEventStartTime());
                tbPost.setEventEndTime(tbPostDto.getEventEndTime());
                tbPost.setPostThumbnailBig(tbPostDto.getPostThumbnailBig());
                tbPost.setPostThumbnailSmall(tbPostDto.getPostThumbnailSmall());
                tbPost.setPostAuthor(tbPostDto.getPostAuthor());
                tbPost.setIsJoin(tbPostDto.getIsJoin());
                tbPost.setIsNeedPay(tbPostDto.getIsNeedPay());
                tbPost.setPostPrice(tbPostDto.getPostPrice());
                tbPost.setStatus(tbPostDto.getStatus());
                tbPost.setOption01(tbPostDto.getOption01());
                tbPost.setOption02(tbPostDto.getOption02());
                tbPost.setOption03(tbPostDto.getOption03());
                tbPost.setOption04(tbPostDto.getOption04());
                tbPost.setOption05(tbPostDto.getOption05());

                boolean isSuccessUpdate = postService.updateTbPost(tbPost);
                if (isSuccessUpdate) {
                    return BaseResponse.valueOfSuccessMessage(ResponseCode.SAVE_SUCCESS.getDesc());
                }
                return BaseResponse.valueOfFailureMessage(ResponseCode.SAVE_ERROR.getDesc());
            }
        } catch (Exception e) {
            e.printStackTrace();
            return BaseResponse.valueOfFailureMessage(ResponseCode.SAVE_ERROR.getDesc());
        }
    }

    @GetMapping(value = "/info")
    public BaseResponse getTbPostByPostId(@RequestParam("postId") Integer postId) {
        if (postId == null) {
            return BaseResponse.valueOfFailureCodeMessage(ResponseCode.ILLEGAL_ARGUMENT.getCode(), ResponseCode.ILLEGAL_ARGUMENT.getDesc());
        }

        TbPostDto tbPostDto = postService.getTbPostByPostId(postId);
        return BaseResponse.valueOfSuccess(tbPostDto);

    }

    @GetMapping(value = "/category/list")
    public BaseResponse getBlogCategoryList(@RequestBody TbBlogCategoryDto tbBlogCategoryDto) {
        if (tbBlogCategoryDto == null) {
            return BaseResponse.valueOfFailureCodeMessage(ResponseCode.ILLEGAL_ARGUMENT.getCode(), ResponseCode.ILLEGAL_ARGUMENT.getDesc());
        }

        List<TbBlogCategoryDto> lstBlogCategory = blogCategoryService.getAllTbBlogCategoryByTbBlogCategory(tbBlogCategoryDto);
        return BaseResponse.valueOfSuccess(lstBlogCategory);
    }

    @PostMapping(value = "/category/proc")
    public BaseResponse proc_TbBlogCategory(@RequestBody TbBlogCategoryDto tbBlogCategoryDto) {
        try {
            if (tbBlogCategoryDto.getCategoryId() == null || "".equals(tbBlogCategoryDto.getCategoryId())) {
                // insert new
                TbBlogCategory tbBlogCategory = new TbBlogCategory();
                tbBlogCategory.setParentCategoryId(tbBlogCategoryDto.getParentCategoryId());
                tbBlogCategory.setCategoryName(tbBlogCategoryDto.getCategoryName());
                tbBlogCategory.setCategoryType(tbBlogCategoryDto.getCategoryType());
                tbBlogCategory.setSortOrder(tbBlogCategoryDto.getSortOrder());
                tbBlogCategory.setStatus(tbBlogCategoryDto.getStatus());
                tbBlogCategory.setOption01(tbBlogCategoryDto.getOption01());
                tbBlogCategory.setOption02(tbBlogCategoryDto.getOption02());
                tbBlogCategory.setOption03(tbBlogCategoryDto.getOption03());
                tbBlogCategory.setOption04(tbBlogCategoryDto.getOption04());
                tbBlogCategory.setOption05(tbBlogCategoryDto.getOption05());

                boolean isSuccessInsert = blogCategoryService.insertTbBlogCategory(tbBlogCategory);
                if (isSuccessInsert) {
                    return BaseResponse.valueOfSuccessMessage(ResponseCode.INSERT_SUCCESS.getDesc());
                }
                return BaseResponse.valueOfFailureMessage(ResponseCode.INSERT_ERROR.getDesc());
            } else {
                // update
                TbBlogCategory tbBlogCategory = new TbBlogCategory();
                tbBlogCategory.setCategoryId(tbBlogCategoryDto.getCategoryId());
                tbBlogCategory.setParentCategoryId(tbBlogCategoryDto.getParentCategoryId());
                tbBlogCategory.setCategoryName(tbBlogCategoryDto.getCategoryName());
                tbBlogCategory.setCategoryType(tbBlogCategoryDto.getCategoryType());
                tbBlogCategory.setSortOrder(tbBlogCategoryDto.getSortOrder());
                tbBlogCategory.setStatus(tbBlogCategoryDto.getStatus());
                tbBlogCategory.setOption01(tbBlogCategoryDto.getOption01());
                tbBlogCategory.setOption02(tbBlogCategoryDto.getOption02());
                tbBlogCategory.setOption03(tbBlogCategoryDto.getOption03());
                tbBlogCategory.setOption04(tbBlogCategoryDto.getOption04());
                tbBlogCategory.setOption05(tbBlogCategoryDto.getOption05());

                boolean isSuccessUpdate = blogCategoryService.updateTbBlogCategory(tbBlogCategory);
                if (isSuccessUpdate) {
                    return BaseResponse.valueOfSuccessMessage(ResponseCode.SAVE_SUCCESS.getDesc());
                }
                return BaseResponse.valueOfFailureMessage(ResponseCode.SAVE_ERROR.getDesc());
            }
        } catch (Exception e) {
            e.printStackTrace();
            return BaseResponse.valueOfFailureMessage(ResponseCode.SAVE_ERROR.getDesc());
        }
    }

    @PostMapping(value = "/category/depth")
    public BaseResponse lstDepthCategory(@RequestBody TbBlogCategoryDto tbBlogCategoryDto) {
        if (tbBlogCategoryDto == null) {
            return BaseResponse.valueOfFailureMessage(ResponseCode.ILLEGAL_ARGUMENT.getDesc());
        }

        List<Map<String, Object>> lstDepthCategoryData = blogCategoryService.lstDepthCategory(tbBlogCategoryDto);
        return BaseResponse.valueOfSuccess(lstDepthCategoryData);
    }

}
