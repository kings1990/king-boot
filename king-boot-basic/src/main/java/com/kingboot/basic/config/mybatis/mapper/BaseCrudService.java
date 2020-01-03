package com.kingboot.basic.config.mybatis.mapper;

import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Param;

import java.util.Collection;
import java.util.List;
import java.util.Map;


public interface BaseCrudService<T> {

	/**
	 * <p class="detail">
	 * 功能：往实体设置属性 直接根据通用mapper的select实现分页
	 * </p>
	 * @param pageInfo 分页对象
	 * @param t        实体
	 *
	 * @return PageInfo info
	 * @throws Exception e e
	 * @author Kings
	 * @date 2015年8月4日 17:23:46
	 */
	PageInfo selectPage(PageInfo pageInfo, T t) throws Exception;

	/**
	 * <p class="detail">
	 * 功能：实现sqlserver 分页查询
	 * </p>
	 * @param pageInfo      分页对象
	 * @param t             实体参数
	 * @param orderbyString 排序参数  无需另外加 order by ，值实例：“deliverytime desc”
	 *
	 * @return PageInfo info
	 * @throws Exception e e
	 * @author zqb
	 * @date 2016年3月21日 17:23:46
	 */
	PageInfo selectPage(PageInfo pageInfo, T t, String orderbyString) throws Exception;

	/**
	 * <p class="detail">
	 * 功能：实现sqlserver 分页查询,是否进行count查询
	 * </p>
	 * @param pageInfo 分页对象
	 * @param method   mapper执行方法
	 * @param param    map参数
	 * @param count    是否进行count查询
	 * @param orderBy  :
	 *
	 * @return PageInfo info
	 * @throws Exception e e
	 * @author zqb
	 * @date 2016年3月21日 17:23:46
	 */
	PageInfo selectPage(PageInfo pageInfo, String method, Map<String, Object> param, boolean count, String orderBy) throws Exception;

	/**
	 * <p class="detail">
	 * 功能：复杂参数分页(物理分页)
	 * </p>
	 * @param pageInfo 分页对象
	 * @param method   mapper执行方法
	 * @param param    map参数
	 *
	 * @return PageInfo info
	 * @throws Exception e
	 * @author Kings
	 * @date 2015年8月4日 17:23:46
	 */
	@SuppressWarnings ("rawtypes")
	PageInfo selectPage(PageInfo pageInfo, String method, Map<String, Object> param) throws Exception;

	/**
	 * <p class="detail">
	 * 功能：单表分页(物理分页)
	 * </p>
	 * @param pageInfo 分页对象
	 * @param method   mapper执行方法
	 * @param record   :实体  实体
	 *
	 * @return PageInfo info
	 * @throws Exception e
	 * @author Kings
	 * @date 2015年8月4日 17:23:47
	 */
	@SuppressWarnings ("rawtypes")
	PageInfo<T> selectPage(PageInfo pageInfo, String method, T record) throws Exception;

	/**
	 * <p class="detail">
	 * 功能：复杂参数分页(物理分页)
	 * </p>
	 * @param pageInfo 分页对象
	 * @param method   mapper执行方法
	 * @param param    map参数
	 * @param orderBy  orderBy排序
	 *
	 * @return PageInfo info
	 * @throws Exception e
	 * @author Kings
	 * @date 2015年8月4日 17:23:47
	 */
	PageInfo selectPage(PageInfo pageInfo, String method, Map<String, Object> param, String orderBy) throws Exception;

	/**
	 * <p class="detail">
	 * 功能：单表分页(物理分页)
	 * </p>
	 * @param pageInfo 分页对象
	 * @param method   mapper执行方法
	 * @param record   :实体  实体
	 * @param orderBy  orderBy排序
	 *
	 * @return PageInfo info
	 * @throws Exception e
	 * @author Kings
	 * @date 2015年8月4日 17:23:47
	 */
	PageInfo<T> selectPage(PageInfo pageInfo, String method, T record, String orderBy) throws Exception;

	/**
	 * <p class="detail">
	 * 功能：查询返回单条记录
	 * </p>
	 * @param record :实体:查询实体
	 *
	 * @return T
	 * @author Kings
	 * @date 2015年8月4日 17:23:47
	 */
	T selectOne(T record);

	/**
	 * <p class="detail">
	 * 功能：根据主键进行查询,必须保证结果唯一,单个字段做主键时,可以直接写主键的值,联合主键时,key可以是实体类,也可以是Map
	 * </p>
	 * @param obj :主键
	 *
	 * @return T
	 * @author Kings
	 * @date 2015年8月4日 17:23:47
	 */
	T selectByPrimaryKey(Object obj);

	/**
	 * <p class="detail">
	 * 功能：根据实体类不为null的字段进行查询,条件全部使用=号and条件
	 * </p>
	 * @param record :实体 实体
	 *
	 * @return List<T>
	 * @author Kings
	 * @date 2015年8月4日 17:23:47
	 */
	List<T> select(T record);

	/**
	 * <p class="detail">
	 * 功能：根据实体类不为null的字段查询总数,条件全部使用=号and条件
	 * </p>
	 * @param record :实体 实体
	 *
	 * @return int
	 * @author Kings
	 * @date 2015年8月4日 17:23:47
	 */
	int selectCount(T record);

	/**
	 * <p class="detail">
	 * 功能：根据主键集合或者数组查询,支持联合主键
	 * </p>
	 * @param ids 主键集合或者数组
	 *
	 * @return List<T>
	 * @author Kings
	 * @date 2016年3月21日 17:23:47
	 */
	List<T> selectByPrimaryKeys(Object ids);

	/**
	 * <p class="detail">
	 * 功能：根据Example条件进行查询
	 * </p>
	 * @param example : example实体
	 *
	 * @return List<T>
	 * @author Kings
	 * @date 2015年8月4日 17:23:47
	 */
	List<T> selectByExample(Object example);

	/**
	 * <p class="detail">
	 * 功能：插入一条数据
	 * </p>
	 * @param record :实体
	 *
	 * @return int
	 * @author Kings
	 * @date 2015年8月4日 17:23:47
	 */
	int insert(T record);

	/**
	 * <p class="detail">
	 * 功能：插入一条数据,只插入不为null的字段,不会影响有默认值的字段
	 * </p>
	 * @param record :实体
	 *
	 * @return int
	 * @author Kings
	 * @date 2015年8月4日 17:23:47
	 */
	int insertSelective(T record);

	/**
	 * <p class="detail">
	 * 功能：根据实体类中字段不为null的条件进行删除,条件全部使用=号and条件
	 * </p>
	 * @param record :实体
	 *
	 * @return int
	 * @author Kings
	 * @date 2015年8月4日 17:23:47
	 */
	int delete(T record);

	/**
	 * <p class="detail">
	 * 功能：通过主键进行删除,这里最多只会删除一条数据,单个字段做主键时,可以直接写主键的值,联合主键时,key可以是实体类,也可以是Map
	 * </p>
	 * @param key :主键
	 *
	 * @return int
	 * @author Kings
	 * @date 2015年8月4日 17:23:47
	 */
	int deleteByPrimaryKey(Object key);

	/**
	 * <p class="detail">
	 * 功能：删除实体
	 * </p>
	 * @param record :实体
	 *
	 * @return int
	 * @author Kings
	 * @date 2015年9月27日 17:23:47
	 */
	int deleteAll(Collection<T> record);

	/**
	 * <p class="detail">
	 * 功能：根据id批量删除,支持联合主键
	 * </p>
	 * @param ids :主键id集合
	 *
	 * @return int
	 * @author Kings
	 * @date 2015年9月27日 17:23:47
	 */
	int deleteByPrimaryKeys(Object ids);

	/**
	 * <p class="detail">
	 * 功能：根据主键进行更新,这里最多只会更新一条数据
	 * </p>
	 * @param record :实体
	 *
	 * @return int
	 * @author Kings
	 * @date 2015年8月4日 17:23:48
	 */
	int updateByPrimaryKey(T record);

	/**
	 * <p class="detail">
	 * 功能：根据主键进行更新,只会更新不是null的数据
	 * </p>
	 * @param record :实体
	 *
	 * @return int
	 * @author Kings
	 * @date 2015年8月4日 17:23:48
	 */
	int updateByPrimaryKeySelective(T record);

	/**
	 * <p class="detail">
	 * 功能：根据Example条件更新实体`record`包含的全部属性，null值会被更新
	 * </p>
	 * @param record  :实体
	 * @param example :example实体
	 *
	 * @return int
	 * @author Kings
	 * @date 2015年8月29日 17:23:48
	 */
	int updateByExample(@Param ("record") T record, @Param ("example") Object example);

	/**
	 * <p class="detail">
	 * 功能：根据Example条件更新实体`record`包含的不是null的属性值
	 * </p>
	 * @param record  :实体
	 * @param example :example实体
	 *
	 * @return int
	 * @author Kings
	 * @date 2015年8月29日 17:23:48
	 */
	int updateByExampleSelective(@Param ("record") T record, @Param ("example") Object example);

	/**
	 * <p class="detail">
	 * 功能：查询所有
	 * </p>
	 * @return List
	 * @author Kings
	 * @date 2015年11月5日 17:23:48
	 */
	List<T> selectAll();

	/**
	 * <p class="detail">
	 * 功能:根据Example条件进行查询总数
	 * </p>
	 * @param example :
	 *
	 * @return int
	 * @author 吴至轩
	 * @date 2017.06.20 17:23:48
	 */
	int selectCountByExample(Object example);

	/**
	 * <p class="detail">
	 * 功能:根据Example条件删除数据
	 * </p>
	 * @param example :
	 *
	 * @return int
	 * @author 吴至轩
	 * @date 2017.06.20 17:11:48
	 */
	int deleteByExample(Object example);

}
