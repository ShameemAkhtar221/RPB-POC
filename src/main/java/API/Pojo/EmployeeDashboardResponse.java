package API.Pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

public class EmployeeDashboardResponse {
    private int statusCode;
    private boolean status;
    private Object message;
    @JsonIgnoreProperties(ignoreUnknown = true)
    private Data data;
    @JsonIgnoreProperties(ignoreUnknown = true)
    private String error;
    @JsonIgnoreProperties(ignoreUnknown = true)
    private String path;

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public Object getMessage() {
        if (message instanceof String) {
            return (String) message;
        } else if (message instanceof List<?>) {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.convertValue(message, new TypeReference<List<String>>() {});
        } else {
            return null;
        }
    }
        public void setMessage(Object message) {
            this.message = message;
        }

        public String getError() {
            return error;
        }
        public void setError(String error) {
            this.error = error;
        }

        public String getPath() {
            return path;
        }
        public void setPath(String path) {
            this.path = path;
        }

        public static class Data {
            private int totalEmployees;
            private int totalProfiles;
            private int completedProfiles;
            private int draftProfiles;

            public int getTotalEmployees() {
                return totalEmployees;
            }

            public int getTotalProfiles() {
                return totalProfiles;
            }

            public int getCompletedProfiles() {
                return completedProfiles;
            }

            public int getDraftProfiles() {
                return draftProfiles;
            }

            public void setTotalEmployees(int totalEmployee) {
                this.totalEmployees = totalEmployee;
            }

            public void setTotalProfiles(int totalProfiles) {
                this.totalProfiles = totalProfiles;
            }

            public void setCompletedProfiles(int completedProfiles) {
                this.completedProfiles = completedProfiles;
            }

            public void setDraftProfiles(int draftProfiles) {
                this.draftProfiles = draftProfiles;
            }
        }
    }
