package API.Pojo;

public class PostEmployeeRequest {
        private String name;
        private String designation;
        private String department;
        private String dateOfBirth;
        private String phone;
        private String dateOfJoining;
        private String email;
        private String profilePicture;

    public PostEmployeeRequest(String name, String designation, String department, String dateOfBirth, String phone, String dateOfJoining, String email, String profilePicture) {
        this.name = name;
        this.designation = designation;
        this.department = department;
        this.dateOfBirth = dateOfBirth;
        this.phone = phone;
        this.dateOfJoining = dateOfJoining;
        this.email = email;
        this.profilePicture = profilePicture;
    }

    public PostEmployeeRequest(){

    }

    public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDesignation() {
            return designation;
        }

        public void setDesignation(String designation) {
            this.designation = designation;
        }

        public String getDepartment() {
            return department;
        }

        public void setDepartment(String department) {
            this.department = department;
        }

        public String getDateOfBirth() {
            return dateOfBirth;
        }

        public void setDateOfBirth(String dateOfBirth) {
            this.dateOfBirth = dateOfBirth;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getDateOfJoining() {
            return dateOfJoining;
        }

        public void setDateOfJoining(String dateOfJoining) {
            this.dateOfJoining = dateOfJoining;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getProfilePicture() {
            return profilePicture;
        }

        public void setProfilePicture(String profilePicture) {
            this.profilePicture = profilePicture;
        }
}
