package com.vsevolodvisnevskij.data.net;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.vsevolodvisnevskij.data.entity.Error;
import com.vsevolodvisnevskij.data.entity.ErrorType;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.SocketTimeoutException;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Completable;
import io.reactivex.CompletableSource;
import io.reactivex.CompletableTransformer;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.functions.Function;
import retrofit2.HttpException;

@Singleton
public class ErrorTransformer {

    private Gson gson;

    @Inject
    public ErrorTransformer(Gson gson) {
        this.gson = gson;
    }


    public <Model, Err extends Error> ObservableTransformer<Model, Model> parseHttpError() {
        return new ObservableTransformer<Model, Model>() {
            @Override
            public ObservableSource<Model> apply(Observable<Model> upstream) {
                return upstream.onErrorResumeNext(new Function<Throwable, ObservableSource<? extends Model>>() {
                    @Override
                    public ObservableSource<? extends Model> apply(Throwable throwable) throws Exception {
                        if (throwable instanceof HttpException) {
                            HttpException httpException = (HttpException) throwable;
                            String body = (String) httpException.response().body();
                            Type errorType = new TypeToken<Err>() {
                            }.getType();
                            Err err = gson.fromJson(body, errorType);
                            return Observable.error(err);
                        } else if (throwable instanceof SocketTimeoutException) {
                            return Observable.error(new Error(ErrorType.SERVER_NOT_AVAILAIBLE));
                        } else if (throwable instanceof IOException) {
                            return Observable.error(new Error(ErrorType.NO_INTERNET));
                        } else {
                            return Observable.error(new Error(ErrorType.UNKNOWN_ERROR));
                        }
                    }
                });
            }
        };
    }

    public <Err extends Error> CompletableTransformer parseHttpErrorCompletable() {
        return upstream -> upstream.onErrorResumeNext(throwable -> {
            if (throwable instanceof HttpException) {
                HttpException httpException = (HttpException) throwable;
                String body = (String) httpException.response().body();
                Type errorType = new TypeToken<Err>() {
                }.getType();
                Err err = gson.fromJson(body, errorType);
                return Completable.error(err);
            } else if (throwable instanceof SocketTimeoutException) {
                return Completable.error(new Error(ErrorType.SERVER_NOT_AVAILAIBLE));
            } else if (throwable instanceof IOException) {
                return Completable.error(new Error(ErrorType.NO_INTERNET));
            } else {
                return Completable.error(new Error(ErrorType.UNKNOWN_ERROR));
            }
        });
    }
}
