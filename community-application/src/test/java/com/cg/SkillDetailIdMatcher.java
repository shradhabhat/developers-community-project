package com.cg;
 
import org.mockito.ArgumentMatcher;
 
import com.cg.entity.SkillDetailId;
 
public class SkillDetailIdMatcher implements ArgumentMatcher<SkillDetailId> {
    private final SkillDetailId expectedSkillDetailId;
 
    public SkillDetailIdMatcher(SkillDetailId expectedSkillDetailId) {
        this.expectedSkillDetailId = expectedSkillDetailId;
    }
 
    @Override
    public boolean matches(SkillDetailId skillDetailId) {
        return skillDetailId.getDeveloper().getDeveloperId()==(expectedSkillDetailId.getDeveloper().getDeveloperId()) &&
               skillDetailId.getSkill().getSkillId().equals(expectedSkillDetailId.getSkill().getSkillId());
    }
}
