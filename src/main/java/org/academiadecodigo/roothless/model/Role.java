package org.academiadecodigo.roothless.model;

/**
 * Created by codecadet on 23/03/17.
 */
public class Role {

    private int id;

    private String role;
    private String permissions;

    public Role() {
    }

    public Role(String role, String permissions) {
        this.role = role;
        this.permissions = permissions;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPermissions() {
        return permissions;
    }

    public void setPermissions(String permissions) {
        this.permissions = permissions;
    }
}
