package by.uniterra.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import by.uniterra.entity.User;
import by.uniterra.entity.enums.UserRoleEnum;

@Service
public class UserDetailsServiceImpl implements UserDetailsService
{

    @Autowired
    private UserService userService;

    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException
    {
        // � ������� ������� UserService �������� User
        User user = userService.getUser("test user");

        // ��������� ���� ��� ����� ������������
        Set<GrantedAuthority> roles = new HashSet<GrantedAuthority>();
        roles.add(new SimpleGrantedAuthority(UserRoleEnum.USER.name()));

        // �� ��������� ����������� ����� ��������� ������ UserDetails
        // ������� �������� ��������� �������� ������������� ����� � ������

        UserDetails userDetails = new org.springframework.security.core.userdetails.User(user.getLogin(), user.getPassword(), roles);

        return userDetails;
    }

}