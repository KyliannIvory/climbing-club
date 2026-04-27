package fr.univ.escaladeclub.climbingclub.service;

import fr.univ.escaladeclub.climbingclub.entity.Member;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class ResetTokenService {

    // stockage temporaire {token → membre}
    private final Map<String, Member> tokenStorage = new HashMap<>();

    public void storeToken(String token, Member member) {
        tokenStorage.put(token, member);
    }

    public Member getMemberByToken(String token) {
        return tokenStorage.get(token);
    }

    public void removeToken(String token) {
        tokenStorage.remove(token);
    }
}