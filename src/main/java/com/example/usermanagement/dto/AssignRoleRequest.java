package com.example.usermanagement.dto;

import jakarta.validation.constraints.NotBlank;

public class AssignRoleRequest {
	  	@NotBlank
	    private String roleName; // ADMIN / MANAGER / USER
	    public String getRoleName() { return roleName; }
	    public void setRoleName(String roleName) { this.roleName = roleName; }
}
