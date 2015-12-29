package ca.etsmtl.applets.seemobile.utils;

import android.accounts.Account;
import android.accounts.AccountManager;

import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

/**
 * Created by gnut3ll4 on 27/12/15.
 */
public class AuthenticationInterceptor implements Interceptor {

    private String authToken;
    private AccountManager accountManager;

    public AuthenticationInterceptor(AccountManager accountManager) {
        this.accountManager = accountManager;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Account[] accounts = accountManager.getAccountsByType(Constants.ACCOUNT_TYPE);
        if (accounts.length > 0) {
            authToken = accountManager.peekAuthToken(accounts[0], Constants.AUTH_TOKEN_TYPE);
        } else {
            authToken = "";
        }

        Request.Builder builder = chain.request().newBuilder();
        Request request = builder.addHeader("Cookie", authToken)
                .build();

        return chain.proceed(request);
    }
}