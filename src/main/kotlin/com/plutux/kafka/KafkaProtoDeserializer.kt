package com.plutux.kafka

import com.google.protobuf.Message
import org.apache.kafka.common.serialization.Deserializer

class KafkaProtoDeserializer<T : Message>(private val clazz: Class<T>) : Deserializer<T> {
    override fun configure(configs: MutableMap<String, *>?, isKey: Boolean) {}

    override fun deserialize(topic: String, data: ByteArray): T {
        val builder = clazz.getDeclaredMethod("newBuilder").invoke(null) as com.google.protobuf.Message.Builder
        builder.mergeFrom(data)

        @Suppress("unchecked_cast")
        return builder.build() as T
    }

    override fun close() {}
}

