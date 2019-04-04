package com.plutux.kafka

import com.google.protobuf.Message
import io.github.gaplotech.PBCodec
import org.apache.kafka.common.serialization.Deserializer
import org.bson.BsonBinaryReader
import org.bson.codecs.DecoderContext
import java.nio.ByteBuffer

class KafkaBsonProtoDeserializer<T : Message>(clazz: Class<T>) :
    Deserializer<T> {
    private val codec = PBCodec(clazz)

    override fun configure(configs: MutableMap<String, *>?, isKey: Boolean) {}

    override fun deserialize(topic: String, data: ByteArray): T {
        return BsonBinaryReader(ByteBuffer.wrap(data))
            .use {
                //Workaround: To setup the reader to have currencyBsonType for PBBsonReader to read
                it.readBsonType()

                codec.decode(it, DecoderContext.builder().build())
            }
    }

    override fun close() {}
}