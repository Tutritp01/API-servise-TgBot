package com.tutrit.storestapiservice.exception;

import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.WebRequest;

import java.util.Map;

/**
 * This could be used to manipulate attributes no error object in general
 * @see org.springframework.boot.autoconfigure.web.servlet.error.BasicErrorController
 */
@Component
public class ErrorAttributesHandler extends DefaultErrorAttributes {

    @Override
    public Map<String, Object> getErrorAttributes(WebRequest webRequest,
                                                  ErrorAttributeOptions options) {
        Map<String, Object> errorAttributes = super.getErrorAttributes(webRequest, options);
        errorAttributes.put("locale", webRequest.getLocale().toString());
        errorAttributes.remove("error");
        errorAttributes.put("mikas", "@mikas");
        return errorAttributes;
    }
}
