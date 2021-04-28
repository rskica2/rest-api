/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mybean;

/**
 *
 * @author rober
 */
public class UsingBeanScopeApplication {
    
    private String name = "";
    private String status = "";
    private String created_at = "";
    
    public void UsingBeanScopeApplication () {
    }

    public void UsingBeanScopeApplication (String name, String status, String created_at) {
        this.name = name;
        this.status = status;
        this.created_at = created_at;
    }
    
    public void setName (String name) {
        this.name = name;
    }
    
    public String getName() {
        return name;
    }
    
    public void setStatus (String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
    
    public void setCreated_at (String created_at) {
        this.created_at = created_at;
    }

    public String getCreated_at() {
        return created_at;
    }
   
}
