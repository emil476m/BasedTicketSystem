package BE;

public class User {
    private String name;
    private String mail;
    private String userName;
    private String passWord;

    private int userID;

    public User(int userID, String passWord, String userName, String mail, String name){
        this.userID = userID;
        this.passWord = passWord;
        this.userName = userName;
        this.mail = mail;
        this.name = name;
    }
    public User(String passWord, String userName, String mail, String name){
        this.passWord = passWord;
        this.userName = userName;
        this.mail = mail;
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getName() {
        return name;
    }

    public String getMail() {
        return mail;
    }

    public String getUserName() {
        return userName;
    }
    public int getUserID() {
        return userID;
    }
    public String getPassWord(){
        return passWord;
    }
}
