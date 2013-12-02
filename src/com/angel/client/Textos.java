package com.angel.client;

import com.google.gwt.i18n.client.Constants;
import com.google.gwt.i18n.client.ConstantsWithLookup;

public interface Textos extends ConstantsWithLookup {
String helloWorld();
String goodbyeWorld();
String errores();
String tablaVacia();
@DefaultStringValue("Name must be between {min} and {max} characters long.")
      @Key("custom.name.size.message")
      String custom_name_size_message();
}