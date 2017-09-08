package com.ht.htlibrary.net;

import com.google.gson.Gson;

import java.io.IOException;
import java.lang.reflect.Type;

import okhttp3.ResponseBody;
import retrofit2.Converter;


final class MyGsonResponseBodyConverter<T> implements Converter<ResponseBody, T> {

    private final Gson gson;
    private final Type type;



    MyGsonResponseBodyConverter(Gson gson, Type type) {
        this.gson = gson;
        this.type = type;
    }

    @Override
    public T convert(ResponseBody value) throws IOException {
        String response = value.string();
        BaseResponse baseResponse = gson.fromJson(response, BaseResponse.class);
        if(NetClient.SUCCESS_CODE.equals(baseResponse.getCode())){
            return gson.fromJson(response, type);
        } else {
            throw new ResultException(baseResponse.getCode(), baseResponse.getMsg());
        }
    }
}
