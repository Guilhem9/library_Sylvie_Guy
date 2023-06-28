package com.library.libraryapp.controller;

import com.library.libraryapp.model.Member;
import com.library.libraryapp.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/members")
public class MemberController {
    @Autowired
    private MemberService memberService;

    @GetMapping
    public List<Member> getAllMembers() {
        return memberService.getAllMembers();
    }

    @GetMapping("/{id}")
    public Member getMemberById(@PathVariable String id) {
        return memberService.getMemberById(id);
    }

    @PostMapping
    public Member createMember(@RequestBody Member member) {
        return memberService.createMember(member);
    }

    @PutMapping("/{id}")
    public Member updateMember(@RequestBody Member member, @PathVariable String id) {
        return memberService.updateMember(member);
    }

    @DeleteMapping("{id}")
    public void deleteMember(@PathVariable String id) {
        memberService.deleteMember(id);
    }
}
