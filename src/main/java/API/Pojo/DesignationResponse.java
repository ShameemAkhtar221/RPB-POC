package API.Pojo;

import java.util.List;

public class DesignationResponse {

    private boolean status;
    private int statusCode;
    private String message;
    private Data data;

    // Getter and Setter methods

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public static class Data{
        private List<Designation> designations;

        // Getter and Setter methods

        public List<Designation> getDesignations() {
            return designations;
        }

        public void setDesignations(List<Designation> designations) {
            this.designations = designations;
        }
    }

    public static class Designation {
        private String _id;
        private String title;

        // Getter and Setter methods

        public String get_id() {
            return _id;
        }

        public void set_id(String _id) {
            this._id = _id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }
}
