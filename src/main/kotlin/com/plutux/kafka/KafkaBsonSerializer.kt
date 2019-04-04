package com.plutux.kafka

import org.apache.kafka.common.serialization.Serializer
import org.bson.BsonBinaryWriter
import org.bson.BsonDocument
import org.bson.codecs.BsonDocumentCodec
import org.bson.codecs.EncoderContext
import org.bson.io.BasicOutputBuffer

class KafkaBsonSerializer : Serializer<BsonDocument> {

    private val codec = BsonDocumentCodec()

    override fun configure(configs: MutableMap<String, *>, isKey: Boolean) {
    }

    override fun serialize(topic: String, data: BsonDocument): ByteArray {
        val buf = BasicOutputBuffer()

        return BsonBinaryWriter(buf).use {
            codec.encode(it, data, EncoderContext.builder().build())

            buf.internalBuffer.copyOf(buf.size)
        }
    }

    override fun close() {
    }

}

