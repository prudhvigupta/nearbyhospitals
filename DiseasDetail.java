package app.com.nearbyhospital;

public class DiseasDetail {

    int id=0;
    String name=null;

    public DiseasDetail(int id, String name, String diseas, String symptomps, String phone, String region) {
        this.id = id;
        this.name = name;
        this.diseas = diseas;
        this.symptomps = symptomps;
        this.phone = phone;
        this.region = region;
    }

    String diseas=null;
    String symptomps=null;
    String phone=null;
    String region=null;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDiseas() {
        return diseas;
    }

    public void setDiseas(String diseas) {
        this.diseas = diseas;
    }

    public String getSymptomps() {
        return symptomps;
    }

    public void setSymptomps(String symptomps) {
        this.symptomps = symptomps;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }



    public DiseasDetail() {
    }
}
