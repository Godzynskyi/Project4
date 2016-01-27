package com.godzynskyi.command.user;

import com.godzynskyi.annotation.RequestMapper;
import com.godzynskyi.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RequestMapper("/conditions")
public class CommandConditions implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        return "conditions";
    }
}
