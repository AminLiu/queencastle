/**
 * 控制层代码
 * <ul>
 * <li>id必須為String型，db主鍵為long型，在mybatis上指定id專門的<code>{@link IdTypeHandler}</code></li>
 * <li>一般情况下，除主键外键以外的其他基本数据类型，全部使用primary type</li>
 * <li>默认返回list,map,set等不允许null</li>
 * <li>针对本层的操作，尽量放置在业务层进行处理，只有顶级的层处理比较核心的业务，用户中心则是顶级层需要处理的</li>
 * </ul>
 * 
 * @author YuDongwei
 */

package com.queencastle.web.controllers;

