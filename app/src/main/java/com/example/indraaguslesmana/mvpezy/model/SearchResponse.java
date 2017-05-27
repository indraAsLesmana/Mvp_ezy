package com.example.indraaguslesmana.mvpezy.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by indraaguslesmana on 4/5/17.
 */

public class SearchResponse {

    @SerializedName("products")
    @Expose
    public ArrayList<BestSellerResponse.Product> products = null;
    @SerializedName("status")
    @Expose
    public Status status;

    public class Status {

        @SerializedName("code")
        @Expose
        public String code;
        @SerializedName("message")
        @Expose
        public String message;

        public String getCode() {
            return code;
        }

        public String getMessage() {
            return message;
        }
    }
}
