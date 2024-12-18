package com.cg.service;

import java.util.List;

import com.cg.entity.Developer;
import com.cg.exception.ApplicationException;

public interface DeveloperService {

	Developer addDeveloperService(Developer developer)throws ApplicationException;

	Developer updateDeveloperService(Developer developer,int developerUserId)throws ApplicationException;

	Developer getDeveloperByIdService(Integer devId);

	List<Developer> getDeveloperByReputationService(int reputation) throws ApplicationException;

	List<Developer> getAllDevelopersService() throws ApplicationException;


	List<Developer> getByMaxReputationService() throws ApplicationException;

	void deleteDeveloper(int developerId);

}