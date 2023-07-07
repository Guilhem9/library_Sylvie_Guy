package com.library.libraryapp.repository;

import com.library.libraryapp.model.Document;
import com.library.libraryapp.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    Member findByMemberID(Long memberID);
}
