package edu.csumb.james.otterairways; 


public class Username {

    private String username;
    private String password;

    public Username(){

        username ="";
        password ="";

    }

    public Username(String username , String password){

        this.username = username;
        this.password = password;
    }

    public void setUsername( String username){ this.username = username;}

    public String getUsername(){ return username;}

    public void setPassword( String password){ this.password = password;}

    public String getPassword(){ return password;}
}
