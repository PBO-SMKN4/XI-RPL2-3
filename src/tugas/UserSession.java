/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tugas;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.prefs.Preferences;

/**
 *
 * @author Fadillah
 */
 public final class UserSession {

    private static UserSession instance;

    private String userName;
    private Set<String> privileges;
    
    static Preferences userPreferences = Preferences.userRoot();
    
    
    public UserSession(String userName, Set<String> privileges) {
        this.userName = userName;
        this.privileges = privileges;
    }
    public static void setProfile(String nama){
        
        userPreferences.put("nama",nama);
    }
            
    public static UserSession getInstace(String userName, Set<String> privileges) {
        if(instance == null) {
            instance = new UserSession(userName, privileges);
        }
        return instance;
    }

    public String getUserName() {
        return userName;
    }

    public Set<String> getPrivileges() {
        return privileges;
    }

    public void cleanUserSession() {
        userName = "";// or null
        privileges = new HashSet<>();// or null
    }

    @Override
    public String toString() {
        return "UserSession{" +
                "userName='" + userName + '\'' +
                ", privileges=" + privileges +
                '}';
    }
}
