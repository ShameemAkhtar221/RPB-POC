package API.Pojo;

import java.util.ArrayList;
import java.util.List;

public class ProfilePutRequest {

    private BasicInfo basicInfo;
    private List<Experience> experiences;
    private List<Skill> skills;
    private List<Certificate> certificates;
    private List<Academic> academics;
    private List<Award> awardsAndHonors;
    private List<Project> projects;

//    public ProfilePutRequest(BasicInfo basicInfo, List<Experience> experiences, List<Skill> skills, List<Certificate> certificates, List<Academic> academics, List<Award> awardsAndHonors, List<Project> projects) {
//        this.basicInfo = new BasicInfo();
//        this.experiences = new ArrayList<>();
//        this.skills = new ArrayList<>();
//        this.certificates = new ArrayList<>();
//        this.academics = new ArrayList<>();
//        this.awardsAndHonors = new ArrayList<>();
//        this.projects = new ArrayList<>();
//    }

    public BasicInfo getBasicInfo() {
        return basicInfo;
    }

    public void setBasicInfo(BasicInfo basicInfo) {
        this.basicInfo = basicInfo;
    }

    public List<Experience> getExperiences() {
        return experiences;
    }

    public void setExperiences(List<Experience> experiences) {
        this.experiences = experiences;
    }

    public List<Skill> getSkills() {
        return skills;
    }

    public void setSkills(List<Skill> skills) {
        this.skills = skills;
    }

    public List<Certificate> getCertificates() {
        return certificates;
    }

    public void setCertificates(List<Certificate> certificates) {
        this.certificates = certificates;
    }

    public List<Academic> getAcademics() {
        return academics;
    }

    public void setAcademics(List<Academic> academics) {
        this.academics = academics;
    }

    public List<Award> getAwardsAndHonors() {
        return awardsAndHonors;
    }

    public void setAwardsAndHonors(List<Award> awardsAndHonors) {
        this.awardsAndHonors = awardsAndHonors;
    }

    public List<Project> getProjects() {
        return projects;
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }
}
