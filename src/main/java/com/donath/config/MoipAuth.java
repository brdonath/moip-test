package com.donath.config;

import br.com.moip.API;
import br.com.moip.Client;
import br.com.moip.authentication.Authentication;
import br.com.moip.authentication.BasicAuth;
import com.donath.model.Order;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Created by cin_bdonath on 21/03/2017.
 */
@Component
public class MoipAuth {

    @Value("${moip.token}")
    private String token;

    @Value("${moip.chave}")
    private String secret;

    public API getMoipAPI(){
        Authentication auth = new BasicAuth(token, secret);
        Client client = new Client(Client.SANDBOX, auth);
        return new API(client);
    }
}
