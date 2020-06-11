package com.musicbox.auth.common.security;

import com.google.common.collect.Sets;
import com.musicbox.auth.common.models.CustomGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

import static com.musicbox.auth.common.security.UserPermissions.USER_READ;
import static com.musicbox.auth.common.security.UserPermissions.USER_WRITE;

public enum Role {
    USER(Sets.newHashSet(USER_READ)),
    ARTIST(Sets.newHashSet(USER_READ, USER_WRITE)),
    ADMIN(Sets.newHashSet(USER_READ, USER_WRITE));

    private final Set<UserPermissions> permissions;

    Role(Set<UserPermissions> permissions) {
        this.permissions = permissions;
    }

    public Set<UserPermissions> getPermissions() {
        return permissions;
    }

    public Set<CustomGrantedAuthority> getGrantedAuthorities(){
        Set<CustomGrantedAuthority> permissions = getPermissions().stream()
                .map(permission -> new CustomGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());
        permissions.add(new CustomGrantedAuthority("ROLE_" + this.name()));
        return permissions;
    }
}
