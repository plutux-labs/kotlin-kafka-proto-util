package com.plutux.kafka

import com.google.protobuf.Message
import org.apache.kafka.common.serialization.Serializer

class KafkaProtoSerializer<T : Message> : Serializer<T> {
    override fun configure(configs: MutableMap<String, *>?, isKey: Boolean) {}

    override fun serialize(topic: String, data: T): ByteArray {
        return data.toByteArray()
    }

    override fun close() {}
}