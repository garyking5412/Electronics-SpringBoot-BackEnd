package com.bkap.DTOs;

public class UserRoleDto {
    private int id;
    private RoleDto roleDto;

    public UserRoleDto(int id, RoleDto roleDto) {
        this.id = id;
        this.roleDto = roleDto;
    }

    public UserRoleDto() {
        super();
    }

    public RoleDto getRoleDto() {
        return roleDto;
    }

    public void setRoleDto(RoleDto roleDto) {
        this.roleDto = roleDto;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
