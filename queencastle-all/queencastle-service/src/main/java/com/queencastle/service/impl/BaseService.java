package com.queencastle.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.queencastle.dao.model.User;

public class BaseService {
	public Logger logger = LoggerFactory.getLogger(getClass());

	public User getCurrentUser() {
		return null;
	}
}
