package com.gstmadeeasy.Utils;

/**
 *
 */

public interface ApiCallHandler {

    void onApiSuccess();
    void onApiFailure(Exception c);
    void onNoConnection();


}
