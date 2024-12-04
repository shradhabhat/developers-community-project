package com.cg.entity;
 
 
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
 
@Table(name="skill_table")
@Entity
public class Skill {
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="our_seq_generator")
	@SequenceGenerator(name="our_seq_generator",sequenceName="our_sequence",allocationSize=1,initialValue=100 )
	private Long skillId;
	@Column(length=30, unique=true)
	@NotBlank(message = "Please enter Skill name")
	@NotNull(message = "Please enter Skill name")
	private String skillName;
	@Column(length=40)
	@NotBlank(message = "Please enter Skill description")
	@NotNull(message = "Please enter Skill description")
	private String skillDescription;
	@Column(length=50)
	@NotBlank(message = "Please enter Dependency Skill")
	@NotNull(message = "Please enter Dependency Skill")
	private String dependencySkill;
 
 
	public Skill() {
		super();
	}
	public Skill(Long skillId,
			@NotBlank(message = "Please enter Skill name") @NotNull(message = "Please enter Skill name") String skillName,
			@NotBlank(message="Please enter Skill Description") @NotNull(message="Please enter Skill Description") String skillDescription,
			@NotBlank(message = "Please enter Dependency Skill") @NotNull(message = "Please enter Dependency Skill") String dependencySkill) {
		super();
		this.skillId = skillId;
		this.skillName = skillName;
		this.skillDescription=skillDescription;
		this.dependencySkill = dependencySkill;
	}
	public String getSkillDescription() {
		return skillDescription;
	}
	public void setSkillDescription(String skillDescription) {
		this.skillDescription = skillDescription;
	}
	public Long getSkillId() {
		return skillId;
	}
	public void setSkillId(Long skillId) {
		this.skillId = skillId;
	}
 
	public String getSkillName() {
		return skillName;
	}
	public void setSkillName(String skillName) {
		this.skillName = skillName;
	}
	public String getDependencySkill() {
		return dependencySkill;
	}
	public void setDependencySkill(String dependencySkill) {
		this.dependencySkill = dependencySkill;
	}
 
 
}