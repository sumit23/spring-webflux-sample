package com.springsample.webflux.util;

public class Constants {


    public static enum StatusCodes {
        SUCCESS, INTERNAL_SERVER_ERROR, BAD_REQUEST;
        
    	public static String getStatusDesc(StatusCodes status) {
            String desc = null;

            switch (status) {

                case SUCCESS:
                    desc = "SUCCESS";
                    break;
                case INTERNAL_SERVER_ERROR:
                    desc = "INTERNAL_SERVER_ERROR";
                    break;
                case BAD_REQUEST:
                    desc = "BAD_REQUEST";
                    break;
            }

            return desc;
        }
    }

}
