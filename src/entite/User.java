/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entite;

/**
 *
 * @author hamou
 */
public class User {
       private int id;
    private String email;
    private String password;
    private String roles;
    private String name;
    private String tel;
    private int status;
    private String image_name;
    private String activation_token;
    private String reset_token;
    private String img_status;

    public User() {
    }

    public User(int id, String email, String password,String name,String roles, String tel, int status, String image_name, String activation_token, String reset_token, String img_status) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.name= name;
        this.roles= roles;
        this.tel = tel;
        this.status = status;
        this.image_name = image_name;
        this.activation_token = activation_token;
        this.reset_token = reset_token;
        this.img_status = img_status;
    }

    public User(String email, String password,String name, String roles,String tel, int status, String image_name, String activation_token, String reset_token, String img_status) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.roles = roles;
        this.tel = tel;
        this.status = status;
        this.image_name = image_name;
        this.activation_token = activation_token;
        this.reset_token = reset_token;
        this.img_status = img_status;
    }
    public User(int id, String roles) {
       
        this.roles = roles;
       
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
       public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    
    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getImage_name() {
        return image_name;
    }

    public void setImage_name(String image_name) {
        this.image_name = image_name;
    }

    public String getActivation_token() {
        return activation_token;
    }

    public void setActivation_token(String activation_token) {
        this.activation_token = activation_token;
    }

    public String getReset_token() {
        return reset_token;
    }

    public void setReset_token(String reset_token) {
        this.reset_token = reset_token;
    }

    public String getImg_status() {
        return img_status;
    }

    public void setImg_status(String img_status) {
        this.img_status = img_status;
    }

    

}
