/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Dialog;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.util.Resources;
//import com.google.gson.Gson;
import entite.User;
import gui.HomeFormY;
import gui.ModifierProfil;
import gui.SessionManager;
import gui.SignIn;
import com.mycompany.gui.base;
import gui.BaseForm;
import utils.Statics;
//import com.sun.mail.smtp.SMTPTransport;
//import com.sun.mail.smtp.SMTPTransport;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;


//import java.util.Properties;


//import javax.mail.Session;
//import javax.mail.internet.InternetAddress;
//import javax.mail.internet.MimeMessage;








/**
 *
 * @author hamou
 */
public class UserServices {
      public static UserServices instance = null;
       public static boolean resultU = true;
      
    
    private ConnectionRequest req;
    
    public static UserServices getInstance(){
        if(instance == null)
            instance = new UserServices();
        return instance;
    }
    private ArrayList<Object> ulist;


    
    public UserServices (){
        req = new ConnectionRequest();
    
}
    public void SignUp(TextField email , TextField password, TextField confirm_password, TextField name, TextField tel,Resources res ){
        String url = Statics.BASE_URL+"/signup?email="+email.getText()+"&password="+password.getText()+"&name="+name.getText()+"&tel="+tel.getText();
        req.setUrl(url);
        if (email.getText().equals("") && password.getText().equals("") && name.getText().equals("") && tel.getText().equals("")){
            Dialog.show("error","veuillez remplir les champ","ok",null);
        }
        req.addResponseListener((e)->{
            
            byte[]data = (byte[])e.getMetaData();
            String ResponseData = new String(data);
            System.out.println("Data => "+ResponseData);
              new SignIn(res).show();
              
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
    }
    
    public void signin(TextField email,TextField password,Resources res){
         String url = Statics.BASE_URL+"/signin?email="+email.getText().toString()+"&password="+password.getText().toString();
         req = new ConnectionRequest(url,false);
        req.setUrl(url);
        User u = new User();
         req.addResponseListener((e)->{
            JSONParser j = new JSONParser();
            String json = new String(req.getResponseData()) + "";
            try{
                if(json.equals("user not found")){
                    Dialog.show("Echec d'authnetification","votre email ou mot de passe est incorrect","OK",null);
                }
                else{
                    System.out.println("data =>"+json);
                  
                
                Map<String,Object> user = j.parseJSON(new CharArrayReader(json.toCharArray()));
                
                
             
                //Session 
                float id = Float.parseFloat(user.get("id").toString());
                SessionManager.setId((int)id);
                SessionManager.setRoles(user.get("roles").toString());
                SessionManager.setPassowrd(user.get("password").toString());
                SessionManager.setName(user.get("name").toString());
                SessionManager.setEmail(user.get("email").toString());
                SessionManager.setTel(user.get("tel").toString());
                
         
                
                 if(user.size() >0 ){
                     System.out.println("role service :"+SessionManager.getRoles());
                     if(SessionManager.getRoles().contains("ROLE_ADMIN")){
                        new BaseForm(res).show();
                     }
                     else if(SessionManager.getRoles().contains("ROLE_USER")){
                         new ModifierProfil(res, u).show();
                     }
                 }
                  
                  //else{
                    //    new ModifierProfil(res, u).show();
                  //}
                }
            } catch(Exception ex) {
                ex.printStackTrace();
            }
        });
            NetworkManager.getInstance().addToQueueAndWait(req);
    }
//affichage
    public ArrayList<User> afficherUtilisateur(){
	ArrayList<User> result = new ArrayList<>();
        String url=Statics.BASE_URL+"/AfficherAdminMobile";
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>(){
            @Override
            public void actionPerformed(NetworkEvent evt) {
                JSONParser jsonp;
                jsonp = new JSONParser();
                try{
                    Map<String,Object> mapUser = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
                    List<Map<String,Object>> listOfMaps=(List<Map<String,Object>>) mapUser.get("root");
                    System.out.println(listOfMaps);
                    for(Map<String,Object> obj : listOfMaps){
                        User u=new User();
                        
                        float id =Float.parseFloat(obj.get("id").toString());
                        String name=obj.get("name").toString();
                        String email=obj.get("email").toString();
                        String tel = obj.get("tel").toString();
                      //  String image_name=obj.get("image_name").toString();
                     
                        
                        u.setId((int)id);
                        u.setName(name);
                        u.setEmail(email);
                        //u.setImage_name(image_name);
                        u.setTel(tel);
                        
                        result.add(u);
                    }
                }catch(Exception ex){
                    ex.printStackTrace();
                }
            }
            
        });
        

        NetworkManager.getInstance().addToQueueAndWait(req);
        return result;
    }
    
        public boolean DeleteUser(int id){
        String url=Statics.BASE_URL+"/deleteUserMobileAdmin/"+id;
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                    
                    req.removeResponseCodeListener(this);
            }
        });
        
        NetworkManager.getInstance().addToQueueAndWait(req);
        return  resultU;
    }
        
            public void addUser(TextField email , TextField password,  TextField name, TextField tel,Resources res ){
 String url = Statics.BASE_URL+"/AddMobileUser?email="+email.getText()+"&password="+password.getText()+"&name="+name.getText()+"&tel="+tel.getText();
        req.setUrl(url);
        req.addResponseListener((a)->{
            String str=new String(req.getResponseData());
            System.out.println("data = "+str);
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
    }
            
    public boolean modifierAdmin( User u) {
        
        String url = Statics.BASE_URL +"/UpdateCmptMobileAdmin/"+u.getId()+"?roles="+u.getRoles();
        System.out.println(url);
        req.setUrl(url);
                      
       
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultU = req.getResponseCode() == 200 ;  // Code response Http 200 ok
                req.removeResponseListener(this);
                 
              //  JsontooString();
            }
        });
        
    NetworkManager.getInstance().addToQueueAndWait(req);
    return resultU;
        
    }
    
        public boolean modifierCompte() {
        
        String url = Statics.BASE_URL +"/UpdateCmptMobileCompte/"+SessionManager.getId()+"?name="+SessionManager.getName()+"&tel="+SessionManager.getTel();
        System.out.println(url);
        req.setUrl(url);
                  
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultU = req.getResponseCode() == 200 ;  // Code response Http 200 ok
                req.removeResponseListener(this);
                 
              //  JsontooString();
            }
        });
        
    NetworkManager.getInstance().addToQueueAndWait(req);//execution ta3 request sinon yet3ada chy dima nal9awha
    return resultU;
        
    }
        
            
           
   /* public void envoyerMail(User u) {
        try {
            
             Properties props = new Properties();
                props.put("mail.transport.protocol", "smtp"); //SMTP protocol
		props.put("mail.smtps.host", "smtp.gmail.com"); //SMTP Host
		props.put("mail.smtps.auth", "true"); //enable authentication
             
           Session session = Session.getInstance(props,null); 
            
            
            MimeMessage msg = new MimeMessage(session);
            
            msg.setFrom(new InternetAddress("Reintialisation mot de passe <>"));
            msg.setSubject("Application nom  : Confirmation du ");
            
            
           
      
        SMTPTransport t = (SMTPTransport)session.getTransport("smtps");
            
          t.connect("smtp.gmail.com",465,"formula1brightlights@gmail.com","Formula1bright");
           
          t.sendMessage(msg, msg.getAllRecipients());
            
          System.out.println("server response : "+t.getLastServerResponse());
          
        }catch(Exception e ) {
            e.printStackTrace();
        }
    }
*/

}
