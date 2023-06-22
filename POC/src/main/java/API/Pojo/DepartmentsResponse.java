package API.Pojo;

import java.util.List;

public class DepartmentsResponse {

    private List<Department> departments;

    public List<Department> getDepartments() {
        return departments;
    }

    public void setDepartments(List<Department> departments) {
        this.departments = departments;
    }

    public static class Department {
        private String _id;
        private String title;

        public String get_id() {
            return _id;
        }

        public String getTitle() {
            return title;
        }

        public void set_id(String _id) {
            this._id = _id;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }
}
