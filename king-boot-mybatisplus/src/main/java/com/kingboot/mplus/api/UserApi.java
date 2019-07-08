package com.kingboot.mplus.api;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kingboot.common.model.RestResponse;
import com.kingboot.mplus.entity.User;
import com.kingboot.mplus.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;


/**
 * <p class="detail">
 * 功能:用户
 * </p>
 * @author Kings
 * @ClassName Userapi.
 * @Version V1.0.
 * @date 2019-07-08 14:43:45
 */
@RestController
@AllArgsConstructor
@RequestMapping("/users" )
public class UserApi {

    private final  UserService userService;
    
    /**
	 * <p class="detail">
	 * 功能:分页查询
	 * </p>
	 *
	 * @param user 用户
	 * @return RestResponse
	 * @author Kings
	 * @date 2019-07-08 14:43:45
	 */
    @GetMapping("/page" )
    public RestResponse<IPage<User>> getUserPage(Page page, User user) {
        return new RestResponse<>(userService.page(page, Wrappers.query(user)));
    }
    
    /**
     * <p class="detail">
     * 功能:通过id查询用户
     * </p>
     *
     * @param id id
     * @return RestResponse
     * @author Kings
     * @date 2019-07-08 14:43:45
     */
    @GetMapping("/{id}" )
    public RestResponse<User> getById(@PathVariable("id" ) Integer id) {
        return new RestResponse<>(userService.getById(id));
    }
    
    /**
     * <p class="detail">
     * 功能:新增用户
     * </p>
     *
     * @param user 用户
     * @return RestResponse
     * @author Kings
     * @date 2019-07-08 14:43:45
     */
    @PostMapping
    public RestResponse save(@RequestBody User user) {
        return new RestResponse<>(userService.save(user));
    }
    
    /**
     * <p class="detail">
     * 功能:修改用户
     * </p>
     *
     * @param user 用户
     * @return RestResponse
     * @author Kings
     * @date 2019-07-08 14:43:45
     */
    @PutMapping
    public RestResponse updateById(@RequestBody User user) {
        return new RestResponse<>(userService.updateById(user));
    }
    
    /**
     * <p class="detail">
     * 功能:通过id删除用户
     * </p>
     *
     * @param id id
     * @return RestResponse
     * @author Kings
     * @date 2019-07-08 14:43:45
     */
    @DeleteMapping("/{id}" )
    public RestResponse removeById(@PathVariable Integer id) {
        return new RestResponse<>(userService.removeById(id));
    }

}
