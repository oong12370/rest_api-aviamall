package com.garudaindonesia.aviamall.controller;

import com.garudaindonesia.aviamall.exception.ResourceNotFoundException;
import com.garudaindonesia.aviamall.model.Member;
import com.garudaindonesia.aviamall.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class AccountController {
    @Autowired
    private MemberRepository memberRepository;

    @GetMapping("/member")
    public List<Member> getAllMember() {
        return memberRepository.findAll();
    }

    @GetMapping("/member/{id}")
    public ResponseEntity<Member> getMemberById(@PathVariable(value = "id") Long memberId)
            throws ResourceNotFoundException {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + memberId));
        return ResponseEntity.ok().body(member);
    }

    @PostMapping("/member")
    public Member createMember(@Valid @RequestBody Member member) {
        return memberRepository.save(member);
    }

    @PutMapping("/member/{id}")
    public ResponseEntity<Member> updateEmployee(@PathVariable(value = "id") Long memberId,
                                                   @Valid @RequestBody Member memberDetails) throws ResourceNotFoundException {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + memberId));

        member.setNama(memberDetails.getNama());
        member.setAlamat(member.getAlamat());
        final Member updatedMember= memberRepository.save(member);
        return ResponseEntity.ok(updatedMember);
    }

    @DeleteMapping("/member/{id}")
    public Map<String, Boolean> deleteMember(@PathVariable(value = "id") Long memberId)
            throws ResourceNotFoundException {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + memberId));

        memberRepository.delete(member);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}