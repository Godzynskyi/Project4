package com.godzynskyi.command.admin;

import com.godzynskyi.annotation.RequestMapper;
import com.godzynskyi.controller.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RequestMapper("/admin/add_defect")
public class CommandAddDefect implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        return "admin/add_defect";
    }
}
