package com.star.spi;


import com.star.Context;

import java.io.IOException;
import java.text.ParseException;

public interface Listener {

    void render(Context context) throws IOException, ParseException;

}