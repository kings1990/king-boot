package com.kingboot.basic.config.mybatis.mapper;

import com.kingboot.basic.config.mybatis.mapper.provider.CustomDeleteProvider;
import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.Param;


/**
 * <p class="detail">
 * 功能:自定义删除mapper
 * </p>
 * @param <T> the type parameter
 * @author Kings
 * @ClassName Custom delete mapper.
 * @Version V1.0.
 * @date 2016.03.22 11:07:42
 */
public interface CustomDeleteMapper<T> {
    /**
     * <p class="detail">
     * 功能:根据主键集合或者数组删除
     * </p>
     * @param key :主键
     * @return int
     * @author Kings
     * @date 2016.03.22 11:07:42
     */
    @DeleteProvider (type = CustomDeleteProvider.class, method = "dynamicSQL")
    int deleteByPrimaryKeys(@Param ("ids") Object key);
}
