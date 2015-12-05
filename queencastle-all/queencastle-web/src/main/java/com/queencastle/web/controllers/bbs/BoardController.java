package com.queencastle.web.controllers.bbs;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.queencastle.dao.PageInfo;
import com.queencastle.dao.model.SysResourceInfo;
import com.queencastle.dao.model.User;
import com.queencastle.dao.model.bbs.BBSBoard;
import com.queencastle.dao.utils.DateUtils;
import com.queencastle.service.interf.ResourceUploadService;
import com.queencastle.service.interf.SysResourceInfoService;
import com.queencastle.service.interf.bbs.BBSBoardService;
import com.queencastle.service.sessions.PermissionContext;

@Controller
@RequestMapping("/BBS")
public class BoardController {
	private static Logger logger = LoggerFactory.getLogger(BoardController.class);
	@Autowired
	private BBSBoardService bBSBoardService;
	@Autowired
	private ResourceUploadService resourceUploadService;
	@Autowired
	private SysResourceInfoService sysResourceInfoService;
	@RequestMapping("/boardList")
	public String boardList() {
		return "/BBS/boardList";
	}

	@ResponseBody
	@RequestMapping("/getBoardByParams")
	public PageInfo<BBSBoard> getBoardByParams(int page, int rows) {
		Map<String, Object> map = new HashMap<String, Object>();
		PageInfo<BBSBoard> pageInfo = bBSBoardService.getBBSBoardByParams(page, rows, map);
		return pageInfo;

	}

	@ResponseBody
	@RequestMapping("/saveBoard")
	public boolean saveBoard(String title,@RequestParam(
            value = "img", required = true) MultipartFile img) {
		try {
			BBSBoard board = new BBSBoard();
			User user = PermissionContext.getUser();
			board.setTitle(title);
			board.setImg(saveUserImgs(img, user.getId()));
			bBSBoardService.insert(board);
		} catch (IOException e) {
			logger.error(e.getMessage());
		}
		return true;
	}

	private String saveUserImgs(MultipartFile img, String userId) throws IOException {
		byte[] bytes = img.getBytes();
		if (bytes.length > 0) {
			String originName = img.getOriginalFilename();
			String fileName = DateUtils.getCurrFullTime() + "_" + originName;
			String key = resourceUploadService.uploadBytes(bytes, fileName);
			if (StringUtils.isNoneBlank(key)) {
				SysResourceInfo info = new SysResourceInfo();
				info.setFileKey(key);
				info.setFileName(fileName);
				info.setOriginName(originName);
				String ext = originName.substring((originName.lastIndexOf(".") + 1));
				info.setFileExt(ext);
				info.setUserId(userId);
				sysResourceInfoService.insert(info);
			}

			return fileName;
		}
		return null;
	}

}