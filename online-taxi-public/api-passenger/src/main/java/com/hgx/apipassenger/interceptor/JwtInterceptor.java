package com.hgx.apipassenger.interceptor;

import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.hgx.internalcomm.dto.ResponseResult;
import com.hgx.internalcomm.utils.JwtUtils;
import javafx.print.Printer;
import net.sf.json.JSONObject;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.jnlp.SingleInstanceListener;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * jwt拦截器
 */
public class JwtInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        boolean result = true;
        String resultString = "";

         String token = request.getHeader("Authorization");
         try {
             JwtUtils.parseToken(token);
         }catch (SignatureVerificationException e){
             resultString = "token sign error";
             result = false;
         }catch(TokenExpiredException e){
              resultString = "token time out";
              result = false;

         }catch(AlgorithmMismatchException e){
              resultString = "token AlgorithmMismatchException";
              result = false;
         }catch(Exception e){
             resultString = "token invalid";
             result = false;
         }

         if(!result){//如果result为false，就把错误写出去
             PrintWriter out = response.getWriter();
             out.print(JSONObject.fromObject(ResponseResult.fail(resultString)).toString());
         }

        return result;
    }
}
