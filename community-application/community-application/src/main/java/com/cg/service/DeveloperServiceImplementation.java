package com.cg.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.entity.Developer;
import com.cg.exception.ApplicationException;
import com.cg.repository.DeveloperRepository;

import jakarta.transaction.Transactional;
@Service
@Transactional
public class DeveloperServiceImplementation implements DeveloperService{
	@Autowired 
	public DeveloperRepository developerRepository;

	@Override
	public Developer addDeveloperService(Developer developer) {
		developer.setUserName(developer.getUserName().toLowerCase());  
		return developerRepository.save(developer);
	}

	@Override
	public Developer updateDeveloperService(Developer developer, int developerUserId) {
		Developer d = developerRepository.findById(developerUserId).orElseThrow(()->new ApplicationException("Developer not found"));
		d.setDeveloperId(developerUserId);
		d.setEmailAddress(developer.getEmailAddress());
		d.setGithubLink(developer.getGithubLink());
		d.setMemberSince(developer.getMemberSince());
		d.setReputationPoints(developer.getReputationPoints());
		d.setUserName(developer.getUserName());
		return developerRepository.save(d);
	}

	@Override
	public Developer getDeveloperByIdService(Integer devId) {
		return developerRepository.findById(devId).orElseThrow(()->new ApplicationException("Developer not found"));
	}

	@Override
	public List<Developer> getDeveloperByReputationService(int reputation) {
		return developerRepository.developerByReputationRepository(reputation);
	}

	@Override
	public List<Developer> getAllDevelopersService() {
		return developerRepository.findAll();
	}


	@Override
	public List<Developer> getByMaxReputationService() {
		return developerRepository.getByMaxReputationRepository();
	}

	@Override
	public void deleteDeveloper(int developerId) {
		if(!developerRepository.existsById(developerId)) {
			throw new ApplicationException("Developer does not exist");
		}else
			developerRepository.deleteById(developerId);

	}

}
