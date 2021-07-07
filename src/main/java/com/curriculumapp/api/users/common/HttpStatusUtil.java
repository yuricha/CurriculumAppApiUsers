package com.curriculumapp.api.users.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.curriculumapp.api.users.base.BaseComponent;
import com.curriculumapp.api.users.base.ResponseDto;

public class HttpStatusUtil extends BaseComponent {

    private static Logger logger = LoggerFactory.getLogger(HttpStatusUtil.class);

    public ResponseEntity<String> getHttpStatusByResponse(ResponseDto responseBean) {
        logger.info("getHttpStatusByResponse: ");
        if (responseBean != null) {
            int code = responseBean.getCode();
            int status = responseBean.getStatus();
            if(status == 0) {
                if (code == Values.APP_CODE_OK) {
                    return new ResponseEntity<String>(ObjectUtils.convertObjectToJsonResponse(responseBean.getUserMessage()), headersJson, HttpStatus.OK);
                } else if (code == Values.APP_CODE_DATABASE_ERROR) {
                    return internalServerErrorResponse();
                } else if (code == Values.APP_CODE_NOT_RESOURCE_FOUND) {
                    return emptyResponse();
                }
                else if(code == Values.APP_CODE_BAD_REQUEST) {
                    return badRequest();
                }
                else if(code == Values.APP_CODE_RESOURCE_DUPLICATED) {
                    return resourceDuplicated();
                }
                else if(code == Values.APP_CODE_UNAUTHORIZED) {
                    return unauthorized();
                }
                else if(code == Values.APP_CODE_USER_NOT_EXISTS)
                    return userNotExists();
                else {
                    return emptyResponse();
                }
            }
            else {
                switch (status) {
                    case Values.HTTP_CODE_UNAUTHORIZED:
                        return badRequestCustom();
                    default:
                        break;
                }
            }
        }
        return emptyResponse();
    }
    
    public ResponseEntity<String> internalServerErrorResponse() {
        return new ResponseEntity<String>(
                ObjectUtils.convertObjectToJsonResponse(new ResponseDto(Values.HTTP_CODE_INTERNAL_SERVER_ERROR, Values.APP_CODE_NOT_RESOURCE_FOUND,
                        "Error", "Internal Server Error! Please, contact the system administrator")),
                headersJson,
                HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public ResponseEntity<String> emptyResponse() {
        return new ResponseEntity<String>(
                ObjectUtils.convertObjectToJsonResponse(new ResponseDto(Values.HTTP_CODE_NOT_RESOURCE_FOUND, Values.APP_CODE_NOT_RESOURCE_FOUND,
                        "Not found",
                        "Id of the requested resource does not exist")),
                headersJson,
                HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<String> badRequest() {
        return new ResponseEntity<String>(
                ObjectUtils.convertObjectToJsonResponse(new ResponseDto(Values.HTTP_CODE_BAD_REQUEST, Values.APP_CODE_BAD_REQUEST,
                        "Bad request",
                        "The parameters are not valid")),
                headersJson,
                HttpStatus.BAD_REQUEST);
    }




    public ResponseEntity<String> resourceDuplicated() {
        return new ResponseEntity<String>(
                ObjectUtils.convertObjectToJsonResponse(new ResponseDto(Values.HTTP_CODE_RESOURCE_DUPLICATED, Values.APP_CODE_RESOURCE_DUPLICATED,
                        "Resource already exists",
                        "Can not store the information because it generates a duplicated record")),
                headersJson,
                HttpStatus.BAD_REQUEST);
    }


    public ResponseEntity<String> unauthorized() {
        return new ResponseEntity<String>(
                ObjectUtils.convertObjectToJsonResponse(new ResponseDto(Values.HTTP_CODE_UNAUTHORIZED, Values.APP_CODE_UNAUTHORIZED,
                        "Unauthorized",
                        "User is not authorized to perform the request, the session may have expired or it is invalid")),
                headersJson,
                HttpStatus.UNAUTHORIZED);
    }


    private ResponseEntity<String> userNotExists() {
        return new ResponseEntity<String>(
                ObjectUtils.convertObjectToJsonResponse(new ResponseDto(Values.HTTP_CODE_UNAUTHORIZED, Values.APP_CODE_USER_NOT_EXISTS,
                        "Unauthorized",
                        "Id User does not exist")),
                headersJson,
                HttpStatus.UNAUTHORIZED);
    }


    public ResponseEntity<String> badRequestCustom() {
        return new ResponseEntity<String>(
                ObjectUtils.convertObjectToJsonResponse(new ResponseDto(Values.HTTP_CODE_UNAUTHORIZED, Values.APP_CODE_BAD_REQUEST,
                        "Unauthorized",
                        "The parameters are not valid")),
                headersJson,
                HttpStatus.UNAUTHORIZED);
    }

}

