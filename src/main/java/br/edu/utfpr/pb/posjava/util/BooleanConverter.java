package br.edu.utfpr.pb.posjava.util;

import javax.persistence.AttributeConverter;
import javax.persistence.Convert;

@Convert
public class BooleanConverter
        implements AttributeConverter<Boolean, String> {

    @Override
    public String convertToDatabaseColumn(Boolean value) {

        return ( Boolean.TRUE.equals(value) ? "V" : "F");
        /*if (Boolean.TRUE.equals(value)) {
            return "V";
        } else {
            return "F";
        }*/
    }

    @Override
    public Boolean convertToEntityAttribute(String value) {
        return "V".equals(value);
    }

}
