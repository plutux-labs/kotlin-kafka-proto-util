package com.plutux.kafka

import com.google.protobuf.Message
import io.github.gaplotech.PBCodec
import org.apache.kafka.common.serialization.Serializer
import org.bson.BsonBinaryWriter
import org.bson.codecs.EncoderContext
import org.bson.io.BasicOutputBuffer

class KafkaBsonProtoSerializer<T : Message>(clazz: Class<T>) :
    Serializer<T> {
    private val codec = PBCodec(clazz)

    override fun configure(configs: MutableMap<String, *>, isKey: Boolean) {
    }

    override fun serialize(topic: String, data: T): ByteArray {
        val buf = BasicOutputBuffer()
        BsonBinaryWriter(buf).use {
            codec.encode(it, data, EncoderContext.builder().build())

            return buf.internalBuffer.copyOf(buf.size)
        }
    }

    override fun close() {
    }

}
