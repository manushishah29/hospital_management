package com.softvan.hospitalManagement.controller;


import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/doctor")
public class DoctorController {

    @GetMapping({"/forDoctor"})
//    @PreAuthorize("hasAnyRole('ROLE_DOCTOR')")
    @PreAuthorize("hasPermission('forDoctor','write') || hasPermission('forDoctor','read') || hasPermission('forDoctor','update')")
    public String forDoctor() {
        return "This URL is only accessible to the doctor";
    }
}
