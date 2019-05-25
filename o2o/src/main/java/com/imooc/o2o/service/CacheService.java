package com.imooc.o2o.service;

/**
 * Created by Unruly Wind on 2019/2/14/014.
 *
 * @author BlueMelancholy
 * @desc:
 */
public interface CacheService {
	/**
	 * 依据key前缀删除匹配该模式下的所有key-value 如传入:shopcategory,则shopcategory_allfirstlevel等
	 * 以shopcategory打头的key_value都会被清空
	 *
	 * @param keyPrefix
	 * @return
	 */
	void removeFromCache(String keyPrefix);
}
