package com.star.template.spi;


import com.star.template.Context;

import java.io.IOException;
import java.text.ParseException;

public interface Listener {

    void render(Context context) throws IOException, ParseException;

}