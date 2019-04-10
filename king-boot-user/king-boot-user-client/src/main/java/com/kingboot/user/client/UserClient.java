package com.kingboot.user.client;

import com.kingboot.common.model.RestResponse;
import com.kingboot.user.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient (value = "king-boot-user",fallback = UserClientImpl.class)
public interface UserClient {
    
    @GetMapping (value = "/api/user/detail/{id}",name = "根据id查询")
    RestResponse<User> findById (@PathVariable Integer id);
}
