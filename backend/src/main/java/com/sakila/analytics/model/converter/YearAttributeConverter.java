package com.sakila.analytics.model.converter;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import java.time.Year;

@Converter(autoApply = true)
public class YearAttributeConverter implements AttributeConverter<Year, Short> {
    
    @Override
    public Short convertToDatabaseColumn(Year attribute) {
        if (attribute == null) {
            return null;
        }
        return (short) attribute.getValue();
    }

    @Override
    public Year convertToEntityAttribute(Short dbData) {
        if (dbData == null) {
            return null;
        }
        return Year.of(dbData);
    }
}
