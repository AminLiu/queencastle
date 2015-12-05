package com.queencastle.service.test;

import org.junit.Ignore;
import org.junit.Test;

import com.queencastle.dao.model.MsgInfo;
import com.queencastle.dao.mybatis.IdTypeHandler;

public class MsgInfoTester extends BaseTest {

		@Test
		@Ignore
		public void testInser(){
			MsgInfo msgInfo=new MsgInfo();
			String userId=IdTypeHandler.encode(2L);
			
			msgInfo.setUserId(userId);
			msgInfo.setContent("你好！欢迎你you");
			msgInfo.setImages("www.shanxin.com");
			msgInfoService.insert(msgInfo);
		}
		
		@Test
		@Ignore
		public void testGetById(){
			String id=IdTypeHandler.encode(1L);
			MsgInfo msgInfo=new MsgInfo();
			msgInfo=msgInfoService.getById(id);
			if(msgInfo !=null){
				System.out.println(msgInfo.getId());
				System.out.println(msgInfo.getUserId());
				System.out.println(msgInfo.getImages());
				System.out.println(msgInfo.getContent());
		
			}
		}
		
		@Test
		public void testUpdataById(){
			String id=IdTypeHandler.encode(1L);
			String userId=IdTypeHandler.encode(3L);
			MsgInfo msgInfo=new MsgInfo();
			msgInfo.setId(id);
			msgInfo.setContent("我很好啊");
			msgInfo.setUserId(userId);
			msgInfo.setImages("www.lovejava.com");
			msgInfoService.updateById(msgInfo);
		}
}