public class CredentialSet {

    private String studentID;
    private String password;

    public CredentialSet(String id, String password) {
        this.studentID = id;
        this.password = password;
    }

    public String getID() {
        return studentID;
    }

    public String getPassword() {
        return password;
    }
}
