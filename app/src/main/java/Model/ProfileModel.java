package Model;

public class ProfileModel {

    String person_name;
    String email_id;
    String roll_number;
    String person_branch;
    int pic;


    public ProfileModel(String person_name, String email_id, String roll_number, String person_branch, int pic) {
        this.person_name = person_name;
        this.email_id = email_id;
        this.roll_number = roll_number;
        this.person_branch = person_branch;
        this.pic=pic;
    }
    public int getPic() {
        return pic;
    }

    public void setPic(int pic) {
        this.pic = pic;
    }

    public String getPerson_name() {
        return person_name;
    }

    public void setPerson_name(String person_name) {
        this.person_name = person_name;
    }

    public String getEmail_id() {
        return email_id;
    }

    public void setEmail_id(String email_id) {
        this.email_id = email_id;
    }

    public String getRoll_number() {
        return roll_number;
    }

    public void setRoll_number(String roll_number) {
        this.roll_number = roll_number;
    }

    public String getPerson_branch() {
        return person_branch;
    }

    public void setPerson_branch(String person_branch) {
        this.person_branch = person_branch;
    }
}
