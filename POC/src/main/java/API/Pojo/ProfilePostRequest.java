package API.Pojo;

public class ProfilePostRequest {
    private String employeeId;
    private  String profileName;

    public ProfilePostRequest(String employeeId, String profileName){
        this.employeeId = employeeId;
        this.profileName = profileName;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public String getProfileName() {
        return profileName;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public void setProfileName(String profileName) {
        this.profileName = profileName;
    }
}
