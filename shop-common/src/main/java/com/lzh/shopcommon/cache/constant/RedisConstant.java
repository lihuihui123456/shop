package com.lzh.shopcommon.cache.constant;

/**
 * Redis常量信息
 *
 * @author Lee
 *
 */
public interface RedisConstant {

	public static final Integer REDIS_MACHINE_COUNT = 6; // Redis服务器的数量

	public static final String REDIS_PROPERTIES = "redis.properties"; // redis的配置文件

	public static final String REDIS_SAVESTATE_SUCCESS = "OK";// 存储成功
	public static final String REDIS_SAVESTATE_FAIL = "NO";// 存储失败

	public static final Long REDIS_DELETE_SUCCESS = 1L; // 删除成功
	public static final Long REDIS_DELETE_FAIL = 0L; // 删除失败

	public static final Long REDIS_SAVE_LIST_AFTER_FAIL = -1L; // "不成功"的向list集合后添加一条数据
	public static final Long REDIS_SAVE_Set_AFTER_FAIL = -1L; // "不成功"的向Set集合后添加一条数据

}

