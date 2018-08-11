package br.edu.utfpr.pb.posjava.converter;

import br.com.caelum.vraptor.serialization.gson.DateGsonConverter;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.google.gson.JsonSyntaxException;
import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.annotation.Priority;
import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Alternative;
import javax.enterprise.inject.Specializes;
import javax.interceptor.Interceptor;

@Specializes
@Dependent
@Alternative
@Priority(Interceptor.Priority.APPLICATION)
public class CustomDateGsonConverter extends DateGsonConverter 
    implements
        JsonDeserializer<Date>
        ,JsonSerializer<Date> {

   
    @Override
    public JsonElement serialize(Date date, Type typeOfSrc, 
            JsonSerializationContext context) {
        String dateString = getFormatSer().format(date);
        return new JsonPrimitive(dateString);
    }

    @Override
    public Date deserialize(JsonElement json,
            Type typeOfT,
            JsonDeserializationContext context) throws JsonParseException {
        try {
            System.out.println("3TESTE= {" + json.getAsString() + "}");
            return getFormatDes().parse(json.getAsString());
        } catch (Exception e) {
            throw new JsonSyntaxException(json.getAsString(), e);
        }
    }

    protected DateFormat getFormatDes() {
        return new SimpleDateFormat("dd/MM/yyyy");
    }

    protected DateFormat getFormatSer() {
        return new SimpleDateFormat("dd/MM/yyyy");
    }

}
