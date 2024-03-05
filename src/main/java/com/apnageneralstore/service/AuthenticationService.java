package com.apnageneralstore.service;

import com.apnageneralstore.constant.AuthenticationMessage;
import com.apnageneralstore.exception.AuthenticationFailException;
import com.apnageneralstore.repository.ITokenRepository;
import com.apnageneralstore.repository.entity.AuthenticationToken;
import com.apnageneralstore.repository.entity.User;
import com.apnageneralstore.utils.Helper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    @Autowired
    ITokenRepository tokenRepository;

    public void authenticate(String token) throws AuthenticationFailException {
        if (!Helper.notNull(token)) {
            throw new AuthenticationFailException(AuthenticationMessage.AUTH_TOEKN_NOT_PRESENT);
        }
        if (!Helper.notNull(getUser(token))) {
            throw new AuthenticationFailException(AuthenticationMessage.AUTH_TOEKN_NOT_VALID);
        }
    }

    public User getUser(String token) {
        AuthenticationToken authenticationToken = tokenRepository.findTokenByToken(token);
        if (Helper.notNull(authenticationToken)) {
            if (Helper.notNull(authenticationToken.getUser())) {
                return authenticationToken.getUser();
            }
        }
        return null;
    }

    public void saveConfirmationToken(AuthenticationToken authenticationToken) {
        tokenRepository.save(authenticationToken);
    }

    private Object getUser(User user) {
        return tokenRepository.findTokenByUser(user);
    }
}
