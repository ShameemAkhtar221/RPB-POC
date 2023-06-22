package API.Pojo;

import java.util.List;

public class ProfileData {

    private String employeeId;
    private String profileName;
    private String _id;
    private List<Experience> experiences;
    private List<Skill> skills;
    private List<Certificate> certificates;
    private List<Award> awardsAndHonors;
    private List<Academic> academics;
    private List<Project> projects;
    private String createdAt;
    private String updatedAt;
    private int __v;

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getProfileName() {
        return profileName;
    }

    public void setProfileName(String profileName) {
        this.profileName = profileName;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
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

    public List<Award> getAwardsAndHonors() {
        return awardsAndHonors;
    }

    public void setAwardsAndHonors(List<Award> awardsAndHonors) {
        this.awardsAndHonors = awardsAndHonors;
    }

    public List<Academic> getAcademics() {
        return academics;
    }

    public void setAcademics(List<Academic> academics) {
        this.academics = academics;
    }

    public List<Project> getProjects() {
        return projects;
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public int get__v() {
        return __v;
    }

    public void set__v(int __v) {
        this.__v = __v;
    }
}
