//package com.workify.entity;
//
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.FetchType;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.JoinColumn;
//import javax.persistence.ManyToOne;
//import javax.persistence.Table;
//
//import org.springframework.stereotype.Component;
//
//@Entity
//@Component
//@Table(name = "user_role")
//public class UserRole
//{
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "user_role_id")
//    private Integer userRoleId;
//
//    @Column(name = "user_id")
//    private Integer userId;
//
//    @Column(name = "sys_role_id")
//    private Integer roleId;
//
//    @Column(name = "is_active")
//    private Boolean isActive;
//    
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "user_id", insertable = false, updatable = false)
//    private User user;
//    
//    
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn( name = "sys_role_id", insertable = false, updatable = false)
//    private Role role;
//
//
//    public Integer getUserRoleId()
//    {
//        return userRoleId;
//    }
//
//
//    public void setUserRoleId(Integer userRoleId)
//    {
//        this.userRoleId = userRoleId;
//    }
//
//
//    public Integer getUserId()
//    {
//        return userId;
//    }
//
//
//    public void setUserId(Integer userId)
//    {
//        this.userId = userId;
//    }
//
//
//    public Integer getRoleId()
//    {
//        return roleId;
//    }
//
//
//    public void setRoleId(Integer roleId)
//    {
//        this.roleId = roleId;
//    }
//
//
//    public Boolean getIsActive()
//    {
//        return isActive;
//    }
//
//
//    public void setIsActive(Boolean isActive)
//    {
//        this.isActive = isActive;
//    }
//
//
//    public User getUser()
//    {
//        return user;
//    }
//
//
//    public void setUser(User user)
//    {
//        this.user = user;
//    }
//
//
//    public Role getRole()
//    {
//        return role;
//    }
//
//
//    public void setRole(Role role)
//    {
//        this.role = role;
//    }
//    
//   
//
//
//
//}
