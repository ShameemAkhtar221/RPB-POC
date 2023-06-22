package API.Pojo;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

public class Employee {
     private String profilePicture;
        private String name;
        private Object designation;
        private Object department;
        private String dateOfBirth;
        private String email;
        private String phone;
        private String dateOfJoining;
        private String _id;
        private String createdAt;
        private String updatedAt;
        private int __v;


        public String getProfilePicture() {
            return profilePicture;
        }

        public void setProfilePicture(String profilePicture) {
            this.profilePicture = profilePicture;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setDesignation(Object designation) {
            this.designation = designation;
        }

        public void setDepartment(Object department) {
            this.department = department;
        }

        public void setDateOfBirth(String dateOfBirth) {
            this.dateOfBirth = dateOfBirth;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public void setDateOfJoining(String dateOfJoining) {
            this.dateOfJoining = dateOfJoining;
        }

        public void set_id(String _id) {
            this._id = _id;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public void setUpdatedAt(String updatedAt) {
            this.updatedAt = updatedAt;
        }

        public String getName() {
            return name;
        }

        public Object getDesignation() {
            if (designation instanceof String) {
                return (String) department;
            } else if (designation instanceof Designation) {
                return designation;
            } else {
                return null;
            }
        }

        public Object getDepartment() {
            if (designation instanceof String) {
                return (String) department;
            } else if (department instanceof Department) {
                return department;
            } else {
                return null;
            }
        }

        public String getDateOfBirth() {
            return dateOfBirth;
        }

        public String getEmail() {
            return email;
        }

        public String getPhone() {
            return phone;
        }

        public String getDateOfJoining() {
            return dateOfJoining;
        }

        public String get_id() {
            return _id;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public String getUpdatedAt() {
            return updatedAt;
        }

    public int get__v() {
        return __v;
    }

    public void set__v(int __v) {
        this.__v = __v;
    }
}
