package com.star.spi;


import com.star.Context;

import java.io.IOException;
import java.text.ParseException;

public interface Interceptor {

    void render(Context context, Listener listener) throws IOException, ParseException;

}