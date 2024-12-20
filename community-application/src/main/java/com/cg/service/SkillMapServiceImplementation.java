package com.cg.service;
import java.util.List;
 

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cg.dto.SkillMapDto;
import com.cg.entity.Developer;
import com.cg.entity.Skill;
import com.cg.entity.SkillDetailId;
import com.cg.entity.SkillMap;
import com.cg.exception.ApplicationException;
import com.cg.repository.DeveloperRepository;
import com.cg.repository.SkillMapRepository;
import com.cg.repository.SkillRepository;
import jakarta.transaction.Transactional;

 
@Service
@Transactional
public class SkillMapServiceImplementation implements SkillMapService {
	@Autowired
	DeveloperRepository devRepo;
	@Autowired
	SkillRepository skillRepo;
	@Autowired
	private SkillMapRepository skillMapRepository;
	@Override
	public SkillMap addSkillMap(SkillMapDto skillMapdto) {
		SkillMap skillMap= new SkillMap();
		BeanUtils.copyProperties(skillMapdto, skillMap);
		SkillDetailId skillDetail= new SkillDetailId();
		skillDetail.setDeveloper(devRepo.findById(skillMapdto.getDeveloperId()).orElseThrow(()->new ApplicationException("Developer for adding skill map not found")));
		skillDetail.setSkill(skillRepo.findById(skillMapdto.getSkillId()).orElseThrow(()->new ApplicationException("Skill for adding skill map not found")));
		skillMap.setSkillDetailId(skillDetail);
		skillMapRepository.save(skillMap);
		System.out.println(skillMap.getSkillDetailId());
		return skillMap;
	}
	@Override
	public List<SkillMap> getAllSkillMaps() {
		return skillMapRepository.findAll();
	}
	 public SkillMap getSkillMapById(Long skillId, int developerId) { 
	        SkillDetailId skillDetailId = new SkillDetailId();
	        Developer developer = devRepo.findById(developerId).orElseThrow(() -> new ApplicationException("Developer for getting all skill map not found"));
	        Skill skill = skillRepo.findById(skillId).orElseThrow(() -> new ApplicationException("Skill for getting all skill map not found"));
	        skillDetailId.setDeveloper(developer);
	        skillDetailId.setSkill(skill);
	        System.out.println("devipor id "+skillDetailId.getDeveloper().getDeveloperId());
	        System.out.println("skill id "+skillDetailId.getSkill().getSkillId());
	        return skillMapRepository.findById(skillDetailId).orElseThrow(() -> new ApplicationException("SkillMap for getting all skill map not found"));
	    }
	 public SkillMap updateSkillMap(Long skillId, int developerId, SkillMapDto skillMapDto) {
	        SkillDetailId skillDetailId = new SkillDetailId();
	        Developer developer = devRepo.findById(developerId).orElseThrow(() -> new ApplicationException("Developer for updating skill map not found"));
	        Skill skill = skillRepo.findById(skillId).orElseThrow(() -> new ApplicationException("Skill for updating skill map not found"));
	        skillDetailId.setDeveloper(developer);
	        skillDetailId.setSkill(skill);
	        SkillMap existingSkillMap = skillMapRepository.findById(skillDetailId)
	        		.orElseThrow(() -> new ApplicationException("SkillMap for updating skill map not found"));
	        BeanUtils.copyProperties(skillMapDto, existingSkillMap);
	        return skillMapRepository.save(existingSkillMap);
	    }

	    public void deleteSkillMap(Long skillId, int developerId) {
	        SkillDetailId skillDetailId = new SkillDetailId();
	        Developer developer = devRepo.findById(developerId).orElseThrow(() -> new ApplicationException("Developer for deleting skill map not found"));
	        Skill skill = skillRepo.findById(skillId).orElseThrow(() -> new ApplicationException("Skill for deleting skill map not found"));
	        skillDetailId.setDeveloper(developer);
	        skillDetailId.setSkill(skill);
	        SkillMap existingSkillMap = skillMapRepository.findById(skillDetailId).orElseThrow(() -> new ApplicationException("SkillMap for deleting skill map not found"));
	        System.out.println(existingSkillMap.getExperienceLevel());
	        skillMapRepository.delete(existingSkillMap);
	    }
	    public List<SkillMap> findByDeveloperId(int developerId) {
	        Developer developer = devRepo.findById(developerId).orElseThrow(() -> new ApplicationException("Developer for getting skill map not found"));
	        return skillMapRepository.findBySkillDetailIdDeveloper(developer);
	    }
	    public List<SkillMap> findBySkillId(Long skillId) {
	        Skill skill = skillRepo.findById(skillId).orElseThrow(() -> new ApplicationException("Skill  for getting skill map not found"));
	        return skillMapRepository.findBySkillDetailIdSkill(skill);
	    }
		public List<Skill> findSkillByDeveloperId(int developerId){
			List<SkillMap> skillMap =findByDeveloperId(developerId);
			return skillMap.stream().map((sk)->sk.getSkillDetailId().getSkill()).toList();
		}
}