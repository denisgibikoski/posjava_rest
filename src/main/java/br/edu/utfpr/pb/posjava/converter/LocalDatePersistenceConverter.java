package br.edu.utfpr.pb.posjava.converter;

import java.sql.Date;
import java.time.LocalDate;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class LocalDatePersistenceConverter implements AttributeConverter<LocalDate, Date> {

    @Override
    public Date convertToDatabaseColumn(LocalDate attribute) {
        try {
            return (attribute == null) ? null : Date.valueOf(attribute);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public LocalDate convertToEntityAttribute(Date dbData) {
        try {
            return dbData.toLocalDate();
        } catch (Exception e) {
            return null;
        }
    }

}
