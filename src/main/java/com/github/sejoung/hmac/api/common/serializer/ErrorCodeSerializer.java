package com.github.sejoung.hmac.api.common.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.github.sejoung.hmac.api.common.constants.ErrorCode;
import java.io.IOException;

public class ErrorCodeSerializer extends StdSerializer<ErrorCode> {

    public ErrorCodeSerializer() {
        super(ErrorCode.class);
    }

    @Override
    public void serialize(ErrorCode errorCode, JsonGenerator jsonGenerator,
        SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeFieldName("code");
        jsonGenerator.writeString(errorCode.name());
        jsonGenerator.writeFieldName("message");
        jsonGenerator.writeString(errorCode.getMessage());
        jsonGenerator.writeEndObject();
    }
}
