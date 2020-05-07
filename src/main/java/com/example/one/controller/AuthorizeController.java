package com.example.one.controller;

import com.example.one.Dto.AccessTokenDto;
import com.example.one.Dto.GithubUser;
import com.example.one.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthorizeController {

    private GithubProvider githubProvider;
    @Autowired
    public void setUserDao (GithubProvider githubProvider) {
        this.githubProvider = githubProvider;
    }
    @Value("${github.client.id}")
    private String clientId;
    @Value("${github.client.secret}")
    private String clientSecret;
    @Value("${github.redirect.uri}")
    private String redirectUri ;

    @GetMapping("/callback")
    public String callback(@RequestParam(name = "code") String code,
                           @RequestParam(name = "state") String state){
        AccessTokenDto accesstokendto = new AccessTokenDto();
        accesstokendto.setClient_id(clientId);
        accesstokendto.setClient_secret(clientSecret);
        accesstokendto.setCode(code);
        accesstokendto.setRedirect_uri(redirectUri);
        accesstokendto.setState(state);
        String accessToken = githubProvider.getAccessToken(accesstokendto);
        GithubUser user = githubProvider.getUser(accessToken);
        System.out.println(user.getName());
        return "index";
    }
}
