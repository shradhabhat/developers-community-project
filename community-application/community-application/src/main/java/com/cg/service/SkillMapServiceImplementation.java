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
		skillDetail.setDeveloper(devRepo.findById(skillMapdto.getDeveloperId()).orElseThrow(()->new RuntimeException("Developer not found")));
		skillDetail.setSkill(skillRepo.findById(skillMapdto.getSkillId()).orElseThrow(()->new RuntimeException("Skill not found")));
		skillMap.setSkillDetailId(skillDetail);
		skillMapRepository.save(skillMap);
		System.out.println(skillMap.getSkillDetailId());
		return skillMap;
	}
	@Override
	public List<SkillMap> getAllSkillMaps() {
		List<SkillMap> skillMapList = skillMapRepository.findAll();
		return skillMapList;
	}
	 public SkillMap getSkillMapById(Long skillId, int developerId) {
	        SkillDetailId skillDetailId = new SkillDetailId();
	        Developer developer = devRepo.findById(developerId).orElseThrow(() -> new RuntimeException("Developer not found"));
	        Skill skill = skillRepo.findById(skillId).orElseThrow(() -> new RuntimeException("Skill not found"));
	        skillDetailId.setDeveloper(developer);
	        skillDetailId.setSkill(skill);
	        return skillMapRepository.findById(skillDetailId).orElseThrow(() -> new RuntimeException("SkillMap not found"));
	    }
	 public SkillMap updateSkillMap(Long skillId, int developerId, SkillMapDto skillMapDto) {
	        SkillDetailId skillDetailId = new SkillDetailId();
	        Developer developer = devRepo.findById(developerId).orElseThrow(() -> new RuntimeException("Developer not found"));
	        Skill skill = skillRepo.findById(skillId).orElseThrow(() -> new RuntimeException("Skill not found"));
	        skillDetailId.setDeveloper(developer);
	        skillDetailId.setSkill(skill);
	        SkillMap existingSkillMap = skillMapRepository.findById(skillDetailId)
	        		.orElseThrow(() -> new RuntimeException("SkillMap not found"));
	        BeanUtils.copyProperties(skillMapDto, existingSkillMap);
	        SkillMap updatedSkillMap= skillMapRepository.save(existingSkillMap);
	        return updatedSkillMap;
	    }
 
	 
	    public void deleteSkillMap(Long skillId, int developerId) {
	        SkillDetailId skillDetailId = new SkillDetailId();
	        Developer developer = devRepo.findById(developerId).orElseThrow(() -> new RuntimeException("Developer not found"));
	        Skill skill = skillRepo.findById(skillId).orElseThrow(() -> new RuntimeException("Skill not found"));
	        skillDetailId.setDeveloper(developer);
	        skillDetailId.setSkill(skill);
	        SkillMap existingSkillMap = skillMapRepository.findById(skillDetailId).orElseThrow(() -> new RuntimeException("SkillMap not found"));
	        System.out.println(existingSkillMap.getExperienceLevel());
	        skillMapRepository.delete(existingSkillMap);
	    }
	    public List<SkillMap> findByDeveloperId(int developerId) {
	        Developer developer = devRepo.findById(developerId).orElseThrow(() -> new RuntimeException("Developer not found"));
	        return skillMapRepository.findBySkillDetailIdDeveloper(developer);
	    }
 
	    public List<SkillMap> findBySkillId(Long skillId) {
	        Skill skill = skillRepo.findById(skillId).orElseThrow(() -> new RuntimeException("Skill not found"));
	        return skillMapRepository.findBySkillDetailIdSkill(skill);
	    }
		public List<Skill> findSkillByDeveloperId(int developerId){
			List<SkillMap> skillMap =findByDeveloperId(developerId);
			List<Skill> skill = skillMap.stream().map((sk)->sk.getSkillDetailId().getSkill()).toList();
			return skill;
		 }
		public List<Developer> findDeveloperBySkillId(Long skillId){
			List<SkillMap> skillMap = findBySkillId(skillId);
			List<Developer> developerList = skillMap.stream().map((d)->d.getSkillDetailId().getDeveloper()).toList();
			return developerList;
		 }
}
