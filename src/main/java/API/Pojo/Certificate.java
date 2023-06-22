package API.Pojo;

public class Certificate {
    private String certName;
    private String certInstitute;
    private String certStartsFrom;
    private String certEndsTo;

    public String getCertName() {
        return certName;
    }

    public void setCertName(String certName) {
        this.certName = certName;
    }

    public String getCertInstitute() {
        return certInstitute;
    }

    public void setCertInstitute(String certInstitute) {
        this.certInstitute = certInstitute;
    }

    public String getCertStartsFrom() {
        return certStartsFrom;
    }

    public void setCertStartsFrom(String certStartsFrom) {
        this.certStartsFrom = certStartsFrom;
    }

    public String getCertEndsTo() {
        return certEndsTo;
    }

    public void setCertEndsTo(String certEndsTo) {
        this.certEndsTo = certEndsTo;
    }
}
